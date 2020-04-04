package com.mani.covid_19.ui.helful_links;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mani.covid_19.R;

import java.util.List;

public class LinksAdapter extends RecyclerView.Adapter<LinksAdapter.LinksViewHolder> {

    private Context mCtx;
    private List<Links> mLinkList;

    public LinksAdapter(Context mCtx, List<Links> mLinkList) {
        this.mCtx = mCtx;
        this.mLinkList = mLinkList;
    }

    @NonNull
    @Override
    public LinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinksViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.links_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinksViewHolder holder, int position) {

        Links link = mLinkList.get(position);

        holder.tv_title.setText(link.getTitle());
        holder.tv_link.setText(link.getLink());

        holder.tv_link.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.getLink()));
            mCtx.startActivity(browserIntent);

        });
    }

    @Override
    public int getItemCount() {
        return mLinkList.size();
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
