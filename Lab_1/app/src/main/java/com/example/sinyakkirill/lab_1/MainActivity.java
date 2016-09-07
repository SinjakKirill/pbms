package com.example.sinyakkirill.lab_1;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    File file;

    private void setInformationFile(File file){
        TextView absolute = (TextView) findViewById(R.id.Absolute);
        TextView name = (TextView) findViewById(R.id.Name);
        TextView path = (TextView) findViewById(R.id.Path);
        TextView readWrite = (TextView) findViewById(R.id.ReadWrite);
        TextView external = (TextView) findViewById(R.id.External);
        absolute.setText("Absolute: " + file.getAbsolutePath());
        name.setText("Name: " + file.getName());
        path.setText("Path: " + file.getPath());
        if(file.canRead()){
            readWrite.setText("Read/Write: " + "yes/");
        }
        else{
            readWrite.setText("Read/Write: " + "no/");
        }
        if(file.canWrite()){
            readWrite.setText(readWrite.getText() + "yes");
        }
        else{
            readWrite.setText(readWrite.getText() + "no");
        }
        external.setText("External: ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getFilesDir(View v){
        try {
            file = getFilesDir();
            setInformationFile(file);
        }
        catch (Exception e){
            Log.d("Error", e.getMessage());
        }
    }

    public void getCacheDir(View v){
        try {
            file = getCacheDir();
            setInformationFile(file);
        }
        catch (Exception e){
            Log.d("Error", e.getMessage());
        }
    }

    public void getExternalFilesDir(View v){
        try {
            file = getExternalFilesDir("");
            setInformationFile(file);
        }
        catch (Exception e){
            Log.d("Error", e.getMessage());
        }
    }

    public void getExternalCacheDir(View v){
        try {
            file = getExternalCacheDir();
            setInformationFile(file);
        }
        catch (Exception e){
            Log.d("Error", e.getMessage());
        }
    }

    public void getExternalStoregeDirectory(View v){
        try {
            file = Environment.getExternalStorageDirectory();
            setInformationFile(file);
        }
        catch (Exception e){
            Log.d("Error", e.getMessage());
        }
    }

    public void getExternalStoragePublicDirectory(View v){
        try {
            file = Environment.getExternalStoragePublicDirectory("");
            setInformationFile(file);
        }
        catch (Exception e){
            Log.d("Error", e.getMessage());
        }
    }
}
