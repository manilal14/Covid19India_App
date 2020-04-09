package com.mani.covid_19.About;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mani.covid_19.BuildConfig;
import com.mani.covid_19.CommonFuntions;
import com.mani.covid_19.R;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();
    private View mRootView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG,"AboutFragment");
        mRootView = inflater.inflate(R.layout.fragment_helpful_links, container, false);
        clickListeners();
        setFaq();
        return mRootView;
    }

    private void clickListeners() {
        CommonFuntions.setBanner(mRootView);
        CommonFuntions.setupBottomLayoutClicks(getActivity(),mRootView);
        ((TextView)mRootView.findViewById(R.id.app_version)).setText("App Version : "+ BuildConfig.VERSION_NAME);
    }


    private void setFaq() {

        List<Faq> linksList = new ArrayList<>();

        linksList.add(new Faq("[07-Apr] Confusion regarding WB patient numbers",
                "As WB state bulletin only reports the number of active cases, the numbers for WB are currently updated" +
                        " according to https://www.mohfw.gov.in/"));

        linksList.add(new Faq("Are you official?","No"));
        linksList.add(new Faq("What are your sources? How is the data gathered for this project?",
                "We are using state bulletins and official handles to update our numbers. " +
                        "The data is validated by a group of volunteers and published into a Google sheet and an API. " +
                        "API is available for all at api.covid19india.org. We would love it if you can use this data in the " +
                        "fight against this virus. The source list is here."));
        linksList.add(new Faq("Why does covid19india.org have more positive count than MoH?",
                "MoHFW updates the data at a scheduled time. However, we update them based on state press bulletins," +
                " official handles and reliable news channels which could be more recent."));

        linksList.add(new Faq("Who are you?","We are a group of dedicated volunteers who curate and verify the data coming " +
                "from several sources. We extract the details, like a patient's relationship with other patients to identify local and community " +
                "transmissions, travel history and status. We never collect or expose any personally identifiable data regarding the patients."));

        linksList.add(new Faq("Why are you guys putting in time and resources to do this while not gaining a single penny from it?",
                "Because it affects all of us. Today it's someone else who is getting infected; tomorrow it could be us. " +
                        "We need to prevent the spread of this virus. We need to document the data so that people with knowledge " +
                        "can use this data to make informed decisions."));

        RecyclerView recyclerView = mRootView.findViewById(R.id.recycler_view_links);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new FaqAdapter(getActivity(),linksList));

    }
}