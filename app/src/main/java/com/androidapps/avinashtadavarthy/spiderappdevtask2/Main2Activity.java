package com.androidapps.avinashtadavarthy.spiderappdevtask2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("pos");
        TextView textView= (TextView)findViewById(R.id.texttext);
        textView.setText(id);

    }
}
