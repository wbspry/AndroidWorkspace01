package yyyske3.androidworkspace01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnLongClickListener{

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public boolean onLongClick(View v) {
        Log.d(TAG, "LongClicked");
        Intent intent = new Intent(MainActivity.this, IconSettingActivity.class);
        startActivity(intent);
        return true;
    }

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
        protected void onDetachedFromWindow(    ) {
            super.onDetachedFromWindow();
            Log.d(TAG,"MyButton - onDetachedFromWindow");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        setWrapLayout();

        ImageView iv = (ImageView)findViewById(R.id.iv_search);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this, "menu is tap!", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

            }
        });

        LinearLayout ll_main = (LinearLayout)findViewById(R.id.ll_main);
        ll_main.setOnLongClickListener(this);



    }

    /**
     * WrapLayout関係のあれこれ
     */
    private void setWrapLayout() {
        WrapLayout wrapLayout = (WrapLayout)findViewById(R.id.wlTop);
        String[] strings = { "てｓｔ１", "てｓｔ２", "てｓｔ３", "てｓｔ４", "てｓｔ５", "てｓｔ６", "てｓｔ７", "てｓｔ８", "てｓｔ９", "てｓｔ１０", "てｓｔ１１１１１１a", "てｓｔ１１１", "てｓｔ２３４あうぇｒぱおう３ｐ４おう３ｐ４２４３２あをいる２３４"};
        int idx = R.id.wlTop + 1;
        for(String str : strings){
            TextView textView = new TextView(this);
            textView.setText(str);
            textView.setId(idx);
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setMaxWidth(1080);

            wrapLayout.addView(textView);

            Log.d(TAG,str + "is added.");
            idx++;
        }
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
