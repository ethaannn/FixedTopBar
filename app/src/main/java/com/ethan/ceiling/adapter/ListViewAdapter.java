package com.ethan.ceiling.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.ethan.ceiling.R;

import java.util.List;

/**
 * Created by lihongxin on 2019/1/21
 */
public class ListViewAdapter extends BaseAdapter {
    private List<String> datas;

    public ListViewAdapter(List<String> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mall_shop_detail, null);
        TextView tvIndex = view.findViewById(R.id.tv_shop_name);
        tvIndex.setText(datas.get(position));
        return view;

    }
}
