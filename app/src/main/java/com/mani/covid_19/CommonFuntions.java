package com.mani.covid_19;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class CommonFuntions {

    public static void setupBottomLayoutClicks(Context ctx, View view) {

        final String GITHUB_SOURCE_CODE_LINK = "https://github.com/covid19india/covid19india-react";
        final String DATABASE_LINK = "https://docs.google.com/spreadsheets/d/e/2PACX-1vSc_2y5N0I67wDU38DjDh35IZSIS30rQf7_NYZhtYYGU1jJYT6_kDx4YpF-qw0LSlGsBYP8pqM_a1Pd/pubhtml";
        final String TWITTER_LINK  = "https://twitter.com/covid19indiaorg";
        final String TELEGRAM_LINK = "https://telegra.ph/CoVID-19--India-Ops-03-24";
        final String MEKVAHAN_LINK = "http://bit.ly/mekvahan";
        final String COVID19LINK   = "https://github.com/covid19india";


        view.findViewById(R.id.ll_github).setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_SOURCE_CODE_LINK));
            ctx.startActivity(browserIntent);

        });

        view.findViewById(R.id.ll_db).setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(DATABASE_LINK));
            ctx.startActivity(browserIntent);

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

        view.findViewById(R.id.text1).setOnClickListener(v -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(MEKVAHAN_LINK));
                ctx.startActivity(browserIntent);
        });

        view.findViewById(R.id.text2).setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(COVID19LINK));
            ctx.startActivity(browserIntent);
        });



    }


}
