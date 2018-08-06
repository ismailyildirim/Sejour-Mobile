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

import com.example.ismai.projectbase.Models.mdlTourList;
import com.example.ismai.projectbase.R;

import java.util.List;

public class adpTourRecylerView extends RecyclerView.Adapter<adpTourRecylerView.tanımla> {

    Context context;
    List<mdlTourList> list;

    public adpTourRecylerView(Context context, List<mdlTourList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public tanımla onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layouttour, parent, false);
        tanımla holder = new tanımla(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final adpTourRecylerView.tanımla holder, final int position) {
        //bolge,pax,tarih,tur;

        holder.üst.setText(list.get(position).getTur());
        holder.alt.setText(list.get(position).getBolge() + " - " + list.get(position).getTarih());
        holder.pax.setText("Pax: " + list.get(position).getPax());
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
        TextView pax;
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
            pax = (TextView) view.findViewById(R.id.textview_pax);


        }
    }
}
