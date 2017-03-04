package ru.merkulyevsasha.presentation.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import javax.inject.Inject;

import ru.merkulyevsasha.AutoTestApp;
import ru.merkulyevsasha.model.TitleItem;
import ru.merkulyevsasha.R;
import ru.merkulyevsasha.presentation.list.TitlesPresenter;


public class TitleFragmentActivity extends FragmentActivity {

    public static final String KEY_POSITION = "position";

    @Inject
    TitlesPresenter pres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ((AutoTestApp)getApplication()).getComponent().inject(this);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new TitlesFragmentPagerAdapter(pres.getLastResponse(), getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        Intent intent = getIntent();
        int position = intent.getIntExtra(KEY_POSITION, 0);
        pager.setCurrentItem(position);

    }


    private class TitlesFragmentPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<TitleItem> titles;

        public TitlesFragmentPagerAdapter(ArrayList<TitleItem> titles, FragmentManager fm) {
            super(fm);
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return TitlePageFragment.newInstance(titles.get(position));
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position).getTitle();
        }

    }
}
