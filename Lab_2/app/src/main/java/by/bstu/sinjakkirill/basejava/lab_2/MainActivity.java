package by.bstu.sinjakkirill.basejava.lab_2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void inputName(View v){
        final String fileName = "Base_Lab02.txt";
        EditText textName = (EditText) findViewById(R.id.name);
        EditText textSurname = (EditText) findViewById(R.id.surname);
        File f = new File(super.getFilesDir(), fileName);
        if(ExistBase(fileName)){
            try{
                FileWriter fw = new FileWriter(f, true);
                BufferedWriter bw = new BufferedWriter(fw);
                if(!textName.getText().toString().equals("") && !textSurname.getText().toString().equals("")) {
                    bw.write(textName.getText() + " " + textSurname.getText() + "; " + "\r\n");
                    bw.close();
                    Log.d("Log_2", "Данные записаны в файл " + fileName);
                }
                textName.setText("");
                textSurname.setText("");
                textName.setHint("Name");
                textSurname.setHint("Surname");
            }
            catch (IOException e){
                Log.d("Log_2", "Не удалось открыть " + fileName);
            }
        }
        else{
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Создается файл " + fileName).setPositiveButton("Да", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d("Log_02", "Создание файла " + fileName);
                }
            });
            AlertDialog ab = b.create();
            ab.show();
            try{
                f.createNewFile();
                Log.d("Log_02", "Файл " + fileName + " создан");
            }
            catch (IOException e){
                Log.d("Log_02", "Файл " + fileName + " не создан");
            }
        }
    }

    protected void readFile(View v){
        final String fileName = "Base_Lab02.txt";
        File f = new File(super.getFilesDir(), fileName);
        try{
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            char[] buf = new char[(int)f.length()];
            br.read(buf);
            br.close();
            TextView dataText = (TextView) findViewById(R.id.datatextView);
            String data = new String(buf);
            dataText.setText(data);
            Log.d("Log_2", "Данные считаны с файла " + fileName);
        }
        catch(IOException e){
            Log.d("Log_2", "Не удалось открыть " + fileName);
        }
    }

    private boolean ExistBase(String fname){
        boolean rc = false;
        File f = new File(super.getFilesDir(), fname);
        if(rc = f.exists())
            Log.d("Log_02", "Файл " + fname + " существует");
        else Log.d("Log_02", "Файл " + fname + " не найден");
        return rc;
    }
}
