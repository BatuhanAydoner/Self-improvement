package kisiselgelisim.moonturns.com.kisiselgelisim;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kisiselgelisim.moonturns.com.kisiselgelisim.Adapter.VideoAdapter;
import kisiselgelisim.moonturns.com.kisiselgelisim.Data.VideoData;

public class VideoYoutubeActivity extends AppCompatActivity {

    private final String VİDEO_NAME = "videos"; //firebase child

    private ArrayList<VideoData> videoDataList;

    private RecyclerView rv_video;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefresh;

    private void crt(){

        rv_video = (RecyclerView) this.findViewById(R.id.rv_video);
        progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
        swipeRefresh = (SwipeRefreshLayout) this.findViewById(R.id.swiperefresh);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_youtube);

        crt();

        getDataFromDatabase();

        refreshListener();

    }

    //get data drom Firabase videos
    private void getDataFromDatabase(){

        videoDataList = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        reference.child(VİDEO_NAME).orderByPriority().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    VideoData videoData = snapshot.getValue(VideoData.class);
                    videoDataList.add(videoData);

                }

                swipeRefresh.setRefreshing(false);
                setRVAdapter();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    //set data to recyclerview
    private void setRVAdapter(){

        invisibleProgress();

        VideoAdapter adapter = new VideoAdapter(this, videoDataList);

        rv_video.setAdapter(adapter);
        rv_video.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }

    //invisible progressbar
    private void invisibleProgress() {

        progressBar.setVisibility(View.INVISIBLE);

    }

    //swipeRefresh listener
    private void refreshListener() {

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipeRefresh.setColorScheme(android.R.color.holo_blue_bright,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light);

                getDataFromDatabase();

            }
        });

    }

}
