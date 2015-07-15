package com.pizidea.coolplay.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pizidea.coolplay.R;
import com.pizidea.coolplay.widget.adapter.AutoAdapter;
import com.pizidea.coolplay.widget.adapter.ViewBuilder;
import com.pizidea.framework.widgets.A3ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yflai on 2015/1/22.
 */
public class TimelineFragment2 extends Fragment {

    //private RecyclerView mRecyclerView;
    private A3ListView mListView;
    private MyAdapter mAdapter;
    private List<String> dataList = new ArrayList<String>();

    private Activity mActivity;
    private View contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        contentView = getLayoutInflater(savedInstanceState).inflate(R.layout.fragment_timeline,null);
        initListView();
        return contentView;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = getActivity();
    }

    private void initListView(){
        mListView = (A3ListView) contentView.findViewById(R.id.listview);
        mListView.setPullRefreshEnable(true);
        mAdapter = new MyAdapter(getActivity(),dataList);
        for (int i = 0; i < 100; i++){
            dataList.add("item"+i);
        }

        mListView.setAdapter(mAdapter);

    }


    private class MyAdapter extends AutoAdapter {

        public MyAdapter(Context context, List<?> list) {
            super(context, list, R.layout.item_game_list);
        }

        /**
         * 将数据绑定在视图上
         */
        @Override
        public void getView33(int position, View v, ViewBuilder.ViewHolder vh) {
            //final GameFundedMemberBean member = (GameFundedMemberBean) getItem(position);

            //mIvGamePic = (ImageView) itemView.findViewById(R.id.iv_pic);

            vh.getTextView(R.id.tv_game_title).setText("5444");
            vh.getTextView(R.id.tv_name).setText("4555");
            vh.getTextView(R.id.tv_datetime).setText("3322");

           // ImageLoaderUtils.displayImageForDefaultHead(vh.getImageView(R.id.iv_avatar), member.getAvatar());

            vh.getView(R.id.background).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*if(member.getUser_type() == 3){
                        GameMember m = new GameMember();
                        m.setUid(member.getUid());
                        m.setAvatar(member.getAvatar());
                        //m.setDesc(member.get);
                        FanrUtils.jump2ClubIndex(FundedMembersActivity.this, Integer.valueOf(member.getUid()),member.getUser_type());

                        //FanrUtils.jump2ClubIndex(FundedMembersActivity.this, member);
                    }else if(member.getUser_type() == 2){
                        FanrUtils.jump2UserIndex(FundedMembersActivity.this, Integer.valueOf(member.getUid()),member.getUser_type());

                    }*/


                }
            });

        }


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
