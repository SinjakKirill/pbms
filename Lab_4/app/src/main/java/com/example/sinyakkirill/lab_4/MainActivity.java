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
import java.io.FileWriter;
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
    String selectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*File f = new File(super.getFilesDir(), "notes.json");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // Связываемся с нашим календариком:
        mCalendarView = (CalendarView)findViewById(R.id.calendarView);

        //Настраиваем слушателя смены даты:
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            // Описываем метод выбора даты в календаре:
            @Override
            public void onSelectedDayChange(CalendarView view, int year,int month, int dayOfMonth) {

                // При выборе любой даты отображаем Toast сообщение с данными о выбранной дате (Год, Месяц, День):
                /*Toast.makeText(getApplicationContext(),
                        "Год: " + year + "\n" + "Месяц: " + month + "\n" + "День: " + dayOfMonth,
                        Toast.LENGTH_SHORT).show();*/
                notesTextView = (AutoCompleteTextView) findViewById(R.id.NoteTextView);
                notesTextView.setText("add note...");
                String nameFile = new String("notes.json");
                ArrayList<Note> noteList = getListNotes(nameFile);
                selectDate = Integer.toString(year)
                        + "." + Integer.toString(month)
                        + "." + Integer.toString(dayOfMonth);
                if(noteList != null){
                    for (Note note :
                            noteList) {
                        if (note.mDate.equals(selectDate)){
                            notesTextView.setText(note.mNotes);
                        }
                    }
                }
            }
        });
    }

    public void saveNote(View view){
        String fileName = new String("notes.json");
        notesTextView = (AutoCompleteTextView) findViewById(R.id.NoteTextView);
        if(notesTextView.getText().toString().equals("add note...")){
            Toast.makeText(getApplicationContext(),
                    "Added note!",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            gson = new Gson();
            String strNote = new String();
            ArrayList<Note> noteList = new ArrayList<>();
            ArrayList<Note> notes = getListNotes(fileName);
            if (notes == null) {
                Note note = new Note(notesTextView.getText().toString(), selectDate);
                noteList.add(note);
                strNote = gson.toJson(noteList);
                File file = new File(super.getFilesDir(), "notes.json");
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(strNote);
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),
                        strNote,
                        Toast.LENGTH_SHORT).show();
            }
            else{
                boolean point = false;
                for (Note note :
                        notes) {
                    if(note.mDate.equals(selectDate))
                    {
                        note.mNotes = notesTextView.getText().toString();
                        point = true;
                    }
                }
                if(point){
                    File file = new File(super.getFilesDir(), fileName);
                    try {
                        FileWriter fileWriter = new FileWriter(file);
                        String strNotes = gson.toJson(notes);
                        fileWriter.write(strNotes);
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Note note = new Note(notesTextView.getText().toString(), selectDate);
                    notes.add(note);
                    String newStrNote = gson.toJson(notes);
                    File file = new File(super.getFilesDir(), fileName);
                    try {
                        FileWriter fileWriter = new FileWriter(file);
                        fileWriter.write(newStrNote);
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    /*Toast.makeText(getApplicationContext(),
                            "Будет создан новый Note!",
                            Toast.LENGTH_SHORT).show();*/
                }
            }
        }


        /*File file = new File(super.getFilesDir(), "notes.json");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void deleteNote(View view){
        String fileName = new String("notes.json");
        notesTextView = (AutoCompleteTextView) findViewById(R.id.NoteTextView);
        if(notesTextView.getText().toString().equals("add note...")){
            Toast.makeText(getApplicationContext(),
                    "Added note!",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            gson = new Gson();
            ArrayList<Note> noteArrayList = getListNotes(fileName);
            int index = 0;
            if(noteArrayList != null){
                for(int i = 0; i < noteArrayList.size(); i++){
                    if(noteArrayList.get(i).mDate == selectDate)
                        index = i;
                }
                noteArrayList.remove(index);
                String strNotes = gson.toJson(noteArrayList);
                File file = new File(super.getFilesDir(), fileName);
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(strNotes);
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                notesTextView.setText("add note...");
            }
            else{
                Toast.makeText(getApplicationContext(),
                        "Нет сохраненных записей!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public ArrayList<Note> getListNotes(String pathName){
        File file = new File(super.getFilesDir(), pathName);
        Type type = new TypeToken<ArrayList<Note>>(){}.getType();
        String jsonStrNotes = new String();
        ArrayList<Note> notes;
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
