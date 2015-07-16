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

    //private List<String> dataList = new ArrayList<String>();

    private Activity mActivity;
    private View contentView;


    private final String FROM_COMENTER_HEAD = "IMAGE_HEAD_PATH";
    private final String FROM_COMMENTER_NAME = "commenter_name";
    private final String FROM_COMMENTER_CLICK = "commenter_click";
    private final String FROM_COMMENT_TIME = "comment_time";
    private final String FROM_COMMENT_CONTENT = "comment_content";
    private final String FROM_CONTENT_CLICK = "content_click";

    private int[] resId = {R.layout.item_game_new,R.layout.item_game_hot,R.layout.item_timeline_ad,R.layout.item_game_new_first};

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

        /*for (int i = 0; i < 100; i++){
            dataList.add("item"+i);
        }
*/
        //mListView.setAdapter(mAdapter);
        initAdapter();

    }


    private void initAdapter() {
        Map<Integer, String[]> fromMap = new HashMap<Integer, String[]>();
        fromMap.put(resId[0], from);
        fromMap.put(resId[1], from);
        fromMap.put(resId[2], from);
        fromMap.put(resId[3], from);

        Map<Integer, int[]> toMap = new HashMap<Integer, int[]>();
        toMap.put(resId[0], to);
        toMap.put(resId[1], to);
        toMap.put(resId[2], to);
        toMap.put(resId[3], to);

        mAdapter = new SocialStreamAdapter(getActivity(), data, resId, fromMap, toMap, 32, 16);

        HashMap<String,Object> map ;

        if(data.size()>0){
            data.clear();
        }

        for(int i = 0 ; i < 20 ; i++){
            map = new HashMap<String, Object>();
            map.put(FROM_COMMENTER_NAME,"aaaaa"+i);

            if(i == 0){
                map.put(SocialStreamAdapter.CommonDataKey.ITEM_TYPE,2);
            }

            if(i == 1){
                map.put(SocialStreamAdapter.CommonDataKey.ITEM_TYPE,1);
                map.put(FROM_COMMENT_CONTENT,"推荐活动");
            }else if(i == 2){
                map.put(SocialStreamAdapter.CommonDataKey.ITEM_TYPE,3);
                map.put(FROM_COMMENT_CONTENT,"最新活动");
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


}
