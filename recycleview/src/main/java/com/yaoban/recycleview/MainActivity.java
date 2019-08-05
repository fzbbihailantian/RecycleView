package com.yaoban.recycleview;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout layout;
    RecyclerView recyclerView;
    private DividerItemDecoration dv, dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.contentview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "垂直线性布局");
        menu.add(1, 2, 1, "水平线性布局");
        menu.add(1, 3, 1, "网格布局—默认分割线");
        menu.add(1, 4, 1, "自定义分割线");
        menu.add(1, 5, 1, "去除右下多余分割线");
        menu.add(1, 6, 1, "横竖分割线合并");
        menu.add(1, 7, 1, "加外边框");
        menu.add(1, 8, 1, "无边框均匀分布");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        layout.removeAllViews();
        recyclerView = new RecyclerView(this);
        switch (item.getItemId()) {
            case 1:
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(new Adapter());
                recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
                break;
            case 2:
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setAdapter(new Adapter());
                recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
                break;
            case 3:
                recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                recyclerView.setAdapter(new Adapter());
                dv = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
                dh = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
                recyclerView.addItemDecoration(dv);
                recyclerView.addItemDecoration(dh);
                break;
            case 4:
                recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                recyclerView.setAdapter(new Adapter());
                dv = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
                dh = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
                dv.setDrawable(getResources().getDrawable(R.drawable.divideritemdecoration_v));
                dh.setDrawable(getResources().getDrawable(R.drawable.divideritemdecoration_h));
                recyclerView.addItemDecoration(dv);
                recyclerView.addItemDecoration(dh);
                break;
            case 5:
                final EditText editText = new EditText(this);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                new AlertDialog.Builder(this).setTitle("spancount").
                        setView(editText).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int spancount = Integer.parseInt(editText.getText().toString());
                        if (spancount <= 0) spancount = 3;
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, spancount));
                        recyclerView.setAdapter(new Adapter());
                        DividerItemDecoration_br_disappear dv1 = new DividerItemDecoration_br_disappear(MainActivity.this, DividerItemDecoration.VERTICAL, spancount);
                        DividerItemDecoration_br_disappear dh1 = new DividerItemDecoration_br_disappear(MainActivity.this, DividerItemDecoration.HORIZONTAL, spancount);
                        dv1.setDrawable(getResources().getDrawable(R.drawable.divideritemdecoration_v));
                        dh1.setDrawable(getResources().getDrawable(R.drawable.divideritemdecoration_h));
                        recyclerView.addItemDecoration(dv1);
                        recyclerView.addItemDecoration(dh1);
                        layout.removeAllViews();
                        layout.addView(recyclerView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    }
                }).show();


                break;
            case 6:
                final EditText editText2 = new EditText(this);
                editText2.setInputType(InputType.TYPE_CLASS_NUMBER);
                new AlertDialog.Builder(this).setTitle("spancount").
                        setView(editText2).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int spancount = Integer.parseInt(editText2.getText().toString());
                        if (spancount <= 0) spancount = 3;
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, spancount));
                        recyclerView.setAdapter(new Adapter());
                        fzbItemDecoration dv1 = new fzbItemDecoration(MainActivity.this, spancount);
                        dv1.setmDivider_V(getResources().getDrawable(R.drawable.divideritemdecoration_v));
                        dv1.setmDivider_H(getResources().getDrawable(R.drawable.divideritemdecoration_h));
                        recyclerView.addItemDecoration(dv1);
                        recyclerView.addItemDecoration(dv1);
                        layout.removeAllViews();
                        layout.addView(recyclerView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    }
                }).show();
                break;
            case 7:
                final EditText editText3 = new EditText(this);
                editText3.setInputType(InputType.TYPE_CLASS_NUMBER);
                new AlertDialog.Builder(this).setTitle("spancount").
                        setView(editText3).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int spancount = Integer.parseInt(editText3.getText().toString());
                        if (spancount <= 0) spancount = 3;
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, spancount));
                        recyclerView.setAdapter(new Adapter());
                        FrameItemDecoration dv1 = new FrameItemDecoration(MainActivity.this, spancount);
                        dv1.setmDivider_V(getResources().getDrawable(R.drawable.divideritemdecoration_v));
                        dv1.setmDivider_H(getResources().getDrawable(R.drawable.divideritemdecoration_h));
                        recyclerView.addItemDecoration(dv1);
                        recyclerView.addItemDecoration(dv1);
                        layout.removeAllViews();
                        layout.addView(recyclerView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    }
                }).show();
                break;
            case 8:
                final EditText editText4 = new EditText(this);
                editText4.setInputType(InputType.TYPE_CLASS_NUMBER);
                new AlertDialog.Builder(this).setTitle("spancount").
                        setView(editText4).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int spancount = Integer.parseInt(editText4.getText().toString());
                        if (spancount <= 0) spancount = 3;
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, spancount));
                        recyclerView.setAdapter(new Adapter());
                        fzbIEventemDecoration dv1 = new fzbIEventemDecoration(MainActivity.this, spancount);
                        dv1.setmDivider_V(getResources().getDrawable(R.drawable.divideritemdecoration_v));
                        dv1.setmDivider_H(getResources().getDrawable(R.drawable.divideritemdecoration_h));
                        recyclerView.addItemDecoration(dv1);
                        recyclerView.addItemDecoration(dv1);
                        layout.removeAllViews();
                        layout.addView(recyclerView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    }
                }).show();
                break;

        }

        layout.addView(recyclerView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        return super.onOptionsItemSelected(item);
    }


    public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycleitem, viewGroup, false)) {
                @Override
                public String toString() {
                    return super.toString();
                }
            };
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 17;
        }
    }
}
