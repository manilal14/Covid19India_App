package com.mani.covid_19.faq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mani.covid_19.R;

import java.util.List;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.LinksViewHolder> {

    private Context mCtx;
    private List<Faq> mFaqList;

    public FaqAdapter(Context mCtx, List<Faq> mFaqList) {
        this.mCtx = mCtx;
        this.mFaqList = mFaqList;
    }

    @NonNull
    @Override
    public LinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinksViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.single_links_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinksViewHolder holder, int position) {

        Faq faq = mFaqList.get(position);

        holder.tv_title.setText(faq.getQuestion());
        holder.tv_link.setText(faq.getAnswer());
    }

    @Override
    public int getItemCount() {
        return mFaqList.size();
    }

    public class LinksViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_link;
        public LinksViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.title);
            tv_link  = itemView.findViewById(R.id.link);
        }
    }
}
