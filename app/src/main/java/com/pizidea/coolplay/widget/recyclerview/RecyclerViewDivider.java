package com.pizidea.coolplay.widget.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liuzhongyi on 2015/1/22.
 */
public class RecyclerViewDivider extends RecyclerView.ItemDecoration {


    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    private Drawable mDivider;

    public RecyclerViewDivider(Context ctx) {
        final TypedArray a = ctx.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();

        int l = parent.getPaddingLeft();
        int r = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + layoutParams.rightMargin;
            int right = left + mDivider.getIntrinsicWidth();

            int t = child.getBottom() +layoutParams.bottomMargin;
            int b = t+mDivider.getIntrinsicHeight();

            mDivider.setBounds(l, t, r, b);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
    }


}
