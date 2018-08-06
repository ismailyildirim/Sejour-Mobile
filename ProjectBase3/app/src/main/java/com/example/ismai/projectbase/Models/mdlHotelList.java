package com.example.ismai.projectbase.Models;

public class mdlHotelList {
    private String turop;
    private String adi;
    private String yasi;
    private String otel;
    private String unvan;
    private String cikisTarihi;
    private String girisTarihi;
    private String voucher;
    private int musNo;
    private boolean selected;

    public mdlHotelList(String unvan, String adi, String yasi, String turop, int musNo) {
        this.adi = adi;
        this.yasi = yasi;
        this.unvan = unvan;
        this.musNo = musNo;
        this.turop = turop;
    }

    public mdlHotelList(String otel, String girisTarihi) {
        this.otel = otel;
        this.girisTarihi = girisTarihi;

    }

    public void setTurop(String turop) {
        this.turop = turop;
    }

    public String getTurop() {
        return turop;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getAdi() {
        return adi;
    }

    public void setYasi(String yasi) {
        this.yasi = yasi;
    }

    public String getYasi() {
        return yasi;
    }

    public void setOtel(String otel) {
        this.otel = otel;
    }

    public String getOtel() {
        return otel;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setCikisTarihi(String cikisTarihi) {
        this.cikisTarihi = cikisTarihi;
    }

    public String getCikisTarihi() {
        return cikisTarihi;
    }

    public void setGirisTarihi(String girisTarihi) {
        this.girisTarihi = girisTarihi;
    }

    public String getGirisTarihi() {
        return girisTarihi;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setMusNo(int musNo) {
        this.musNo = musNo;
    }

    public int getMusNo() {
        return musNo;
    }

    @Override
    public String toString() {
        return
                "MdlTourView{" +
                        "turop = '" + turop + '\'' +
                        ",adi = '" + adi + '\'' +
                        ",yasi = '" + yasi + '\'' +
                        ",otel = '" + otel + '\'' +
                        ",unvan = '" + unvan + '\'' +
                        ",cikisTarihi = '" + cikisTarihi + '\'' +
                        ",girisTarihi = '" + girisTarihi + '\'' +
                        ",voucher = '" + voucher + '\'' +
                        ",musNo = '" + musNo + '\'' +
                        "}";
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}
