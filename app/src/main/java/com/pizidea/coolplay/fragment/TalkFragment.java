package com.pizidea.coolplay.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pizidea.coolplay.R;
import com.pizidea.coolplay.widget.adapter.SocialStreamAdapter;
import com.pizidea.framework.widgets.A3ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yflai on 2015/1/22.
 */
public class TalkFragment extends Fragment {

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

    private int[] resId = {R.layout.item_talk_news_first,R.layout.item_talk_news,R.layout.item_talk_fellows,R.layout.item_talk_photos,R.layout.item_talk_graduate_first,R.layout.item_talk_graduate,R.layout.item_talk_channels_first,R.layout.item_talk_channels};

    private String[] from = {FROM_COMENTER_HEAD, FROM_COMMENTER_NAME,
            FROM_COMMENTER_CLICK, FROM_COMMENT_TIME,
            FROM_COMMENT_CONTENT, FROM_CONTENT_CLICK};

    private int[] to = {R.id.iv_avatar, R.id.tv_name,
            R.id.tv_name, R.id.tv_name,
            R.id.tv_game_name, R.id.tv_name};

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
        fromMap.put(resId[4], from);
        fromMap.put(resId[5], from);
        fromMap.put(resId[6], from);
        fromMap.put(resId[7], from);

        Map<Integer, int[]> toMap = new HashMap<Integer, int[]>();
        toMap.put(resId[0], to);
        toMap.put(resId[1], to);
        toMap.put(resId[2], to);
        toMap.put(resId[3], to);
        toMap.put(resId[4], to);
        toMap.put(resId[5], to);
        toMap.put(resId[6], to);
        toMap.put(resId[7], to);

        mAdapter = new SocialStreamAdapter(getActivity(), data, resId, fromMap, toMap, 32, 16);

        HashMap<String,Object> map ;

        if(data.size()>0){
            data.clear();
        }

        for(int i = 0 ; i < 20 ; i++){
            map = new HashMap<String, Object>();
            map.put(FROM_COMMENTER_NAME,"aaaaa"+i);

            if(i == 0){
                map.put(SocialStreamAdapter.CommonDataKey.ITEM_TYPE,0);
                map.put(FROM_COMMENTER_NAME,"iknow");
            }else if(i < 3){
                map.put(SocialStreamAdapter.CommonDataKey.ITEM_TYPE,1);
                //map.put(FROM_COMMENTER_NAME,"校园一角");
            }else if(i == 3){
                map.put(SocialStreamAdapter.CommonDataKey.ITEM_TYPE,2);
                map.put(FROM_COMMENTER_NAME,"同学们");
            }else if(i == 4){
                map.put(SocialStreamAdapter.CommonDataKey.ITEM_TYPE,3);
                map.put(FROM_COMMENTER_NAME,"校园一角");

            }else if(i == 5){
                map.put(SocialStreamAdapter.CommonDataKey.ITEM_TYPE,4);
                map.put(FROM_COMMENTER_NAME,"毕业那些事");
                map.put(FROM_COMMENT_CONTENT,"离开了桂林电子科技大学，有太多的不舍，终究逃不过毕业.");
            }else if( i < 9){
                map.put(SocialStreamAdapter.CommonDataKey.ITEM_TYPE,5);
                map.put(FROM_COMMENT_CONTENT,"为什么你对生活如此留恋？因为你爱你的校园");
            }else if(i == 9){
                map.put(SocialStreamAdapter.CommonDataKey.ITEM_TYPE,6);
                map.put(FROM_COMMENTER_NAME,"推荐频道");
                map.put(FROM_COMMENT_CONTENT,"十一教那些事 HOT");
            }else{
                map.put(SocialStreamAdapter.CommonDataKey.ITEM_TYPE,7);
                map.put(FROM_COMMENT_CONTENT,"北师大夜话");
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
