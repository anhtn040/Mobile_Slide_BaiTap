package com.example.baitap8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainDetail extends AppCompatActivity {
    EditText edtMaSV, edtTen, edtNgaySinh;
    ImageView ivGioiTinh;
    Button btnSua, btnThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setControl();
        setEvent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.mnThongBao:
                Toast.makeText(this, "Thông Báo", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sinhvien,menu);

        return true;
    }

    private void setEvent() {
        SinhVien sv = (SinhVien) getIntent().getSerializableExtra("msg");
        edtMaSV.setText(sv.getMaSV());
        edtTen.setText(sv.getTenSV());
        edtNgaySinh.setText(sv.getNgaySinh());
        if (sv.getGioiTinh())
            ivGioiTinh.setImageResource(R.drawable.baseline_boy_24);
        else
            ivGioiTinh.setImageResource(R.drawable.baseline_girl_24);
    }

    private void setControl() {
        edtMaSV =findViewById(R.id.edtMaSV);
        edtTen =findViewById(R.id.edtTen);
        edtNgaySinh =findViewById(R.id.edtNgaySinh);
        btnSua=findViewById(R.id.btnSua);
        btnThoat=findViewById(R.id.btnThoat);
        ivGioiTinh = findViewById(R.id.ivGioiTinh);
    }
}