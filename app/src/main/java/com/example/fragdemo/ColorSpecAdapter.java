package com.example.fragdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.fragdemo.databinding.LayoutColoritemBinding;

import java.util.List;

public class ColorSpecAdapter extends BaseAdapter {

    List<ColorSpec> adapterColors;
    LayoutColoritemBinding coloritemBinding;

    public ColorSpecAdapter(List<ColorSpec> adapterColors) {
        this.adapterColors = adapterColors;
    }

    @Override
    public int getCount() {
        return adapterColors.size();
    }

    @Override
    public Object getItem(int position) {
        return adapterColors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            coloritemBinding = LayoutColoritemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        }
        coloritemBinding.txtViewColorItem.setText(adapterColors.get(position).getColorDesc());
        coloritemBinding.txtViewColorItem.setTextColor(adapterColors.get(position).getColorVal());
        return coloritemBinding.getRoot();
    }
}
