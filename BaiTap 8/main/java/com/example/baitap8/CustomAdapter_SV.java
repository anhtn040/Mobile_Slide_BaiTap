package com.example.baitap8;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter_SV extends ArrayAdapter<SinhVien> {
    Context context;
    int resource;
    ArrayList<SinhVien> data;
    ArrayList<SinhVien> data_temp = new ArrayList<>();

    public CustomAdapter_SV(@NonNull Context context, int resource, @NonNull ArrayList<SinhVien> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
        data_temp.addAll(data);

    }

    public void TimKiem(String msg) {
        data.clear();
        if (msg=="")
            data.addAll(data_temp);
        else
            for (SinhVien sv:data_temp){
                if (sv.getTenSV().contains(msg))
                    data.add(sv);
            }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        viewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource,null);
            viewHolder = new viewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder= (viewHolder) convertView.getTag();

        SinhVien sv = data.get(position);
        viewHolder.tvHoTen.setText(sv.getTenSV());
        if (sv.getGioiTinh())
            viewHolder.ivGioiTinh.setImageResource(R.drawable.baseline_boy_24);
        else
            viewHolder.ivGioiTinh.setImageResource(R.drawable.baseline_girl_24);
        viewHolder.cbChon.setChecked(sv.getChon());
        viewHolder.cbChon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                sv.setChon(b);
            }
        });

        viewHolder.ivDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MainDetail.class);
                intent.putExtra("msg",sv);
                context.startActivity(intent);
            }
        });
        viewHolder.ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0123456789"));
                context.startActivity(intent);
            }
        });
        viewHolder.ivMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:0123456789"));
                intent.putExtra("sms_bodyy","Thông Báo");
                context.startActivity(intent);
            }
        });

        return convertView;
    }
    public void ChonHet(){ 
        for (SinhVien sv: data){
            sv.setChon(true);
        }
        notifyDataSetChanged();
    }
    public void BoChon(){
        for (SinhVien sv: data){
            sv.setChon(false);
        }
        notifyDataSetChanged();
    }
    public void XoaDS(){
        ArrayList<SinhVien> data_temp = new ArrayList<>();
        for (SinhVien sv: data){
            if (sv.getChon()==false)
                data_temp.add(sv);
        }
        data.clear();
        data.addAll(data_temp);
        notifyDataSetChanged();
    }

    private class viewHolder{
        TextView tvHoTen;
        ImageView ivGioiTinh;
        ImageView ivDetail, ivCall, ivMessage;
        CheckBox cbChon;
        public viewHolder(View view) {
            tvHoTen = view.findViewById(R.id.tvHoten);
            ivGioiTinh = view.findViewById(R.id.ivGioiTinh);
            cbChon = view.findViewById(R.id.cbChon);
            ivDetail = view.findViewById(R.id.ivDetail);
            ivCall = view.findViewById(R.id.ivCall);
            ivMessage = view.findViewById(R.id.ivMessage);
        }

    }
}
