package kisiselgelisim.moonturns.com.kisiselgelisim.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kisiselgelisim.moonturns.com.kisiselgelisim.ContentActivity;
import kisiselgelisim.moonturns.com.kisiselgelisim.Data.Database;
import kisiselgelisim.moonturns.com.kisiselgelisim.Data.RVData;
import kisiselgelisim.moonturns.com.kisiselgelisim.FilterHelper;
import kisiselgelisim.moonturns.com.kisiselgelisim.R;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> implements Filterable {

    private View view = null;

    private Context context;
    private ArrayList<RVData> rvData;
    private FilterHelper filterHelper;
    private int style = 0;

    public RVAdapter(Context context, ArrayList<RVData> rvData, int style) {
        this.context = context;
        this.rvData = rvData;
        filterHelper = new FilterHelper(rvData, this);
        this.style = style;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (style) {

            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.rv_line, parent, false);

                break;

            case 1:

                view = LayoutInflater.from(context).inflate(R.layout.rv_line_other, parent, false);

                break;


        }

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        RVData data = rvData.get(position);

        holder.setData(data, position);

    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    @Override
    public Filter getFilter() {

        return filterHelper;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private ImageView imgText;
        private TextView txtTitle;
        private ProgressBar progressBar;

        public MyViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            imgText = (ImageView) itemView.findViewById(R.id.imgText);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);

        }

        public void setData(final RVData rvData, final int position) {

            txtTitle.setText(rvData.getTitle());

            setImageFromDatabase(rvData.getImage());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, ContentActivity.class);
                    intent.putExtra("image", rvData.getImage());
                    intent.putExtra("title", rvData.getTitle());
                    intent.putExtra("text", rvData.getText());
                    context.startActivity(intent);

                }
            });

        }

        private void setImageFromDatabase(String image) {

            if (!imgText.equals("")) {

                Picasso.get().load(image).into(imgText);//image
                invisibleProgress();

            }


        }

        private void invisibleProgress() {

            progressBar.setVisibility(View.INVISIBLE);

        }

    }

    public void setFilter(ArrayList<RVData> data) {

        rvData = data;

    }

}