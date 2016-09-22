package by.bstu.sinjakkirill.basejava.lab_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
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
        //f.delete();

        /*String key = "12345";
        int k = key.hashCode();
        inputValue.setText(k);*/


        if(!ExistBase(fileName)){
            try{
                f.createNewFile();
                Log.d("Log_03", "Файл " + fileName + " успешно создан");
            }
            catch (IOException e){
                Log.d("Log_03", "Не удалось создать файл " + fileName);
            }
        }
        try {
            RandomAccessFile rafile = new RandomAccessFile(f, "rw");
            //rafile.seek(30);
            int i = 99;
            rafile.writeChars("12345kirillsinj" + i);
            rafile.seek(30);
            rafile.writeChars("0" + i);
            rafile.close();
            RandomAccessFile rafile1 = new RandomAccessFile(f, "rw");

            byte[] b = new byte[(int)rafile1.length()];
            byte[] b1 = new byte[6];

            rafile1.read(b);

            rafile1.seek(30);
            rafile1.read(b1);

            String sss = new String(b, "UTF-16");

            inputValue.setText(sss);

            String sss1 = new String(b1, "UTF-16");
            int key;
            key = Integer.parseInt(sss1);
            EditText outKey = (EditText) findViewById(R.id.outputKeyEditText);
            outKey.setText(Integer.toString(key));

            int q = (int)rafile1.length();
            inputKey.setText(Integer.toString(q));
            rafile.close();

            /////////////////////////
            EditText outText = (EditText) findViewById(R.id.outputValueeditText);
            char[] d = new char[3];
            d[0] = 'a';
            d[1] = 'f';
            d[2] = 'q';
            int ppp = d[0] + d[1] + d[2];
            outText.setText(Integer.toString(hashCode("afq")));
            String s = new String(outText.getText().toString());
            /////////////////////////

            Log.d("Lab_03", "Данные успешно записаны " + sss);
        }
        catch (IOException e){
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

}
