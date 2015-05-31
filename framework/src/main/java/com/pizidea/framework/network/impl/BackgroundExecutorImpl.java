package com.pizidea.framework.network.impl;

import android.os.*;
import android.os.Process;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.pizidea.framework.consts.AppConfig;
import com.pizidea.framework.network.BackgroundCallRunnable;
import com.pizidea.framework.network.BackgroundExecutor;
import com.pizidea.framework.network.NetworkCallRunnable;

import java.util.concurrent.ExecutorService;

import retrofit.RetrofitError;

/**
 * desc your class
 * Created by yflai on 2015/5/31.
 */
public class BackgroundExecutorImpl implements BackgroundExecutor {

    private static final Handler sHandler = new Handler(Looper.getMainLooper());
    private final ExecutorService mExecutorService;

    public BackgroundExecutorImpl(ExecutorService executorService) {
        mExecutorService = Preconditions.checkNotNull(executorService, "executorService cannot be null");
    }


    @Override
    public <E> void execute(NetworkCallRunnable<E> runnable) {
        mExecutorService.execute(new NetworkRunner<>(runnable));

    }

    @Override
    public <E> void execute(BackgroundCallRunnable<E> runnable) {
        mExecutorService.execute(new BackgroundCallRunner<>(runnable));

    }


    private class BackgroundCallRunner<E> implements Runnable{
        private final BackgroundCallRunnable<E> mBackgroundCallRunnable;

        BackgroundCallRunner(BackgroundCallRunnable<E> runnable) {
            mBackgroundCallRunnable = runnable;
        }

        @Override
        public void run() {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    mBackgroundCallRunnable.onPreCall();
                }
            });

            E result = mBackgroundCallRunnable.runAsync();//run in background thread

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
                mBackgroundCallRunnable.onPostCall(mResult);
            }
        }

    }


    /**
     * For network
     * @param <E>
     */
    private class NetworkRunner<E> implements Runnable{
        private final NetworkCallRunnable<E> mNetworkCallRunnable;

        NetworkRunner(NetworkCallRunnable<E> runnable){
            mNetworkCallRunnable = runnable;
        }

        @Override
        public void run() {
            android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    mNetworkCallRunnable.onPreCall();
                }
            });

            E result = null;
            RetrofitError retrofitError = null;

            try {
                result = mNetworkCallRunnable.doInBackground();
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
                    mNetworkCallRunnable.onSuccess(mResult);
                } else if (mRetrofitError != null) {
                    mNetworkCallRunnable.onError(mRetrofitError);
                }
                mNetworkCallRunnable.onPostCall();//always call this
            }
        }

    }



}
