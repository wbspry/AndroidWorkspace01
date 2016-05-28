package yyyske3.androidworkspace01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by chama on 16/05/28.
 */
public class WrapLayout extends RelativeLayout {
    public WrapLayout(Context context) {
        super(context);
    }

    public WrapLayout(Context context, AttributeSet attrs)
    {
        super(context,attrs);
    }

    public WrapLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);
    }

    @Override
    public void addView(View child) {
        // idが未設定の場合は乱数でどうにかする。
        int id = new Random(System.currentTimeMillis()).nextInt(Integer.MAX_VALUE);
        if (child.getId() == -1) {
            child.setId(id);
            Log.d("tag", "addView setID:" + id);
        }


        super.addView(child);
    }

    @Override
    public void addView(View child, int width, int height) {
        // idが未設定の場合は乱数でどうにかする。
        if (child.getId() == -1) { child.setId(new Random(System.currentTimeMillis()).nextInt(Integer.MAX_VALUE)); }

        super.addView(child, width, height);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        // idが未設定の場合は乱数でどうにかする。
        if (child.getId() == -1) { child.setId(new Random(System.currentTimeMillis()).nextInt(Integer.MAX_VALUE)); }

        super.addView(child, params);
    }

    @Override
    public void addView(View child, int index) {
        // idが未設定の場合は乱数でどうにかする。
        if (child.getId() == -1) { child.setId(new Random(System.currentTimeMillis()).nextInt(Integer.MAX_VALUE)); }

        super.addView(child, index);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        // idが未設定の場合は乱数でどうにかする。
        if (child.getId() == -1) { child.setId(new Random(System.currentTimeMillis()).nextInt(Integer.MAX_VALUE)); }

        super.addView(child, index, params);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.d("tag", "WrapLayout - onWindowFocusChanged");

//        int l = this.getChildCount();
//        if(l == 0){
//            return;
//        }
//
//        WindowManager wm = (WindowManager)this.getContext().getSystemService(Context.WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
//
//        Point point = new Point();
//        display.getSize(point);
//        int max = point.x;
//
//        View pline = this.getChildAt(0);
//
//        int currentTotal = pline.getWidth();
//
//        for(int i = 1; i < l; i++){
//            int w = this.getChildAt(i).getWidth();
//            RelativeLayout.LayoutParams nextLayoutParams = (RelativeLayout.LayoutParams)this.getChildAt(i).getLayoutParams();
//
//            Log.d("tag", currentTotal + "");
//            int prevId = this.getChildAt(i - 1).getId();
//            Log.d("tag", "prevId:" + prevId);
//            int nextId = this.getChildAt(i).getId();
//            Log.d("tag", "nextId:" + nextId);
//
//            if(max > currentTotal + w) {
//                Log.d("tag", "max:" + max);
//                Log.d("tag", "currentTotal:" + currentTotal);
//                Log.d("tag", "w:" + w);
//                currentTotal += w;
//                nextLayoutParams.addRule(RelativeLayout.ALIGN_TOP, prevId);
//                nextLayoutParams.addRule(RelativeLayout.RIGHT_OF, prevId);
//                this.getChildAt(i).setLayoutParams(nextLayoutParams);
//            }else{
//                Log.d("tag", "else");
//                nextLayoutParams.addRule(RelativeLayout.BELOW,prevId);
//                pline = this.getChildAt(i);
//                currentTotal = pline.getWidth();
//            }
//        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int marginWidth = getResources().getDimensionPixelSize(R.dimen.dp60);
        Log.d("tag","WrapLayout - onMeasure");
        int l = this.getChildCount();
        if (l > 0) {
        int max = MeasureSpec.getSize(widthMeasureSpec);
        View pline = this.getChildAt(0);
        View prev = this.getChildAt(0);
        prev.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        int currentTotal = pline.getMeasuredWidth();
        for (int i = 1; i < l; i++) {
            View child = this.getChildAt(i);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int width = child.getMeasuredWidth() + marginWidth;
            RelativeLayout.LayoutParams layoutParams =
                    (RelativeLayout.LayoutParams) child.getLayoutParams();
            if (max > currentTotal + width) {
                currentTotal += width;
                layoutParams.addRule(RelativeLayout.ALIGN_TOP, prev.getId());
                layoutParams.addRule(RelativeLayout.RIGHT_OF, prev.getId());
                layoutParams.setMargins(marginWidth,0,0,0);
            } else {
                layoutParams.addRule(RelativeLayout.BELOW, pline.getId());
                layoutParams.addRule(RelativeLayout.ALIGN_LEFT, pline.getId());
                pline = child;
                currentTotal = pline.getMeasuredWidth();
                if(currentTotal > max){
                    Log.d("tag","currentTotal OVER:" + currentTotal );
                }
            }
            prev = child;
        }
    }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("tag","WrapLayout - onDraw");

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.d("tag","WrapLayout - dispatchDraw");


        int l = this.getChildCount();
        if(l == 0){
            return;
        }

        for(int i = 0; i < l; i++){

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)getChildAt(i).getLayoutParams();
            int[] rules =  layoutParams.getRules();


            Log.d("tag", "RULES:" + Arrays.toString(rules));

        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d("tag","WrapLayout - onLayout");
    }
}
