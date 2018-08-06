package com.example.ismai.projectbase.RestApi;

import com.example.ismai.projectbase.Models.mdlLoginRequest;
import com.example.ismai.projectbase.Models.mdlSyncRequest;
import com.google.gson.JsonElement;

import retrofit2.Call;

public class ManagerAll extends BaseManager {
    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getOurInstance() {
        return ourInstance;
    }

    public Call<JsonElement> giris(String mobUser, String mobPass) {
        mdlLoginRequest loginRequest = new mdlLoginRequest(mobUser, mobPass);

        Call<JsonElement> call = getRestApiClient().Login(loginRequest);
        return call;
    }

    public Call<JsonElement> listeleHotel(String hotel, int logID) {
        mdlSyncRequest syncRequest = new mdlSyncRequest(hotel, logID);
        Call<JsonElement> call = getRestApiClient().SynchronizeHotel(syncRequest);
        return call;
    }

    public Call<JsonElement> listeleTour(String hotel, int logID) {
        mdlSyncRequest syncRequest2 = new mdlSyncRequest(hotel, logID);
        Call<JsonElement> call = getRestApiClient().SynchronizeTour(syncRequest2);
        return call;
    }


}
