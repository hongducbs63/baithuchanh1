package com.example.recycleviewdemo_crud.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.recycleviewdemo_crud.R;

public class SpinnerAdapter extends BaseAdapter {
    private int[] imgs = {R.drawable.anh_1, R.drawable.anh_2, R.drawable.anh_3,
                          R.drawable.anh_4, R.drawable.anh_5, R.drawable.anh_6};
    private Context context;

    public SpinnerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_image, viewGroup, false);
        ImageView img = item.findViewById(R.id.img);
        img.setImageResource(imgs[position]);
        return item;
    }
}
