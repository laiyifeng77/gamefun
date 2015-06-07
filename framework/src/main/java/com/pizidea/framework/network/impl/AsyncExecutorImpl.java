package com.pizidea.framework.network.impl;

import android.os.*;
import android.os.Process;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.pizidea.framework.consts.AppConfig;
import com.pizidea.framework.network.AsyncExecutor;
import com.pizidea.framework.network.BackgroundCallable;
import com.pizidea.framework.network.NetworkCallable;

import java.util.concurrent.ExecutorService;

import retrofit.RetrofitError;

/**
 * desc your class
 * Created by yflai on 2015/5/31.
 */
public class AsyncExecutorImpl implements AsyncExecutor {

    private static final Handler sHandler = new Handler(Looper.getMainLooper());
    private final ExecutorService mExecutorService;

    public AsyncExecutorImpl(ExecutorService executorService) {
        mExecutorService = Preconditions.checkNotNull(executorService, "executorService cannot be null");
    }


    @Override
    public <E> void execute(NetworkCallable<E> runnable) {
        mExecutorService.execute(new NetworkRunner<>(runnable));

    }

    @Override
    public <E> void execute(BackgroundCallable<E> runnable) {
        mExecutorService.execute(new BackgroundCallRunner<>(runnable));

    }


    private class BackgroundCallRunner<E> implements Runnable{
        private final BackgroundCallable<E> mBackgroundCallable;

        BackgroundCallRunner(BackgroundCallable<E> runnable) {
            mBackgroundCallable = runnable;
        }

        @Override
        public void run() {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    mBackgroundCallable.onPreCall();
                }
            });

            E result = mBackgroundCallable.runAsync();//run in background thread

            sHandler.post(new ResultCallback(result));

        }

        /**
         * Result wrapper
         */
        private class ResultCallback implements Runnable {
            private final E mResult;

            private ResultCallback(E result) {
                mResult = result;
            }

            @Override
            public void run() {
                mBackgroundCallable.onPostCall(mResult);
            }
        }

    }


    /**
     * For network
     * @param <E>
     */
    private class NetworkRunner<E> implements Runnable{
        private final NetworkCallable<E> mNetworkCallable;

        NetworkRunner(NetworkCallable<E> runnable){
            mNetworkCallable = runnable;
        }

        @Override
        public void run() {
            android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    mNetworkCallable.onPreCall();
                }
            });

            E result = null;
            RetrofitError retrofitError = null;

            try {
                result = mNetworkCallable.doInBackground();
            } catch (RetrofitError re) {
                retrofitError = re;
                if (AppConfig.DEBUG) {
                    Log.d(((Object) this).getClass().getSimpleName(), "Error while completing network call", re);
                }
            }

            sHandler.post(new ResultCallback(result, retrofitError));

        }

        /**
         * Result wrapper
         */
        private class ResultCallback implements Runnable {
            private final E mResult;
            private final RetrofitError mRetrofitError;

            private ResultCallback(E result, RetrofitError retrofitError) {
                mResult = result;
                mRetrofitError = retrofitError;
            }

            @Override
            public void run() {
                if (mResult != null) {
                    mNetworkCallable.onSuccess(mResult);
                } else if (mRetrofitError != null) {
                    mNetworkCallable.onError(mRetrofitError);
                }
                mNetworkCallable.onPostCall();//always call this
            }
        }

    }



}
