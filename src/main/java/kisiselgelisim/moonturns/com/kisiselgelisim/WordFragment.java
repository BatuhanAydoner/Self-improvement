package kisiselgelisim.moonturns.com.kisiselgelisim;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kisiselgelisim.moonturns.com.kisiselgelisim.Data.WordData;

public class WordFragment extends DialogFragment {

    private DatabaseReference reference;
    private static final String DATABASASE_WORD_NAME = "word"; //Firebase child
    private static final String DATA_COUNT = "data_count"; //Firebase word's count
    private long wordCount = 0; //data count that comes from firebase
    private long randomWord = 0;

    private TextView txtWord, txtProposal, txtClose, txtPerson;
    private ProgressBar progressBar;
    private ArrayList<WordData> wordDataList;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        setCancelable(false);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.word_dialog_fragment, null, false);

        txtWord = (TextView) view.findViewById(R.id.txtWord);
        txtProposal = (TextView) view.findViewById(R.id.txtProposal);
        txtClose = (TextView) view.findViewById(R.id.txtClose);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        txtPerson = (TextView) view.findViewById(R.id.txtPerson);

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(view);

        return dialog;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setTxtProposal();
        setTxtClose();
        getWordCount();

    }

    //txtProposal click listener
    private void setTxtProposal() {

        txtProposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getDataFromFirebase();

            }
        });

    }

    //txtClose click listener
    private void setTxtClose() {

        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();

            }
        });

    }

    //get word count from firebase
    public void getWordCount() {

        reference = FirebaseDatabase.getInstance().getReference();

        reference.child(DATABASASE_WORD_NAME).child(DATA_COUNT).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                wordCount = (long) dataSnapshot.getValue();
                Log.e("word",""+wordCount);
                getDataFromFirebase();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    //get datas from firebase word and person
    private void getDataFromFirebase() {


        if (progressBar.getVisibility() == View.INVISIBLE)
            visibleProgress();

        randomCount();

        reference.child(DATABASASE_WORD_NAME).child(DATABASASE_WORD_NAME + randomWord).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                WordData data = dataSnapshot.getValue(WordData.class);
                putData(data.getText(), data.getPerson());
                invisibleProgress();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void visibleProgress() {

        progressBar.setVisibility(View.VISIBLE);

    }

    private void invisibleProgress() {

        progressBar.setVisibility(View.INVISIBLE);

    }

    //set data for txt
    private void putData(String word, String person) {

        txtWord.setText(word);
        txtPerson.setText(person);

    }

    //random word from firebase word
    private void randomCount() {

        randomWord = 1 + (long) (Math.random() * wordCount);
        Log.e("random", "" + randomWord);

    }

}
