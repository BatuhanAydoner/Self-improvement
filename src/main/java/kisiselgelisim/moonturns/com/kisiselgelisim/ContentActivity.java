package kisiselgelisim.moonturns.com.kisiselgelisim;

import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import javax.security.auth.login.LoginException;

import kisiselgelisim.moonturns.com.kisiselgelisim.Data.Database;
import kisiselgelisim.moonturns.com.kisiselgelisim.Data.RVData;

public class ContentActivity extends AppCompatActivity {

    private String image="";
    private String title="";
    private String text="";

    private CollapsingToolbarLayout contentCollapsing;
    private Toolbar contentToolbar;
    private ImageView imgCollapsing;
    private TextView txtContent, txtTitle;
    private ProgressBar progressBar;

    private void crt() {

        contentCollapsing = (CollapsingToolbarLayout) this.findViewById(R.id.contentCollapsing);
        contentToolbar = (Toolbar) this.findViewById(R.id.contentToolbar);

        imgCollapsing = (ImageView) this.findViewById(R.id.imgCollapsing);

        txtContent = (TextView) this.findViewById(R.id.txtContent);
        txtTitle = (TextView) this.findViewById(R.id.txtTitle);
        progressBar=(ProgressBar) this.findViewById(R.id.progressBar);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        crt();

        contentToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        contentToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        setContent();

    }

    //put content to widget
    private void setContent() {

        image = getIntent().getStringExtra("image");
        title = getIntent().getStringExtra("title");
        text = getIntent().getStringExtra("text");

        txtTitle.setText(title);

        Spanned spanned = (Spanned) Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY );

        txtContent.setText(spanned);

        setImageFromDatabase();


    }

    //put a picture imgText from Firebase
    private void setImageFromDatabase() {

        Picasso.get().load(image).into(imgCollapsing);

        Drawable drawable = imgCollapsing.getDrawable();

        if (drawable != null) {

            invisibleProgress();

        }

    }

    //make progressBar invisible
    private void invisibleProgress(){

        progressBar.setVisibility(View.INVISIBLE);

    }

}
