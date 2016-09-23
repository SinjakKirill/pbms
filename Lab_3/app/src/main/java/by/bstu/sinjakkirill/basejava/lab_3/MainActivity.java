package by.bstu.sinjakkirill.basejava.lab_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
            /*RandomAccessFile rafile = new RandomAccessFile(f, "rw");
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
            rafile.close();*/

            /////////////////////////
            EditText outText = (EditText) findViewById(R.id.outputValueeditText);
            /*String _str1 = new String("kkkkk");    // hashCode = 9;
            String _str2 = new String("mmmmm");    // hashCode = 4;
            String _str3 = new String("sssss");    // hashCode
            String _str4 = new String("ddddd");    // hashCode
            String _str5 = new String("qqqqq");    // hashCode

            int number1 = hashCode(_str1);
            int number2 = hashCode(_str2);
            int number3 = hashCode(_str3);
            int number4 = hashCode(_str4);
            int number5 = hashCode(_str5);*/

            writeData("kiri1", "1234567890", f, 0);
            writeData("marg2", "0987654321", f, 1);
            writeData("kiri3", "1234567890", f, 2);
            writeData("marg4", "0987654321", f, 3);
            writeData("kiri5", "1234567890", f, 4);
            writeData("marg6", "0987654321", f, 5);
            writeData("kiri7", "1234567890", f, 6);
            writeData("marg8", "0987654321", f, 7);
            writeData("kiri9", "1234567890", f, 8);
            writeData("mar10", "0987654321", f, 9);
            //outText.setText(number1 + " " + number2 + " " + number3 + " " + number4 + " " + number5);

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


            /*char[] d = new char[3];
            d[0] = 'a';
            d[1] = 'f';
            d[2] = 'q';
            int ppp = d[0] + d[1] + d[2];
            outText.setText(Integer.toString(hashCode("afq")));
            String s = new String(outText.getText().toString());*/
            /////////////////////////

            //Log.d("Lab_03", "Данные успешно записаны " + sss);
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
