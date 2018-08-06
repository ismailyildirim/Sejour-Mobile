package com.example.ismai.projectbase.RestApi;

import com.example.ismai.projectbase.Models.mdlLoginRequest;
import com.example.ismai.projectbase.Models.mdlSyncRequest;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RestApi {

    @POST(Url.login)
    @Headers({"content-type: application/json", "token-required: 0"})
    Call<JsonElement> Login(@Body mdlLoginRequest body);

    @POST(Url.SynchronizeHotel)
    @Headers({"content-type: application/json", "token-required: 0"})
    Call<JsonElement> SynchronizeHotel(@Body mdlSyncRequest body);

    @POST(Url.SynchronizeTour)
    @Headers({"content-type: application/json", "token-required: 0"})
    Call<JsonElement> SynchronizeTour(@Body mdlSyncRequest body);

}
