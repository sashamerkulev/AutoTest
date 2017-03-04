package ru.merkulyevsasha.presentation.list;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.merkulyevsasha.AutoTestApp;
import ru.merkulyevsasha.data.HttpTitlesService;
import ru.merkulyevsasha.model.TitleItem;
import ru.merkulyevsasha.presentation.R;
import ru.merkulyevsasha.presentation.details.TitleFragmentActivity;


public class TitlesActivity extends AppCompatActivity implements TitlesView {

    protected SwipeRefreshLayout mRefreshLayout;
    protected View mRootView;
    private TitlesViewAdapter mListAdaper;

    private HttpTitlesService http;


    @Inject
    TitlesPresenter pres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((AutoTestApp)getApplication()).getComponent().inject(this);

        mRootView = findViewById(R.id.main_content);

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        mListAdaper = new TitlesViewAdapter(this, new ArrayList<TitleItem>());
        ListView mListView = (ListView) findViewById(R.id.listview_titles);
        mListView.setAdapter(mListAdaper);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(TitlesActivity.this, TitleFragmentActivity.class);
                intent.putExtra(TitleFragmentActivity.KEY_POSITION, position);
                startActivity(intent);

            }
        });

    }

    private void refresh(){
        pres.load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_refresh){
            mRefreshLayout.setRefreshing(true);
            refresh();
        }

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (pres != null){
            pres.onStart(this);
            pres.load();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (pres != null){
            pres.onStop();
        }

    }

    @Override
    public void showProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void showMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(mRootView, message, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void loadTitles(final List<TitleItem> titles) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mListAdaper.clear();
                mListAdaper.addAll(titles);
                mListAdaper.notifyDataSetChanged();
            }
        });
    }
}
