package com.example.baitap8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtHoTen,edtMaSV, edtNgaySinh;
    RadioButton radNam, radNu;
    SearchView svTimSV;
    Button btnThem, btnXoa, btnSua, btnThoat, btnChonHet, btnBoChon, btnXoaDS;
    ListView lvDanhSach;
    ArrayList<SinhVien> data_SV = new ArrayList<>();
    CustomAdapter_SV adapter_SV;

    SinhVien sinhVien = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        khoitao();
        adapter_SV = new CustomAdapter_SV(this, R.layout.layout_sinhvien_item,data_SV);
        lvDanhSach.setAdapter(adapter_SV);
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sinhVien = data_SV.get(position);
                ChonSV();
            }
        });
        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                data_SV.remove(position);
                adapter_SV.notifyDataSetChanged();
                return true;
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemDL();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_SV.remove(sinhVien);
                adapter_SV.notifyDataSetChanged();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sinhVien.setMaSV(edtMaSV.getText().toString());
                sinhVien.setTenSV(edtHoTen.getText().toString());
                sinhVien.setNgaySinh(edtNgaySinh.getText().toString());
                sinhVien.setGioiTinh(radNam.isChecked());
                adapter_SV.notifyDataSetChanged();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        svTimSV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter_SV.TimKiem(newText);
                return false;
            }
        });

        btnChonHet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter_SV.ChonHet();
            }
        });
        btnBoChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter_SV.BoChon();
            }
        });
        btnXoaDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter_SV.XoaDS();
            }
        });

    }

    private void ChonSV() {
        edtMaSV.setText(sinhVien.getMaSV());
        edtHoTen.setText(sinhVien.getTenSV());
        edtNgaySinh.setText(sinhVien.getNgaySinh());
        if (sinhVien.getGioiTinh())
            radNam.setChecked(true);
        else
            radNu.setChecked(true);
    }

    private void ThemDL() {
        SinhVien sv = new SinhVien();
        sv.setTenSV(edtHoTen.getText().toString());
        sv.setMaSV(edtMaSV.getText().toString());
        sv.setNgaySinh(edtNgaySinh.getText().toString());
        sv.setGioiTinh(radNam.isChecked());
        data_SV.add(sv);
        adapter_SV.notifyDataSetChanged();

    }

    private void khoitao() {
        data_SV.add(new SinhVien("Sv001", "Nguyễn Văn A", "12/12/2000",true));
        data_SV.add(new SinhVien("Sv002", "Nguyễn Văn B", "12/12/2000",false));
        data_SV.add(new SinhVien("Sv003", "Nguyễn Văn C", "12/12/2000",true));
    }

    private void setControl() {
        lvDanhSach = findViewById(R.id.lvDanhSach);
        edtHoTen = findViewById(R.id.edtHoten);
        edtMaSV = findViewById(R.id.edtMaSV);
        edtNgaySinh = findViewById(R.id.edtNgaysinh);
        radNam = findViewById(R.id.radNam);
        radNu = findViewById(R.id.radNu);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnThoat = findViewById(R.id.btnThoat);
        btnChonHet = findViewById(R.id.btnChonHet);
        btnBoChon = findViewById(R.id.btnBoChon);
        btnXoaDS = findViewById(R.id.btnXoaDS);
        svTimSV = findViewById(R.id.svTimSV);



    }
}