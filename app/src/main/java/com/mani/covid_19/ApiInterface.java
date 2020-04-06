package com.mani.covid_19;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/data.json")
    Call<String> getStateWiseList();


    @GET("/v2/state_district_wise.json")
    Call<String> getDistrictWiseList();
}
