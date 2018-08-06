package com.example.ismai.projectbase.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.ismai.projectbase.Models.mdlHotelList;
import com.example.ismai.projectbase.R;

import java.util.List;


public class adpHotelRecylerView extends RecyclerView.Adapter<adpHotelRecylerView.tanımla> {
    Context context;
    List<mdlHotelList> list;

    public adpHotelRecylerView(Context context, List<mdlHotelList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public tanımla onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layouthotel, parent, false);
        return new tanımla(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final tanımla holder, final int position) {
        holder.üst.setText(list.get(position).getUnvan() + " " + list.get(position).getAdi() + "(" + list.get(position).getYasi() + ")");
        holder.alt.setText(list.get(position).getTurop() + " - " + list.get(position).getMusNo());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(position).setSelected(isChecked);
            }
        });
        holder.checkBox.setChecked(list.get(position).isSelected());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class tanımla extends RecyclerView.ViewHolder {
        TextView üst;
        TextView alt;
        CheckBox checkBox;

        public tanımla(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()) {
                        checkBox.setChecked(false);
                    } else {
                        checkBox.setChecked(true);
                    }
                }
            });

            checkBox = view.findViewById(R.id.checkBox);
            üst = (TextView) view.findViewById(R.id.textview_ust);
            alt = (TextView) view.findViewById(R.id.textview_alt);


        }
    }

}