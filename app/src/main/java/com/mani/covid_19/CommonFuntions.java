package com.mani.covid_19;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static void setBanner(View rootView) {

        List<String> bannerList = new ArrayList<>();
        bannerList.add("Wash your hands with soap and water.");
        bannerList.add("Your essential needs will be taken care by the government in an timely manner. Please do not hoard.");
        bannerList.add("Help out the elderly by bringing them their groceries and other essentials.");
        bannerList.add("Don't hoard groceries and essentials. Please ensure that people who are in need don't face a shortage because of you!");
        bannerList.add("Call up your loved ones during the lockdown.");
        bannerList.add("Help out your employees and domestic workers by not cutting their salaries. Show the true Indian spirit!");
        bannerList.add("Get in touch with your local NGO's and district administration to volunteer for this cause.");
        bannerList.add("Stands against FAKE and illegit WhatsApp forwards! Do NOT forword a message untill you verify the content it contains.");
        bannerList.add("Our brothers from North-East are just as Indian as you! Help everyone during this crisis.");
        bannerList.add("The virus does not discriminate. Why do you? DO NOT DISCRIMINATE. We are all INDIANS.");
        bannerList.add("There is no evidence that hot weather will stop the virus! You can! Stay home, stay safe.");
        bannerList.add("Help the medical medical fraternity by staying at home!");
        bannerList.add("Plan ahead! Take a minute and check how much supplies you have at home. Planning lets you buys exactly what you need.");
        bannerList.add("If you have any mediacal queries, reach out to your state helpline, district administration or trusted doctors!.");
        bannerList.add("This will pass too. Enjoy your time at home and spend quality time with your family! Things will be normal soon.");
        bannerList.add("If you hava any symtoms and suspect you have coronavirus - reaach ot to your doctors or call state helplines.");
        bannerList.add("Panic Mode : OFF, ESSENTIALS are ON!");
        bannerList.add("Lockdown means LOCKDOWNS! Avoid going out unless absolutely neccesary, Stay safe");


        setRandomBannerText(bannerList, rootView);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setRandomBannerText(bannerList, rootView);
                handler.postDelayed(this, 5000);
            }
        },5000);

        rootView.findViewById(R.id.ll_banner).setOnClickListener(v -> {
            setRandomBannerText(bannerList,rootView);
        });

    }

    private static void setRandomBannerText(List<String> bannerList, View rootView) {
        int randNum = new Random().nextInt(bannerList.size()-1);
        ((TextView)rootView.findViewById(R.id.banner_text)).setText(bannerList.get(randNum));
    }


}
