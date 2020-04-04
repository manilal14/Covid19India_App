package com.mani.covid_19;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mani.covid_19.faq.FaqFragment;
import com.mani.covid_19.helful_links.HelpfulLinksFragment;
import com.mani.covid_19.home.HomeFragment;

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


            loadFragment(new FaqFragment());
        });

    }

    private void loadFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment,fragment.getClass().getSimpleName());
        fragmentTransaction.commit();

    }

}
