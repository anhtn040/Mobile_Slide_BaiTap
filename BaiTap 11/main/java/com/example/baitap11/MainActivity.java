package com.example.baitap11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtMa, edtTen, edtSoLuong;
    Button btnThem, btnXoa, btnSua, btnHienThi;
    ListView lvDanhSach;
    ArrayList<SanPham> data_SP = new ArrayList<>();
    ArrayAdapter adapter_SP;
    DbSanPham dbSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
        HienThiDL();
    }

    private void setEvent() {
        dbSanPham = new DbSanPham(this);
        adapter_SP = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, data_SP);
        lvDanhSach.setAdapter(adapter_SP);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemDL();
            }
        });
        btnHienThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HienThiDL();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XoaDL();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SuaDL();
            }
        });
    }

    private void SuaDL() {
        SanPham sp = new SanPham();
        sp.setMa(edtMa.getText().toString());
        sp.setTen(edtTen.getText().toString());
        sp.setSoluong(edtSoLuong.getText().toString());
        dbSanPham.SuaDL(sp);
        HienThiDL();
    }

    private void XoaDL() {
        SanPham sp = new SanPham();
        sp.setMa(edtMa.getText().toString());
        dbSanPham.XoaDL(sp);
        edtMa.setText("");
        edtTen.setText("");
        edtSoLuong.setText("");
        HienThiDL();
    }

    private void HienThiDL() {
        data_SP.clear();
        data_SP.addAll(dbSanPham.HienThiDL());
        adapter_SP.notifyDataSetChanged();
    }

    private void ThemDL() {
        SanPham sp = new SanPham();
        sp.setMa(edtMa.getText().toString());
        sp.setTen(edtTen.getText().toString());
        sp.setSoluong(edtSoLuong.getText().toString());
        dbSanPham.ThemDL(sp);
        HienThiDL();
    }

    private void setControl() {
        edtMa = findViewById(R.id.edtMa);
        edtTen = findViewById(R.id.edtTen);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnHienThi = findViewById(R.id.btnHienthi);
        lvDanhSach = findViewById(R.id.lvDanhSach);
    }
}