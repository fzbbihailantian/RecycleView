package com.yaoban.recycleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class fzbItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider_H;
    private Drawable mDivider_V;
    private int spancount = -1;
    private final Rect mBounds = new Rect();

    public fzbItemDecoration(Context context, int spancount) {
        this.spancount = spancount;
    }

    public void setmDivider_H(Drawable mDivider_H) {
        this.mDivider_H = mDivider_H;
    }

    public void setmDivider_V(Drawable mDivider_V) {
        this.mDivider_V = mDivider_V;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getLayoutManager() != null && this.mDivider_V != null) {
            this.drawVertical(c, parent);
        }
        if (parent.getLayoutManager() != null && this.mDivider_H != null) {
            this.drawHorizontal(c, parent);
        }
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int top;
        int bottom;
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top, parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount ; ++i) {
            if (i%spancount==spancount-1) continue;

            View child = parent.getChildAt(i);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, this.mBounds);
            int right = this.mBounds.right + Math.round(child.getTranslationX());
            int left = right - this.mDivider_H.getIntrinsicWidth();
            this.mDivider_H.setBounds(left, top, right, bottom);
            this.mDivider_H.draw(canvas);
        }

        canvas.restore();
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int left;
        int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount ; ++i) {

            if (spancount>-1 &&childCount-i<=spancount){
                continue;
            }

            View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, this.mBounds);
            int bottom = this.mBounds.bottom + Math.round(child.getTranslationY());
            int top = bottom - this.mDivider_V.getIntrinsicHeight();
            this.mDivider_V.setBounds(left, top, right, bottom);
            this.mDivider_V.draw(canvas);
        }

        canvas.restore();
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    //item  偏移  相当添加 margin
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (this.mDivider_H == null||this.mDivider_H==null) {
            outRect.set(0, 0, 0, 0);
        } else {
            outRect.set(0, 0, parent.getChildAdapterPosition(view)%spancount==spancount-1?0:this.mDivider_H.getIntrinsicWidth()/2, parent.getAdapter().getItemCount()-parent.getChildAdapterPosition(view)<=parent.getAdapter().getItemCount()%spancount?0:this.mDivider_V.getIntrinsicHeight()/2);

        }

    }
}
