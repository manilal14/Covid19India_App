package com.mani.covid_19.home;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mani.covid_19.ApiInterface;
import com.mani.covid_19.CommonFuntions;
import com.mani.covid_19.R;
import com.mani.covid_19.RetrofitClientInstance;

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

public class HomeFragment extends Fragment {
    
    private final String TAG = this.getClass().getSimpleName();
    private View mRootView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "HomeFragment");
        mRootView = inflater.inflate(R.layout.fragment_home, container, false);
        mSwipeRefreshLayout = mRootView.findViewById(R.id.swipe_to_refresh);

        clickListeners();

        Bundle bundle = getArguments();

        if(bundle==null) {
            Log.e(TAG, "bundle is null");
            fetchDistrictWiseList();
        }
        else {
            List<State> stateList = (List<State>) getArguments().getSerializable("stateList");
            State wholeIndia = (State) getArguments().getSerializable("wholeIndia");
            setTotalCasesAndStartAdapter(stateList, wholeIndia);
        }

        return mRootView;
    }

    private void clickListeners() {
        CommonFuntions.setBanner(mRootView);
        mSwipeRefreshLayout.setOnRefreshListener(() -> fetchDistrictWiseList());
        CommonFuntions.setupBottomLayoutClicks(getActivity(),mRootView);

    }






    /*
    * fetch district list for states
    * make pair with corresponding state i.e state name is first and second is list of their district
    * now fetch state detail
    * search for district list from pair according to first i.e state name and add second i.e district list to state
    * make state list which will contain district list in corresponding states
    */

    private void fetchDistrictWiseList(){

        Log.e(TAG,"called : fetchDistrictWiseList");
        mSwipeRefreshLayout.setRefreshing(true);


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
                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
                    Log.e(TAG,e.toString());
                }

                fetchStateWiseList(stateDistrictPairList);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                Log.e(TAG,t.toString());
            }
        });
    }

    private void fetchStateWiseList(List<Pair<String, List<District>>> stateDistrictList) {

        Log.e(TAG,"called : fetchStateWiseList");
        //mSwipeRefreshLayout.setRefreshing(true);

        List<State> mStateList = new ArrayList<>();
        final State[] wholeIndia = new State[1];

        Call<String> call = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class).getStateWiseList();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                mSwipeRefreshLayout.setRefreshing(false);
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

                    setTotalCasesAndStartAdapter(mStateList,wholeIndia[0]);



                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, e.toString());
                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                Log.e(TAG,t.getMessage());
                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void setLastUpdatedTextView(String updateAt) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(updateAt);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e(TAG,e.toString());
        }

        Log.e(TAG, date.toString());

        String formattedDate = date.toString();

        String day   = formattedDate.substring(8,10);
        String month = formattedDate.substring(4,7);
        String time  = formattedDate.substring(11,16);

        long diffInSec = (System.currentTimeMillis() - date.getTime()) / 1000L;
        int hrs = (int) (diffInSec/(60*60));
        int min = ((int) (diffInSec/(60)))%60;

        String dateTimeToShow = "Last update\n";
        if(hrs!=0)
            dateTimeToShow += hrs+" Hrs ";
        if(min!=0)
            dateTimeToShow += min +" Minutes Ago\n";
        else dateTimeToShow += " Ago\n";

        dateTimeToShow += day+" "+month+", "+time+" IST";


        ((TextView)mRootView.findViewById(R.id.last_updated_at)).setText(dateTimeToShow);

    }

    private void setTotalCasesAndStartAdapter(List<State> stateList, State wholeIndia) {

        setTotalCases(wholeIndia);

        Log.e(TAG,"called : startAdapter");

        RecyclerView recyclerView = mRootView.findViewById(R.id.recycler_view_states);
        StateAdapter adapter = new StateAdapter(getActivity(),stateList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        ((TextView)mRootView.findViewById(R.id.states_affected)).setText(stateList.size()+" states/uts affected");

    }



    private void setTotalCases(State wholeIndia) {

        TextView t_conf        = mRootView.findViewById(R.id.total_conf);
        TextView t_active      = mRootView.findViewById(R.id.total_active);
        TextView t_recovered  = mRootView.findViewById(R.id.total_recovered);
        TextView t_death      = mRootView.findViewById(R.id.total_death);

        //startCountAnimation(wholeIndia.getConfired(),t_conf);
        //startCountAnimation(wholeIndia.getActive(),t_active);
        startCountAnimation(wholeIndia.getRecovered(),t_recovered);
        //startCountAnimation(wholeIndia.getD_deaths(),t_death);

        t_conf.setText(wholeIndia.getConfired());
        t_active.setText(wholeIndia.getActive());
        //t_recovered.setText(wholeIndia.getRecovered());
        t_death.setText(wholeIndia.getDeaths());

        ((TextView)mRootView.findViewById(R.id.d_conf)).setText("[+"+wholeIndia.getD_conf()+"]");
        ((TextView)mRootView.findViewById(R.id.d_active)).setText("");
        ((TextView)mRootView.findViewById(R.id.d_recovered)).setText("[+"+wholeIndia.getD_recovered()+"]");
        ((TextView)mRootView.findViewById(R.id.d_death)).setText("[+"+wholeIndia.getD_deaths()+"]");

        setLastUpdatedTextView(wholeIndia.getLastUpdateAt());
    }

    private void startCountAnimation(String num, TextView view) {
        ValueAnimator animator = ValueAnimator.ofInt(0, Integer.parseInt(num));
        animator.setDuration(2000);
        animator.addUpdateListener(animation ->
                view.setText(animation.getAnimatedValue().toString()));
        animator.start();
    }
}