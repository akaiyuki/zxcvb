package com.smartwave.taskr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daprlabs.cardstack.SwipeDeck;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.smartwave.taskr.core.BaseActivity;
import com.smartwave.taskr.R;
import com.smartwave.taskr.core.TSingleton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class TaskActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, GoogleApiClient.ConnectionCallbacks {

    private static final String TAG = "TaskActivity";
    private SwipeDeck cardStack;
    private Context context = this;

    private SwipeDeckAdapter adapter;
    private ArrayList<String> testData;

    public GoogleApiClient google_api_client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

         /* Initialize toolbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("");

        google_api_client =  new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API,Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();

        TextView mTextLogout = (TextView) toolbar.findViewById(R.id.logout);
        mTextLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginActivity.INSTANCE.is_signInBtn_clicked = false;

                if (google_api_client.isConnected()) {
                    Plus.AccountApi.clearDefaultAccount(google_api_client);
                    google_api_client.disconnect();
                    google_api_client.connect();
//            changeUI(false);
                }

                    Log.d("sign out clicked", "clicked");


//                TSingleton.setLogoutGmail("1");

                startActivity(new Intent(TaskActivity.this, LoginActivity.class));
                finish();

            }
        });


        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);
        cardStack.setHardwareAccelerationEnabled(true);

        testData = new ArrayList<>();
        testData.add("0");
        testData.add("1");
        testData.add("2");
        testData.add("3");
        testData.add("4");

        adapter = new SwipeDeckAdapter(testData, this);
        cardStack.setAdapter(adapter);

        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                Log.i("MainActivity", "card was swiped left, position in adapter: " + position);
            }

            @Override
            public void cardSwipedRight(int position) {
                Log.i("MainActivity", "card was swiped right, position in adapter: " + position);
            }

            @Override
            public void cardsDepleted() {
                Log.i("MainActivity", "no more cards");
            }

            @Override
            public void cardActionDown() {
                Log.i(TAG, "cardActionDown");
            }

            @Override
            public void cardActionUp() {
                Log.i(TAG, "cardActionUp");
            }

        });
        cardStack.setLeftImage(R.id.left_image);
        cardStack.setRightImage(R.id.right_image);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardStack.swipeTopCardLeft(180);

            }
        });
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardStack.swipeTopCardRight(180);
            }
        });

        Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testData.add("a sample string.");
//                ArrayList<String> newData = new ArrayList<>();
//                newData.add("some new data");
//                newData.add("some new data");
//                newData.add("some new data");
//                newData.add("some new data");
//
//                SwipeDeckAdapter adapter = new SwipeDeckAdapter(newData, context);
//                cardStack.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    protected void onStart() {
        super.onStart();
        google_api_client.connect();
    }

    protected void onStop() {
        super.onStop();
        if (google_api_client.isConnected()) {
            google_api_client.disconnect();
        }
    }

    protected void onResume(){
        super.onResume();
        if (google_api_client.isConnected()) {
            google_api_client.connect();
        }
    }

    public class SwipeDeckAdapter extends BaseAdapter {

        private List<String> data;
        private Context context;

        public SwipeDeckAdapter(List<String> data, Context context) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = convertView;
            if (v == null) {
                LayoutInflater inflater = getLayoutInflater();
                // normally use a viewholder
                v = inflater.inflate(R.layout.test_card2, parent, false);
            }
            //((TextView) v.findViewById(R.id.textView2)).setText(data.get(position));
            ImageView imageView = (ImageView) v.findViewById(R.id.offer_image);
            Picasso.with(context).load(R.drawable.taskerlogo).fit().centerCrop().into(imageView);
            TextView textView = (TextView) v.findViewById(R.id.sample_text);
            final String item = (String)getItem(position);
            textView.setText(item);

//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.i("Layer type: ", Integer.toString(v.getLayerType()));
//                    Log.i("Hwardware Accel type:", Integer.toString(View.LAYER_TYPE_HARDWARE));
//                    Intent i = new Intent(v.getContext(), MainActivity.class);
//                    v.getContext().startActivity(i);
//                }
//            });

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("position_item", item);
                    startActivity(new Intent(TaskActivity.this, UnfoldableDetailsActivity.class));
                }
            });

            

            return v;
        }
    }

}
