package com.pizidea.coolplay.widget.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public abstract class AutoAdapter extends MBaseAdapter{
	 
    /**
     * item 的布局文件
     */
    private int layoutID;
    /**
     * 这是一个生产 ViewHolder的工具类
     */
    private ViewBuilder viewFactory;

    public AutoAdapter(Context context, List<?> list, int layoutID) {
        super(context, list);
        this.layoutID = layoutID;
        viewFactory = new ViewBuilder();
    }

    @Override
    public View getView33(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(layoutID, parent, false);
        }
        getView33(position, convertView, viewFactory.get(convertView));
        return convertView;
    }

    /**
     * 通过暴露这个方法,通过操作vh实现将数据的绑定在视图上
     * @param position
     * @param v
     * @param vh
     */
    public abstract void getView33(int position, View v, ViewBuilder.ViewHolder vh);

}