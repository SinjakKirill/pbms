package com.example.sinyakkirill.lab_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getFilesDir(View v){
        TextView text = (TextView) findViewById(R.id.textView);
        text.setText("11111"+ '\n' + "2222");
    }
}
