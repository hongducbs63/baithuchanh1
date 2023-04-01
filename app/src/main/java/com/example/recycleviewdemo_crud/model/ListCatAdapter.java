package com.example.recycleviewdemo_crud.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.recycleviewdemo_crud.R;

import java.util.ArrayList;

public class ListCatAdapter extends BaseAdapter {
    private ArrayList<Cat> listCat;

    public ListCatAdapter(ArrayList<Cat> listCat) {
        this.listCat = listCat;
    }
//    private Context context;
//
//    public ListCatAdapter(ArrayList<Cat> context) {
//        this.context = context;
//    }

    @Override
    public int getCount() {
        return listCat.size();
    }

    @Override
    public Object getItem(int position) {
        return listCat.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        View item = LayoutInflater.from(context).inflate(R.layout.item_image, viewGroup, false);
//        ImageView img = item.findViewById(R.id.img);
//        img.setImageResource(imgs[position]);
//        return item;
        View item;
        if (view == null) {
            item = View.inflate(viewGroup.getContext(), R.layout.item, null);
        }
        else item = view;
        return item;
    }
}
