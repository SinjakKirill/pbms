package by.bstu.sinjakkirill.basejava.lab_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

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
            rafile.seek(24);
            rafile.writeChars("Hello kirill");
            rafile.close();
            RandomAccessFile rafile1 = new RandomAccessFile(f, "rw");

            byte[] b = new byte[(int)rafile1.length()];
            rafile1.read(b);
            String sss = new String(b, "UTF-16");
            inputValue.setText(sss);
            rafile.close();
            Log.d("Lab_03", "Данные успешно записаны " + sss);
        }
        catch (IOException e){
            Log.d("Lab_03", "Не удалось записать данные");
        }
        //String key = inputKey.getText().toString();
        //inputValue.setText(key);
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
