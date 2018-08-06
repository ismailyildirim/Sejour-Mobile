package com.example.ismai.projectbase.Models;

public class mdlLoginRequest{
    private String MobUser;
    private String MobPass;

    public mdlLoginRequest(String mobUser, String mobPass) {
        MobUser = mobUser;
        MobPass = mobPass;
    }

    public String getMobUser() {
        return MobUser;
    }

    public void setMobUser(String mobUser) {
        MobUser = mobUser;
    }

    public String getMobPass() {
        return MobPass;
    }

    public void setMobPass(String mobPass) {
        MobPass = mobPass;
    }
}
