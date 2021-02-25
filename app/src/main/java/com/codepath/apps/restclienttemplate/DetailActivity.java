package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {
    public static final String TAG_D = "DetailActivity";
    TwitterClient client;
    ImageView ivProfileImage;
    TextView tvBody;
    TextView tvScreenNameDetail;
    TextView tvNameDetail;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.i(TAG_D,"Detail Activity Active");

        tvScreenNameDetail = findViewById(R.id.tvScreenNameDetail);
        tvNameDetail = findViewById(R.id.tvNameDetail);

        client = TwitterApp.getRestClient(this);
        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        User user = Parcels.unwrap(getIntent().getParcelableExtra("user"));
    }
}