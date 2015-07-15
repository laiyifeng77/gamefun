package com.pizidea.coolplay.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pizidea.coolplay.R;
import com.pizidea.coolplay.widget.recyclerview.RecyclerViewDivider;

/**
 * Created by yflai on 2015/1/22.
 */
public class TimelineFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private Activity mActivity;
    private View contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        contentView = getLayoutInflater(savedInstanceState).inflate(R.layout.fragment_timeline,null);
        initRecyclerView();
        return contentView;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = getActivity();
    }

    public void initRecyclerView(){
        //mRecyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerview_vertical);


        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        // 默认是Vertical，可以不写
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);

        // 创建数据集
        String[] dataset = new String[100];
        for (int i = 0; i < dataset.length; i++){
            dataset[i] = "item" + i;
        }
        // 创建Adapter，并指定数据集
        GameAdapter adapter = new GameAdapter(dataset);
        // 设置Adapter
        mRecyclerView.setAdapter(adapter);

        RecyclerView.ItemDecoration decoration = new RecyclerViewDivider(mActivity);
        mRecyclerView.addItemDecoration(decoration);

    }

    /**
     * adapter
     */
    class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder>{

        // 数据集
        private String[] mDataset;

        public GameAdapter(String[] dataset) {
            super();
            mDataset = dataset;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
            View view = View.inflate(viewGroup.getContext(), R.layout.item_game_list, null);
            // 创建一个ViewHolder
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            // 绑定数据到ViewHolder上
            // viewHolder.mTextView.setText(mDataset[i]);
        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }


        class ViewHolder extends RecyclerView.ViewHolder{

            public ImageView mIvGamePic;
            public TextView mTvGameTitle;
            public TextView mTvAdminName;
            public TextView mTvDateTime;

            public ViewHolder(View itemView) {
                super(itemView);
                mIvGamePic = (ImageView) itemView.findViewById(R.id.iv_pic);
                mTvGameTitle = (TextView) itemView.findViewById(R.id.tv_game_title);
                mTvAdminName = (TextView) itemView.findViewById(R.id.tv_name);
                mTvDateTime = (TextView) itemView.findViewById(R.id.tv_datetime);
            }
        }


    }




}
