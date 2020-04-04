package com.mani.covid_19.home;

import android.content.Context;
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

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.StateViewHolder> {

    private Context mCtx;
    private List<State> mStateList;

    public StateAdapter(Context mCtx, List<State> mStateList) {
        this.mCtx = mCtx;
        this.mStateList = mStateList;
    }

    @NonNull
    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StateViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.r_v_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StateViewHolder holder, int pos) {

        State state = mStateList.get(pos);

        setBackgroundColor(holder, pos);

        holder.tv_state.setText(state.getName());

        String conf = state.getConfired();
        String act = state.getActive();
        String rec = state.getRecovered();
        String death = state.getDeaths();
        String d_conf = state.getD_conf();

        if(conf.equals("0")) conf = "-";
        if(act.equals("0")) act = "-";
        if(rec.equals("0")) rec = "-";
        if(death.equals("0")) death = "-";


        holder.tv_conf.setText(conf);
        holder.tv_act.setText(act);
        holder.tv_rec.setText(rec);
        holder.tv_death.setText(death);

        if(!d_conf.equals("0"))
            holder.tv_d_conf.setText("+"+d_conf);

    }

    private void setBackgroundColor(StateViewHolder holder, int pos) {
        if(pos%2==0){
            holder.tv_state.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white));
            holder.tv_conf.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white));
            holder.tv_act.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white));
            holder.tv_rec.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white));
            holder.tv_death.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white));

            holder.ll_confirm.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white));
        }
        else{
            holder.tv_state.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white_95));
            holder.tv_conf.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white_95));
            holder.tv_act.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white_95));
            holder.tv_rec.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white_95));
            holder.tv_death.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white_95));

            holder.ll_confirm.setBackgroundColor(ContextCompat.getColor(mCtx,R.color.white_95));
        }
    }

    @Override
    public int getItemCount() {
        return mStateList.size();
    }

    public class StateViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_state, tv_conf, tv_act, tv_rec, tv_death;
        private TextView tv_d_conf;
        private LinearLayout ll_confirm;
        public StateViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_state = itemView.findViewById(R.id.tv_state);
            tv_conf  = itemView.findViewById(R.id.tv_confirmed);
            tv_act   = itemView.findViewById(R.id.tv_active);
            tv_rec   = itemView.findViewById(R.id.tv_recovered);
            tv_death = itemView.findViewById(R.id.tv_death);
            tv_d_conf = itemView.findViewById(R.id.d_confirmed);

            ll_confirm = itemView.findViewById(R.id.ll_confirm);
        }
    }
}
