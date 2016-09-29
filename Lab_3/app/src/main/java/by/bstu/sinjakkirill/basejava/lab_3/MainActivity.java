package by.bstu.sinjakkirill.basejava.lab_3;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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
        EditText inputKey = (EditText) findViewById(R.id.inputKeyeditText);
        EditText inputValue = (EditText) findViewById(R.id.inputValueeditText);
        final String fileName = "Lab_03.txt";
        File f = new File(super.getFilesDir(), fileName);
        //f.delete();
        if(inputKey.getText().length() == 5 && inputValue.getText().length() == 10){
            if(!ExistBase(fileName)){
                try{
                    //создание файла, если он не существует
                    f.createNewFile();
                    RandomAccessFile rf = new RandomAccessFile(f, "rw");
                    for (int i = 0; i < 180; i++) {
                        rf.writeChar('0');
                    }
                    rf.close();
                    writeData(inputKey.getText().toString(), inputValue.getText().toString(), f);
                    Log.d("Log_03", "Файл " + fileName + " успешно создан");
                }
                catch (IOException e){
                    Log.d("Log_03", "Не удалось создать файл " + fileName);
                }
            }
            else{
                   writeData(inputKey.getText().toString(), inputValue.getText().toString(), f);
                   Log.d("Log_03", "Данные записаны в таблицу");
            }
        }
        else{
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Incorrekt data!\nKey(5 symbol) Value(10 symbol)").setPositiveButton("Да", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d("Log_02", "Неверный формат введенных данных.");
                }
            });
            AlertDialog ab = b.create();
            ab.show();
        }

        //вывод таблицы в TextView
        try{
            RandomAccessFile rf = new RandomAccessFile(f, "rw");
            byte[] _text = new byte[(int)f.length()];
            rf.read(_text);
            String _textString = new String(_text, "UTF-16");
            TextView text = (TextView) findViewById(R.id.TesttextView);
            text.setText(_textString);
        }
        catch (IOException e){
            Log.d("Log_03", "Ошибка считывания.");
        }
    }

    //проверка существования файла
    private boolean ExistBase(String fname){
        boolean rc = false;
        File f = new File(super.getFilesDir(), fname);
        if(rc = f.exists())
            Log.d("Log_02", "Файл " + fname + " существует");
        else Log.d("Log_02", "Файл " + fname + " не найден");
        return rc;
    }

    //вычисление хэш-кода
    int hashCode(String key){
        char[] arr = new char[key.length()];
        arr = key.toCharArray();
        int number = 0;
        for(int f = 0; f < arr.length; f++)
            number += arr[f];
        return number % 10;
    }

    //запись данных в таблицу
    boolean writeData(String key, String value, File file){
        try {
            RandomAccessFile raFile = new RandomAccessFile(file, "rw");
            int numberStr = hashCode(key);
            raFile.seek(numberStr * 36);
            byte[] checkString = new byte[10];
            raFile.read(checkString);
            String _checkString = new String(checkString, "UTF-16");
            Log.d("Log_03", "String _checkString: " + _checkString);
            if(_checkString.equals("00000")){
                Log.d("Log_03", "Процес записи: " + key + " " + value);
                raFile.seek(numberStr * 36);
                raFile.writeChars(key + value);
            }
            else{
                int point = numberStr * 36;
                boolean numb = true;
                //boolean checkWrite = false;
                while (numb){
                    //проверка на совпадение ключей
                    byte[] checkKey = new byte[10];
                    raFile.seek(point);
                    raFile.read(checkKey);
                    String qwe = new String(checkKey, "utf-16");
                    if(qwe.equals(key)){
                        raFile.seek(point + 10);
                        raFile.writeChars(value);
                        //checkWrite = true;
                        numb = false;
                        break;
                    }
                        int twoPoint;
                        raFile.seek(point + 30);
                        byte[] pointByte = new byte[6];
                        raFile.read(pointByte);
                        twoPoint = Integer.valueOf(new String(pointByte, "utf-16"));
                        if (twoPoint == 0) {
                            raFile.seek(point + 30);
                            raFile.writeChars(Integer.toString((int) raFile.length()));
                            raFile.seek((int)raFile.length());
                            raFile.writeChars(key + value + "000");
                            Log.d("Log_03", "Значение последнего указателя в списке: " + point);
                            numb = false;
                        } else {
                            point = twoPoint;
                        }
                    }
                /*if(!checkWrite){
                    raFile.seek((int)raFile.length());
                    raFile.writeChars(key + value + "000");
                }*/
                //raFile.seek((int)raFile.length());
                //raFile.writeChars(key + value + "000");
            }
            raFile.close();
            Log.d("Log_03", "Данные успешно записаны.");
        }
        catch (IOException e){
            Log.d("Log_03", e.getMessage());
            return false;
        }
        return true;
    }

    boolean readData(String key, File file) {
        return true;
    }

}
