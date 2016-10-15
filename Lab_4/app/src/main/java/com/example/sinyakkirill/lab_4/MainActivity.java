package com.example.sinyakkirill.lab_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Используем объект CalendarView:
    CalendarView mCalendarView;
    Button addNote;
    Gson gson;
    AutoCompleteTextView notesTextView;
    String d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File f = new File(super.getFilesDir(), "notes.json");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Связываемся с нашим календариком:
        mCalendarView = (CalendarView)findViewById(R.id.calendarView);

        //Настраиваем слушателя смены даты:
        /*mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            // Описываем метод выбора даты в календаре:
            @Override
            public void onSelectedDayChange(CalendarView view, int year,int month, int dayOfMonth) {

                // При выборе любой даты отображаем Toast сообщение с данными о выбранной дате (Год, Месяц, День):
                Toast.makeText(getApplicationContext(),
                        "Год: " + year + "\n" + "Месяц: " + month + "\n" + "День: " + dayOfMonth,
                        Toast.LENGTH_SHORT).show();

                addNote = (Button) findViewById(R.id.buttonAddNote);
                Button button = (Button) findViewById(R.id.button);



            }
        });*/
    }

    public void saveNote(View view){
        String fileName = new String("notes.json");
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        notesTextView = (AutoCompleteTextView) findViewById(R.id.NoteTextView);
        List<Note> notes = getListNotes(fileName);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            // Описываем метод выбора даты в календаре:
            @Override
            public void onSelectedDayChange(CalendarView view, int year,int month, int dayOfMonth) {
                d = Integer.toString(year) + "." + Integer.toString(year) + "." + Integer.toString(year);
            }
        });
        if(notes == null){

        }
        else{

        }
        d = null;
    }

    public void deleteNote(View view){
        String fileName = new String("notes.json");
        File fileJson = new File(super.getFilesDir(), fileName);
        gson = new Gson();
        String jsonStrNotes = new String();
    }

    public List<Note> getListNotes(String pathName){
        File file = new File(super.getFilesDir(), pathName);
        Type type = new TypeToken<ArrayList<Note>>(){}.getType();
        String jsonStrNotes = new String();
        List<Note> notes;
        gson = new Gson();
        try {
            FileReader fileReader = new FileReader(file);
            char[] chars = new char[(int)file.length()];
            fileReader.read(chars);
            jsonStrNotes = new String(chars);
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(jsonStrNotes.length() == 0){
            return null;
        }
        else{
            return notes = gson.fromJson(jsonStrNotes, type);
        }
    }

}
