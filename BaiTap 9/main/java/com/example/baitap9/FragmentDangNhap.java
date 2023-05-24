package com.example.baitap9;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentDangNhap extends Fragment {
    EditText edtUsername,edtPassword;
    Button btnDN;
    ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangnhap, container, false);
        setControl(view);
        setEvent();

        return view;
    }

    private void xuLyDangNhap() {
        progressBar.setVisibility(View.VISIBLE);
        CountDownTimer countDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int value = progressBar.getProgress()+10;
                progressBar.setProgress(value);
            }

            @Override
            public void onFinish() {
                if (edtUsername.getText().toString().equals("admin") && edtPassword.getText().toString().equals("123"))
                    Toast.makeText(getActivity(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }.start();
    }

    private void setEvent() {
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDangNhap();
            }
        });

    }

    private void setControl(View view) {
        edtUsername = view.findViewById(R.id.edtUsername);
        edtPassword = view.findViewById(R.id.edtPassword);
        btnDN = view.findViewById(R.id.btnDN);
        progressBar = view.findViewById(R.id.progressBar);

    }

}