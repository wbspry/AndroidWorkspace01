package yyyske3.androidworkspace01;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    public class MyButton extends Button{

        public MyButton(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            Log.d(TAG,"MyButton - onMeasure");
        }

        @Override
        protected void onFinishInflate() {
            super.onFinishInflate();
            Log.d(TAG,"MyButton - onFinishInflate");
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
            Log.d(TAG,"MyButton - onLayout");
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Log.d(TAG,"MyButton - onDraw");
        }

        @Override
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            Log.d(TAG,"MyButton - onAttachedToWindow");
        }

        @Override
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            Log.d(TAG,"MyButton - onDetachedFromWindow");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.rlTop);

        MyButton myButton = new MyButton(this);
        myButton.setText("test!!");

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.dp60),
                getResources().getDimensionPixelSize(R.dimen.dp60));
        layoutParams.addRule(RelativeLayout.BELOW, R.id.tvHello);

        relativeLayout.addView(myButton,layoutParams);



    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume");

    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart");

    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart");

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "onWindowFocusChanged");

    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause");

    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop");

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");

    }

}
