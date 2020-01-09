package kisiselgelisim.moonturns.com.kisiselgelisim.Data;

import android.content.Context;


import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kisiselgelisim.moonturns.com.kisiselgelisim.MainActivity;

public class Database {

    private static final String CHILD_NAME = "content"; //Firebase child name

    DatabaseReference referans = null;

    private Context context;
    private ArrayList<RVData> rvDataList = null; //list as RVData

    public Database(Context context) {
        this.context = context;
    }

    //get data from Firebase content
    public void getDataFromDatabase() {

        rvDataList = new ArrayList<>();

        referans = FirebaseDatabase.getInstance().getReference();

        referans.child(CHILD_NAME).orderByPriority().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    RVData data = snapshot.getValue(RVData.class);
                    rvDataList.add(data);

                }

                putDataRV();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }
        });

    }

    //put data recyclerview at MainActivity and make swipeRefresh false
    private void putDataRV() {

        MainActivity.setRV(context,rvDataList);
        MainActivity.swipeRefresh.setRefreshing(false);

    }

}
