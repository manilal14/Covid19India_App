package com.mani.covid_19;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.mani.covid_19.home.District;
import com.mani.covid_19.home.State;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mProgressBar = findViewById(R.id.horizontal_progress_bar);

        fetchDistrictWiseList();
    }



    private void fetchDistrictWiseList(){

        Log.e(TAG,"called : fetchDistrictWiseList");
        mProgressBar.setVisibility(View.VISIBLE);

        Call<String> call = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class).getDistrictWiseList();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                List< Pair<String, List<District>>> stateDistrictPairList = new ArrayList<>();

                try {
                    JSONArray array = new JSONArray(response.body());

                    for(int i=0;i<array.length();i++){

                        JSONObject obj = array.getJSONObject(i);
                        String state = obj.getString("state");
                        JSONArray districtData = obj.getJSONArray("districtData");

                        List<District> districtList = new ArrayList<>();
                        for(int j=0;j<districtData.length();j++){

                            JSONObject disObj = districtData.getJSONObject(j);

                            String disName = disObj.getString("district");
                            String  conf   = disObj.getString("confirmed");
                            String d_conf  = disObj.getJSONObject("delta").getString("confirmed");

                            districtList.add(new District(disName, conf, d_conf));
                        }

                        stateDistrictPairList.add(new Pair(state,districtList));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    mProgressBar.setVisibility(View.GONE);
                    Log.e(TAG,e.toString());
                    Toast.makeText(SplashScreen.this, e.toString(),Toast.LENGTH_LONG).show();
                }

                fetchStateWiseList(stateDistrictPairList);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG,t.toString());
                mProgressBar.setVisibility(View.GONE);
                launchAlertBox(t.toString());
                //Toast.makeText(SplashScreen.this, t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void launchAlertBox(String message) {

        AlertDialog alertDialog = new AlertDialog.Builder(SplashScreen.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Retry", (dialog, which) ->{
            fetchDistrictWiseList();
            dialog.dismiss();
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Close App", (dialog, which) ->{
            dialog.dismiss();
            finish();
        });
        alertDialog.show();

    }

    private void fetchStateWiseList(List<Pair<String, List<District>>> stateDistrictList) {

        Log.e(TAG,"called : fetchStateWiseList");

        List<State> mStateList = new ArrayList<>();
        final State[] wholeIndia = new State[1];

        Call<String> call = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class).getStateWiseList();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e(TAG, response.body());

                mStateList.clear();

                String result = response.body();

                try {

                    JSONArray array = new JSONObject(result).getJSONArray("statewise");

                    for(int i=0;i<array.length();i++) {

                        JSONObject object = array.getJSONObject(i);

                        String name      = object.getString("state");
                        String active    = object.getString("active");
                        String conf      = object.getString("confirmed");
                        String deaths    = object.getString("deaths");
                        String recov     = object.getString("recovered");

                        String updateAt  = object.getString("lastupdatedtime");     // 03/04/2020 19:32:24

                        // String d_act       = object.getJSONObject("delta").getString("active");
                        String d_act = "";
                        String d_conf      = object.getString("deltaconfirmed");
                        String d_deaths    = object.getString("deltadeaths");
                        String d_recov     = object.getString("deltarecovered");


                        if(conf.equals("0"))
                            continue;

                        if(i!=0){
                            List<District> districtList = new ArrayList<>();
                            for(int j=0;j<stateDistrictList.size();j++){
                                if(stateDistrictList.get(j).first.equals(name)){
                                    districtList = stateDistrictList.get(j).second;
                                    break;
                                }
                            }
                            mStateList.add(new State(name, active,conf,deaths,recov,updateAt, d_act, d_conf,d_deaths,d_recov,districtList));
                        }
                        else{
                            wholeIndia[0] = new State(name, active,conf,deaths,recov,updateAt, d_act, d_conf,d_deaths,d_recov,null);
                        }
                    }


                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    i.putExtra(AppConstants.STATE_LIST, (Serializable) mStateList);
                    i.putExtra(AppConstants.WHOLE_INDIA, wholeIndia[0]);

                    startActivity(i);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                    mProgressBar.setVisibility(View.GONE);
                    Log.e(TAG, e.toString());
                    Toast.makeText(SplashScreen.this, e.toString(),Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Log.e(TAG,t.getMessage());
                launchAlertBox(t.toString());
                //Toast.makeText(SplashScreen.this, t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }



}
