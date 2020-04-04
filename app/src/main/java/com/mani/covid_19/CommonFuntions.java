package com.mani.covid_19;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class CommonFuntions {

    public static void setupBottomLayoutClicks(Context ctx, View view) {

        final String GITHUB_SOURCE_CODE_LINK = "https://github.com/covid19india/covid19india-react";
        final String DATABASE_LINK = "";
        final String TWITTER_LINK = "https://twitter.com/covid19indiaorg";
        final String TELEGRAM_LINK = "https://telegra.ph/CoVID-19--India-Ops-03-24";

        view.findViewById(R.id.ll_github).setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_SOURCE_CODE_LINK));
            ctx.startActivity(browserIntent);

        });

        view.findViewById(R.id.ll_db).setOnClickListener(v -> {

        });

        view.findViewById(R.id.ll_twitter).setOnClickListener(v -> {
            Intent twitterIntent = new Intent(Intent.ACTION_VIEW);
            twitterIntent.setData(Uri.parse(TWITTER_LINK));
            ctx.startActivity(twitterIntent);
        });

        view.findViewById(R.id.ll_telegram).setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            browserIntent.setData(Uri.parse(TELEGRAM_LINK));
            ctx.startActivity(browserIntent);
        });


    }
}
