package com.pizidea.coolplay.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pizidea.coolplay.R;
import com.pizidea.coolplay.widget.adapter.AutoAdapter;
import com.pizidea.coolplay.widget.adapter.SocialStreamAdapter;
import com.pizidea.coolplay.widget.adapter.ViewBuilder;
import com.pizidea.framework.widgets.A3ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yflai on 2015/1/22.
 */
public class TimelineFragment3 extends Fragment {

    //private RecyclerView mRecyclerView;
    private A3ListView mListView;
    private SocialStreamAdapter mAdapter;
    private List<String> dataList = new ArrayList<String>();

    private Activity mActivity;
    private View contentView;


    private final String FROM_COMENTER_HEAD = "IMAGE_HEAD_PATH";
    private final String FROM_COMMENTER_NAME = "commenter_name";
    private final String FROM_COMMENTER_CLICK = "commenter_click";
    private final String FROM_COMMENT_TIME = "comment_time";
    private final String FROM_COMMENT_CONTENT = "comment_content";
    private final String FROM_CONTENT_CLICK = "content_click";

    private int[] resId = {R.layout.item_game_list,R.layout.item_game_hot};

    private String[] from = {FROM_COMENTER_HEAD, FROM_COMMENTER_NAME,
            FROM_COMMENTER_CLICK, FROM_COMMENT_TIME,
            FROM_COMMENT_CONTENT, FROM_CONTENT_CLICK};

    private int[] to = {R.id.iv_avatar, R.id.tv_name,
            R.id.tv_name, R.id.tv_name,
            R.id.tv_name, R.id.tv_name};

    //数据列表
    private List<Map<String,Object>> data = new ArrayList<Map<String, Object>>();





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
        //mAdapter = new MyAdapter(getActivity(),dataList);
        for (int i = 0; i < 100; i++){
            dataList.add("item"+i);
        }

        //mListView.setAdapter(mAdapter);
        initAdapter();

    }


    private void initAdapter() {
        Map<Integer, String[]> fromMap = new HashMap<Integer, String[]>();
        fromMap.put(resId[0], from);
        fromMap.put(resId[1], from);
        Map<Integer, int[]> toMap = new HashMap<Integer, int[]>();
        toMap.put(resId[0], to);
        toMap.put(resId[1], to);

        mAdapter = new SocialStreamAdapter(getActivity(), data, resId, fromMap, toMap, 32, 16);

        HashMap<String,Object> map ;

        for(int i = 0 ; i < 20 ; i++){
            map = new HashMap<String, Object>();
            map.put(FROM_COMMENTER_NAME,"aaaaa"+i);


            if(i == 1){
                map.put(SocialStreamAdapter.CommonDataKey.ITEM_TYPE,1);
                map.put(FROM_COMMENT_CONTENT,"热门活动");
            }else{
                map.put(FROM_COMMENT_CONTENT,"王佳佳");
            }
            data.add(map);
        }

        mListView.setAdapter(mAdapter);

    };

    private Map<String, Object> parseData(){
        final HashMap<String, Object> map = new HashMap<String, Object>();
        return map;
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
