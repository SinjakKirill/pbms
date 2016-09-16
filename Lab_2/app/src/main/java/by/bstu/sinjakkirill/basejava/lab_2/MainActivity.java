package by.bstu.sinjakkirill.basejava.lab_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void inputData(View view){
        EditText textName = (EditText) findViewById(R.id.editText);
        textName.setText("Kirill");
    }
}
