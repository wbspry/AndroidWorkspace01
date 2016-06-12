package yyyske3.androidworkspace01;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chama on 16/06/11.
 */
public class IconSettingActivity extends Activity implements OnStartDragListener, OnItemAddListener{

    private ItemTouchHelper mItemTouchHelper;
    private RecyclerAdapter mAdapter;
    private List<String> mIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iconsetting);

        RecyclerView rv_iconGrid = (RecyclerView)findViewById(R.id.rv_iconGrid);
        rv_iconGrid.setLayoutManager(new GridLayoutManager(IconSettingActivity.this, 4));

        mIcons = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            String iconName = (i + 1) + "個目";
            mIcons.add(iconName);
        }

        //上の方のグリッド。編集するグリッド。
        mAdapter = new RecyclerAdapter(this, mIcons, mItemTouchHelper, this);
        rv_iconGrid.setAdapter(mAdapter);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(rv_iconGrid);



        //

        RecyclerView rv_addingList = (RecyclerView)findViewById(R.id.rv_addingList);
        rv_addingList.setLayoutManager(new GridLayoutManager(IconSettingActivity.this, 2));

        List<String> icons2  = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            String iconName = (i + 1) + "icon";
            icons2.add(iconName);
        }

        AddingIconRecyclerAdapter adapter2 = new AddingIconRecyclerAdapter(this, icons2, this);
        rv_addingList.setAdapter(adapter2);

        ItemTouchHelper.Callback callback2 = new AddingIconTouchHelperCallback(adapter2);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback2);
        itemTouchHelper.attachToRecyclerView(rv_addingList);



        Button button = (Button)findViewById(R.id.btn_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //2個めのRecyclerViewをオーバーレイする
                RecyclerView rv_addingList = (RecyclerView)findViewById(R.id.rv_addingList);
                rv_addingList.setTranslationY(rv_addingList.getY() * -1);
            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(" *** ", "上のグリッドでもロングタップ感知");
                Log.d(" *** ", "位置:" + v.getX() + ":" + v.getY());
                return false;
            }
        });

    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

        //編集する方のグリッドのヘルパのstartDragを呼ぶ。

        //ViewHolderを渡す必要がある。
        //アイテムを新しく作って、グリッド1号に追加し、そのViewHolderを得てここで渡したい。
        //どうやる？それができたら終わりなんだが。







        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void onItemAdd(RecyclerView.ViewHolder viewHolder, float x, float y) {
        //オーバーレイViewでアイコンがロングタップされた場合、ここが動く。


        //ドラッグ元からアイコン名部分を取り出し
        TextView tv = (TextView)viewHolder.itemView.findViewById(R.id.tv_icon);
        //アイコン名をセットして上グリッドの裏データを追加
        mIcons.add(tv.getText().toString());
        //追加の旨告知
        mAdapter.notifyDataSetChanged();

        //追加されたものを即動かしたいんだけどどうやるの・・？

//        MotionEvent ev = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis() + 100, MotionEvent.ACTION_DOWN, x, y, 0);
//
//        this.onTouchEvent(ev);
    }

    public static class RecyclerAdapter extends RecyclerView.Adapter{

        private Context mContext;
        private List<String> mItemList = new ArrayList<>();
        private ItemTouchHelper mItemTouchHelper;

        private OnStartDragListener mOnStartDragListener;

        public RecyclerAdapter(Context context, List<String> itemList, ItemTouchHelper itemTouchHelper, OnStartDragListener onStartDragListener){
            mContext = context;
            mItemList = itemList;
            mItemTouchHelper = itemTouchHelper;
            mOnStartDragListener = onStartDragListener;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.grid_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            TextView tv = (TextView) holder.itemView.findViewById(R.id.tv_icon);
            tv.setText(mItemList.get(position));

            // Start a drag whenever the handle view it touched
            holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        mOnStartDragListener.onStartDrag(holder);
                    }
                    return false;
                }
            });



////            新しく追加されたやつはドラッグ開始
//            if(position >= 20){
//                Log.d(" *** ", "新しく追加されたのでドラッグ開始");
//                //この時点ではまだgetParentがぬnullな模様？
////                Log.d(" *** ", holder.itemView.getParent().toString());
//                mItemTouchHelper.startDrag(holder);
//            }

        }


        @Override
        public int getItemCount() {
            return mItemList.size();
        }

        public boolean onItemMove(int fromPosition, int toPosition){
            Collections.swap(mItemList, fromPosition, toPosition);
            notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder{
            private ImageView mImageView;
            private TextView mTextView;
            public ViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.iv_icon);
                mTextView = (TextView) itemView.findViewById(R.id.tv_icon);
            }
        }
    }

    public static class AddingIconRecyclerAdapter extends RecyclerView.Adapter{

        private final OnItemAddListener mOnItemAddListener;

        private Context mContext;
        private List<String> mItemList = new ArrayList<>();

        public AddingIconRecyclerAdapter(Context context, List<String> itemList, OnItemAddListener onItemAddListener){
            mContext = context;
            mItemList = itemList;
            mOnItemAddListener = onItemAddListener;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.grid_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            TextView tv = (TextView) holder.itemView.findViewById(R.id.tv_icon);
            tv.setText(mItemList.get(position));

            ImageView iv = (ImageView)holder.itemView.findViewById(R.id.iv_icon);
            iv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemAddListener.onItemAdd(holder, v.getX(), v.getY());
                    return true;
                }
            });

        }

        @Override
        public int getItemCount() {
            return mItemList.size();
        }

        public boolean onItemMove(int fromPosition, int toPosition){
            Collections.swap(mItemList, fromPosition, toPosition);
            notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder{
            private ImageView mImageView;
            private TextView mTextView;
            public ViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.iv_icon);
                mTextView = (TextView) itemView.findViewById(R.id.tv_icon);
            }
        }
    }

}
