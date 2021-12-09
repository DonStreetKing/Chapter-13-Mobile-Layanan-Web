package com.example.chapter13;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<listitem> {
    private List<listitem> ProductItemList;
    private Context context;

    public ListViewAdapter(List<listitem> productItemList, Context context) {
        super(context, R.layout.list_item, productItemList);
        this.ProductItemList = productItemList;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View listViewItem = inflater.inflate(R.layout.list_item, null, true);

        TextView textVIewID = listViewItem.findViewById(R.id.textviewID);
        TextView textViewNamaItem = listViewItem.findViewById(R.id.textviewNamaItem);
        TextView textViewHarga = listViewItem.findViewById(R.id.textviewHarga);

        listitem listItem = ProductItemList.get(position);

        textVIewID.setText(listItem.getID());
        textViewNamaItem.setText(listItem.getNamaItem());
        textViewHarga.setText(listItem.getNamaItem());
        return listViewItem;
    }
}
