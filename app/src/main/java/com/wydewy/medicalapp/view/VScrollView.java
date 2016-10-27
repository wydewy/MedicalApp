package com.wydewy.medicalapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by wydewy on 16-10-15.
 */

public class VScrollView extends ScrollView {
    private ClickEventListener clickEventListener;
    private int firstX;
    private int secondX;

    public VScrollView(Context context) {
        super(context);
    }

    public VScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        final int action = event.getAction();
        final int moveX = (int) event.getX();
        final int scape = moveX - firstX;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                firstX = (int) event.getX();//按下的时候开始的x的位置
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                secondX = (int) event.getX();//up的时候x的位置
                int distance = secondX - firstX;
                if (distance == 0) {
                    if (this.clickEventListener != null) {
                        clickEventListener.onClick();
                    }
                }
                break;
        }
        return true;
    }

    public void setClickEventListener(ClickEventListener clickEventListener) {
        this.clickEventListener = clickEventListener;
    }

    public interface ClickEventListener {
        void onClick();
    }
}
