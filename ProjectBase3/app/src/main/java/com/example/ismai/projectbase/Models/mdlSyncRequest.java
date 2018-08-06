package com.example.ismai.projectbase.Models;

public class mdlSyncRequest {
    private String Hotel;
    private int GuideID;
    private int TerminalID;


    public mdlSyncRequest(String hotel, int guideID) {
        this.TerminalID = 0;
        this.Hotel = hotel;
        this.GuideID = guideID;
    }
}