package com.pizidea.coolplay.widget.adapter;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * getView()中生成item的View
 * @author laiyifeng@ruijie.com.cn
 * @date 20150420
 */
public class ViewBuilder {
	 
    /**
     * 得到视图为 view 的 viewHodler
     * @param view
     * @return
     */
    public ViewHolder get(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
 
        return viewHolder;
    }
 
 
    /**
     * viewhodler  存储 view的子 view 的索引
     * @author laiyifeng@ruijie.com.cn
     */
    public class ViewHolder { 
        private SparseArray<View> viewHolder;
        private View view;
 
        public ViewHolder(View view) {
            this.view = view;
            viewHolder = new SparseArray<View>();
        }
 
        @SuppressWarnings("unchecked")
		public <T extends View> T get(int id) {
 
            View childView = viewHolder.get(id);
            if (childView == null) {
                childView = view.findViewById(id);
                viewHolder.put(id, childView);
            }
            return (T) childView;
 
        }
        
 
        public View getView(int id) { 
            return get(id);
        }
 
        /**
         * 得到 view 下 id 为 id 的TextView 这里没有做类型的判断所以你要保证 id 为 id 的控件确实为 TextView类型
         * @param id
         * @return
         */
        public TextView getTextView(int id) { 
            return get(id);
        }
 
        public ImageView getImageView(int id) { 
            return get(id);
        }
 
 
    }
 
}
