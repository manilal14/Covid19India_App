package com.mani.covid_19;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mani.covid_19.About.AboutFragment;
import com.mani.covid_19.helful_links.HelpfulLinksFragment;
import com.mani.covid_19.home.HomeFragment;
import com.mani.covid_19.home.State;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));

        loadFragment(new HomeFragment());

        setCustomToolbar();

    }

    private void setCustomToolbar() {

        TextView btn_home  = findViewById(R.id.home);
        TextView btn_links = findViewById(R.id.helful_links);
        TextView btn_faq   = findViewById(R.id.faq);

        btn_home.setOnClickListener(v -> {

            btn_home.setTextColor(ContextCompat.getColor(this,R.color.black));
            btn_links.setTextColor(ContextCompat.getColor(this,R.color.white_85));
            btn_faq.setTextColor(ContextCompat.getColor(this,R.color.white_85));

            loadFragment(new HomeFragment());


        });

        btn_links.setOnClickListener(v -> {

            btn_home.setTextColor(ContextCompat.getColor(this,R.color.white_85));
            btn_links.setTextColor(ContextCompat.getColor(this,R.color.black));
            btn_faq.setTextColor(ContextCompat.getColor(this,R.color.white_85));

            loadFragment(new HelpfulLinksFragment());
        });

        btn_faq.setOnClickListener(v -> {

            btn_home.setTextColor(ContextCompat.getColor(this,R.color.white_85));
            btn_links.setTextColor(ContextCompat.getColor(this,R.color.white_85));
            btn_faq.setTextColor(ContextCompat.getColor(this,R.color.black));


            loadFragment(new AboutFragment());
        });

    }

    private void loadFragment(Fragment fragment) {

        if(fragment instanceof HomeFragment){
            Log.e("check", "sending bundle");
            //sending bundle received from Splash screen to Homefragment

            List<State> stateList = (List<State>) getIntent().getSerializableExtra(AppConstants.STATE_LIST);
            State state = (State) getIntent().getSerializableExtra(AppConstants.WHOLE_INDIA);

            Bundle b = new Bundle();
            b.putSerializable(AppConstants.STATE_LIST, (Serializable) stateList);
            b.putSerializable(AppConstants.WHOLE_INDIA,state);
            fragment.setArguments(b);

        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment,fragment.getClass().getSimpleName());

        fragmentTransaction.commit();

    }

}
