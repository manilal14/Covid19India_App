package com.mani.covid_19.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mani.covid_19.R;

import java.util.List;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.StateViewHolder> {

    private Context mCtx;
    private List<District> mDistrictList;

    public DistrictAdapter(Context mCtx, List<District> mDistrictList) {
        this.mCtx = mCtx;
        this.mDistrictList = mDistrictList;
    }

    @NonNull
    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StateViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.single_district_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StateViewHolder holder, int pos) {

        District district = mDistrictList.get(pos);

        setBackgroundColor(holder, pos);

        holder.tv_district.setText(district.getName());

        String conf = district.getConf();
        String d_conf = district.getD_conf();

        Log.e("check","df="+d_conf);

        //d_conf = "14";

        if(conf.equals("0")) conf = "-";
        holder.tv_conf.setText(conf);



        if(!d_conf.equals("0"))
            holder.tv_d_conf.setText("+"+d_conf);


    }

    private void setBackgroundColor(StateViewHolder holder, int pos) {
        if(pos%2==0){
            holder.tv_district.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white));
            holder.tv_conf.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white));
            holder.ll_confirm.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white));
        }
        else{
            holder.tv_district.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white_95));
            holder.tv_conf.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white_95));
            holder.ll_confirm.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white_95));
        }
    }

    @Override
    public int getItemCount() {
        return mDistrictList.size();
    }

    public class StateViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_district, tv_conf;
        private TextView tv_d_conf;
        private LinearLayout ll_confirm;


        public StateViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_district = itemView.findViewById(R.id.tv_district);
            tv_conf  = itemView.findViewById(R.id.tv_confirmed);
            tv_d_conf = itemView.findViewById(R.id.d_confirmed);

            ll_confirm = itemView.findViewById(R.id.ll_confirm);

        }
    }
}
