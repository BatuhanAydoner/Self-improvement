package kisiselgelisim.moonturns.com.kisiselgelisim;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import kisiselgelisim.moonturns.com.kisiselgelisim.Adapter.RVAdapter;
import kisiselgelisim.moonturns.com.kisiselgelisim.Data.Database;
import kisiselgelisim.moonturns.com.kisiselgelisim.Data.RVData;
import kisiselgelisim.moonturns.com.kisiselgelisim.Data.UserSettings;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private CollapsingToolbarLayout mainCollapsing;
    private Toolbar mainToolbar;
    private static ProgressBar progressBar;
    private static ArrayList<RVData> rvData;

    public static SwipeRefreshLayout swipeRefresh;
    private static RecyclerView rv;
    private static RVAdapter rvAdapter;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static int valueStyle = 0; // recyclerview style number

    private CountDownTimer timer; //if there is no any connection, when this timer be 10 seconds, app is destroyed

    //get widget from activity main
    private void crt() {

        mainCollapsing = (CollapsingToolbarLayout) this.findViewById(R.id.maincollapsing);
        mainToolbar = (Toolbar) this.findViewById(R.id.mainToolbar);
        swipeRefresh = (SwipeRefreshLayout) this.findViewById(R.id.swiperefresh);
        rv = (RecyclerView) this.findViewById(R.id.rv);
        progressBar = (ProgressBar) this.findViewById(R.id.progressBar);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences(); //sharedpreferences
        crt();//method that gets widget from activity main
        setToolbar();

        //readData();
        setInternetTimer();
        readData();
        refreshList();

    }

    //set toolbar for mainActivity
    private void setToolbar() {

        mainCollapsing.setTitleEnabled(false);

        this.setSupportActionBar(mainToolbar);
        this.getSupportActionBar().setDisplayShowTitleEnabled(true);
        this.getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem menuItem = (MenuItem) menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case R.id.action_rv_style:

                newStyleRV();

                break;

            case R.id.action_word:

                showWordFragment();

                break;

            case R.id.action_videos:

                Intent intent = new Intent(MainActivity.this, VideoYoutubeActivity.class);
                startActivity(intent);

                break;

        }

        return true;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;

    }

    @Override
    public boolean onQueryTextChange(String newText) {


        rvAdapter.getFilter().filter(newText);


        return true;

    }

    //get datas from database
    private void readData() {

        Database database = new Database(this);
        database.getDataFromDatabase();

    }

    //change rcyclerview style
    private void newStyleRV() {

        if (valueStyle == 0)
            valueStyle = 1;
        else if (valueStyle == 1)
            valueStyle = 0;

        editor.putInt("style", valueStyle).commit();

        setRV(this, rvData);

    }

    //invisible progressbar
    private static void invisibleProgress() {

        progressBar.setVisibility(View.INVISIBLE);

    }

    //sharedPreferences process
    private void preferences() {

        UserSettings.statementPreferences(this);
        sharedPreferences = UserSettings.getSharedPreferences(); // return sharedpreferences
        editor = UserSettings.getEditor();

        valueStyle = sharedPreferences.getInt("style", 0); //recyclerview style if it is 0 big style or 1 small style

    }

    //recyclerview adapter
    public static void setRV(Context context, ArrayList<RVData> list) {

        rvData = list;

        invisibleProgress();
        rvAdapter = new RVAdapter(context, rvData, valueStyle);
        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

    }

    //this timer controls internet
    //If there is no connection, shows a dialog fragment
    private void setInternetTimer() {

        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

                if (progressBar.getVisibility() == View.VISIBLE) {

                    FragmentManager manager = MainActivity.this.getSupportFragmentManager();
                    InternetDialogFragment dialogFragment = new InternetDialogFragment();
                    dialogFragment.show(manager, "dialogFragment");


                }

            }
        }.start();

    }

    //refresh listener
    private void refreshList() {

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipeRefresh.setColorScheme(android.R.color.holo_blue_bright,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light);
                readData();


            }
        });

    }

    //when touch word menu item, show a fragment.This fragment shows words
    private void showWordFragment() {

        FragmentManager manager = this.getSupportFragmentManager();
        WordFragment wordFragment = new WordFragment();
        wordFragment.show(manager, "dialogWord");

    }

}
