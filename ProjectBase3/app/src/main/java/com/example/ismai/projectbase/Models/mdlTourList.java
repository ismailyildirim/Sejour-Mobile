package com.example.ismai.projectbase.Models;

public class mdlTourList {

    private String bolge;
    private String pax;
    private String tarih;
    private String tur;
    private boolean selected;

    public mdlTourList(String bolge, String pax, String tarih, String tur) {
        this.bolge = bolge;
        this.pax = pax;
        this.tarih = tarih;
        this.tur = tur;
    }

    public String getBolge() {
        return bolge;
    }

    public void setBolge(String bolge) {
        this.bolge = bolge;
    }

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}
