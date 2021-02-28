package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.List;

import okhttp3.Headers;

public class DetailActivity extends AppCompatActivity {

    public static final String TAG_D = "DetailActivity";
    TwitterClient client;
    ImageView ivProfileDetail;
    TextView tvBodyDetail;
    TextView tvScreenNameDetail;
    TextView tvNameDetail;
    TextView tvDateDetail;
    TextView tvLocation;
    
    private String formattedRelTime;
    private String formattedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.i(TAG_D,"Detail Activity Active");

        client = TwitterApp.getRestClient(this);
        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        User user = Parcels.unwrap(getIntent().getParcelableExtra("user"));

        ivProfileDetail = findViewById(R.id.ivProfileDetail);
        tvBodyDetail = findViewById(R.id.tvBodyDetail);
        tvScreenNameDetail = findViewById(R.id.tvScreenNameDetail);
        tvNameDetail = findViewById(R.id.tvNameDetail);
        tvDateDetail = findViewById(R.id.tvDateDetail);
        tvLocation = findViewById(R.id.tvLocation);

        tvScreenNameDetail.setText(String.format("@%s", tweet.user.screenName));
        tvNameDetail.setText(tweet.user.name);
        formattedRelTime = TimeFormatter.getTimeDifference(tweet.createdAt);
        if (formattedRelTime.equals("Just now")){
            formattedRelTime = (String.format("%s", formattedRelTime));
        }else{
            formattedRelTime = (String.format("%s ago", formattedRelTime));
        }
        formattedDate = TimeFormatter.getDateStamp(tweet.createdAt);
        tvDateDetail.setText(String.format("%s\n%s", formattedRelTime ,formattedDate));
        Glide.with(this).load(tweet.user.profileImageUrl).into(ivProfileDetail);
        tvBodyDetail.setText(tweet.body);
        tvLocation.setText(user.location);


    }
}