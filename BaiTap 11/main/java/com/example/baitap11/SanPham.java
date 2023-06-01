package com.example.baitap11;

public class SanPham {
    private String ma,ten,soluong;

    public SanPham(){

    }
    public SanPham(String ma, String ten, String soluong) {
        this.ma = ma;
        this.ten = ten;
        this.soluong = soluong;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    @Override
    public String toString() {
        return
                "MaSP: " + ma  +
                "\nTen " + ten +
                "\nSo luong '" + soluong;
    }
}
