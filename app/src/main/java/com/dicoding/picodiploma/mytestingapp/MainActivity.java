package com.dicoding.picodiploma.mytestingapp;

import android.app.ActionBar;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvText;
    private Button btnSetValue;
    private ImageView imgPreview;
    private ArrayList<String> names;
    private DelayAsync delayAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = findViewById(R.id.tv_text);
        imgPreview = findViewById(R.id.img_preview);
        btnSetValue = findViewById(R.id.btn_set_value);
        btnSetValue.setOnClickListener(this);

        names = new ArrayList<>();
        names.add("Nandang Sopyan");
        names.add("Putri");
        names.add("Nurumi");

        //imgPreview.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fronalpstock_big));
        Glide.with(this).load(R.drawable.fronalpstock_big).into(imgPreview);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_set_value) {
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < names.size(); i++) {
                name.append(names.get(i)).append("\n");
            }
            btnSetValue.setText("clear nilai");
            btnSetValue.setId(R.id.btn_clear_text);
            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            p.addRule(RelativeLayout.BELOW, R.id.btn_clear_text);
            imgPreview.setLayoutParams(p);
            Log.d("change to", "clear button");
            tvText.setText(name);

            delayAsync = new DelayAsync();
            delayAsync.execute();


        } else {
            tvText.setText("Romadhon");
            btnSetValue.setText("set nilai");
            btnSetValue.setId(R.id.btn_set_value);
            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            p.addRule(RelativeLayout.BELOW, R.id.btn_set_value);
            imgPreview.setLayoutParams(p);
            Log.d("change to", "set value button");
        }
    }

    private static class DelayAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("DelayAsync", "Done");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.d("DelayAsync", "Cancelled");
        }

    }

    protected void onDestroy() {
        super.onDestroy();
        if (delayAsync != null) {
            if (delayAsync.getStatus().equals(AsyncTask.Status.RUNNING)) {
                delayAsync.cancel(true);
            }
        }
    }


}
