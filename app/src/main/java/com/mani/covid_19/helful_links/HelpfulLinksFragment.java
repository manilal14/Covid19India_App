package com.mani.covid_19.helful_links;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mani.covid_19.CommonFuntions;
import com.mani.covid_19.R;

import java.util.ArrayList;
import java.util.List;

public class HelpfulLinksFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();
    private View mRootView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_helpful_links, container, false);
        Log.e(TAG, "HelpfulLinksFragment");
        clickListeners();
        setLinks();

        return mRootView;
    }

    private void clickListeners() {
        CommonFuntions.setupBottomLayoutClicks(getActivity(),mRootView);

    }

    private void setLinks() {

        List<Links> linksList = new ArrayList<>();

        linksList.add(new Links("HELPLINE NUMBERS [by State]","https://www.mohfw.gov.in/pdf/coronvavirushelplinenumber.pdf"));
        linksList.add(new Links("Ministry of Health and Family Welfare, Gov. of India","https://www.mohfw.gov.in/"));
        linksList.add(new Links("WHO : COVID-19 Home Page","https://www.who.int/emergencies/diseases/novel-coronavirus-2019"));
        linksList.add(new Links("CDC","https://www.cdc.gov/coronavirus/2019-ncov/faq.html"));
        linksList.add(new Links("COVID-19 Global Tracker","https://coronavirus.thebaselab.com/"));

        RecyclerView recyclerView = mRootView.findViewById(R.id.recycler_view_links);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new LinksAdapter(getActivity(),linksList));

    }
}