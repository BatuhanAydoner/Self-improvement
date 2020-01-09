package kisiselgelisim.moonturns.com.kisiselgelisim.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;






import kisiselgelisim.moonturns.com.kisiselgelisim.Data.VideoData;
import kisiselgelisim.moonturns.com.kisiselgelisim.R;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<VideoData> videoDataList;

    public VideoAdapter(Context context, ArrayList<VideoData> videoDataList) {
        this.context = context;
        this.videoDataList = videoDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.video_rv_line, parent, false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        VideoData data = videoDataList.get(position);

        holder.setData(data, position);

    }

    @Override
    public int getItemCount() {
        return videoDataList.size();
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
            invisibleProgress();

        }

        public void setData(final VideoData videoData, final int position) {

            txtTitle.setText(videoData.getVideo_title());

            setImageFromDatabase(videoData.getVideo_image());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(videoData.getVideo_url()));
                    intent.setPackage("com.google.android.youtube");
                    context.startActivity(intent);

                }
            });

        }

        //put a picture imgText from Firebase
        private void setImageFromDatabase(String video_image) {

            Picasso.get().load(video_image).into(imgText);//image

            Drawable drawable = imgText.getDrawable();

            if (drawable != null)
                invisibleProgress();

        }

        private void invisibleProgress() {

            progressBar.setVisibility(View.INVISIBLE);

        }

    }
}
