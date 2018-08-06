package com.example.ismai.projectbase.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.ismai.projectbase.Adapter.adpHotelRecylerView;
import com.example.ismai.projectbase.Adapter.adpTourRecylerView;
import com.example.ismai.projectbase.Models.mdlHotelList;
import com.example.ismai.projectbase.Models.mdlTourList;
import com.example.ismai.projectbase.RestApi.ManagerAll;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilsRetrofit {
    private static UtilsRetrofit ourInstance = new UtilsRetrofit();

    public static synchronized UtilsRetrofit getOurInstance() {
        return ourInstance;
    }

    adpHotelRecylerView adpHotel;
    List<mdlHotelList> listHotel;
    adpTourRecylerView adpTour;
    List<mdlTourList> listTour;
    String cinsiyet, isim, yas, turop;
    String bolge, pax, tarih, tur;
    int musteriNo;

    public void istekHotel(int logid, final RecyclerView recyclerViewHotel, final Context context) {

        Call<JsonElement> call = ManagerAll.getOurInstance().listeleHotel("", logid);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                String data = response.body().toString();
                try {

                    listHotel = new ArrayList<>();
                    JSONObject obj = new JSONObject(data);
                    String table = obj.getJSONObject("Data").getString("Table");
                    JSONArray jsonArray = new JSONArray(table);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        isim = jsonObject.getString("Adi");
                        cinsiyet = jsonObject.getString("Unvan");
                        yas = (jsonObject.getString("Yasi").toString());

                        turop = jsonObject.getString("Turop");

                        musteriNo = Integer.parseInt(jsonObject.getString("MusNo").toString());
                        mdlHotelList modelHotel = new mdlHotelList(cinsiyet, isim, yas, turop, musteriNo);

                        listHotel.add(modelHotel);

                    }
                    adpHotel = new adpHotelRecylerView(context, listHotel);
                    recyclerViewHotel.setAdapter(adpHotel);
                    Main2Activity.loadingBar.cancel();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }

    public void istekTour(int logid, final RecyclerView recyclerViewTour, final Context context) {

        Call<JsonElement> call = ManagerAll.getOurInstance().listeleTour("", logid);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                response.body();
                String data = response.body().toString();
                try {
                    listTour = new ArrayList<>();
                    JSONObject obj = new JSONObject(data);
                    String table = obj.getJSONObject("Data").getString("Table");
                    JSONArray jsonArray = new JSONArray(table);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        bolge = jsonObject.getString("Bolge");
                        pax = jsonObject.getString("Pax");
                        tarih = jsonObject.getString("Tarih");
                        tarih = tarihDegistir(tarih);
                        tur = jsonObject.getString("Tur");
                        mdlTourList modelTour = new mdlTourList(bolge, pax, tarih, tur);
                        listTour.add(modelTour);

                    }
                    adpTour = new adpTourRecylerView(context, listTour);
                    recyclerViewTour.setAdapter(adpTour);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }

    public String tarihDegistir(String tarih) throws ParseException {
        final String OLD_FORMAT = "yyyy-MM-dd";
        final String NEW_FORMAT = "dd/MM/yyyy";


        String oldDateString = tarih;
        String newDateString;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(oldDateString);
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);

        return newDateString;
    }

    public void istek(final String username, String password, final Context context) {

        Call<JsonElement> call = ManagerAll.getOurInstance().giris(username, password);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                String data = response.body().toString();
                try {
                    JSONObject obj = new JSONObject(data);
                    String table = obj.getJSONObject("Data").getString("Table");
                    JSONArray jsonArray = new JSONArray(table);


                    if (jsonArray.length() == 1) {
                        //log id yi aratmak için yeni JSON objesi olusturdum.
                        JSONObject obj2 = jsonArray.getJSONObject(0);

                        String logID = obj2.getString("LogID");

                        Intent intent = new Intent(context, Main2Activity.class);
                        intent.putExtra("logid", logID.toString());
                        intent.putExtra("username", username);
                        context.startActivity(intent);
                        MainActivity.loadingBar.cancel();
                    } else {
                        MainActivity.loadingBar.cancel();
                        Toast errorToast = Toast.makeText(context, "Kullanıcı adı veya sifre yanliş", Toast.LENGTH_SHORT);
                        errorToast.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }
}
