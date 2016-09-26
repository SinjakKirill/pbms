package by.bstu.sinjakkirill.basejava.lab_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void saveData(View view){
        final String fileName = "Lab_03.txt";
        EditText inputKey = (EditText) findViewById(R.id.inputKeyeditText);
        EditText inputValue = (EditText) findViewById(R.id.inputValueeditText);
        File f = new File(super.getFilesDir(), fileName);
        f.delete();

        if(!ExistBase(fileName)){
            try{
                f.createNewFile();
                RandomAccessFile rf = new RandomAccessFile(f, "rw");
                for (int i = 0; i < 360; i++) {
                    rf.writeChar('0');
                }
                EditText e = (EditText) findViewById(R.id.inputKeyeditText);
                e.setText(Integer.toString((int)rf.length()));
                rf.close();
                Log.d("Log_03", "Файл " + fileName + " успешно создан");
        }
            catch (IOException e){
                Log.d("Log_03", "Не удалось создать файл " + fileName);
            }
        }


        try {
            try{
                RandomAccessFile rf = new RandomAccessFile(f, "rw");
                byte[] _text = new byte[(int)f.length()];
                rf.read(_text);
                rf.writeChar('6');
                String _textString = new String(_text, "UTF-16");
                TextView text = (TextView) findViewById(R.id.TesttextView);
                text.setText(_textString);
            }
            catch (IOException e){
                Log.d("Log_03", "Ошибка считывания.");
            }
        }
        catch (Exception e){
            Log.d("Lab_03", "Не удалось записать данные");
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

    int hashCode(String key){
        char[] arr = new char[key.length()];
        arr = key.toCharArray();
        int number = 0;
        for(int f = 0; f < arr.length; f++)
            number += arr[f];
        return number % 10;
    }

    boolean writeData(String key, String value, File file, int number){
        try {
            RandomAccessFile raFile = new RandomAccessFile(file, "rw");
            int numberStr = number; //hashCode(key);
            raFile.seek(numberStr * 36);
            raFile.writeChars(key + value);
            raFile.close();
            Log.d("Log_03", "Данные успешно записаны.");
        }
        catch (IOException e){
            Log.d("Log_03", e.getMessage());
        }
        return true;
    }

}
