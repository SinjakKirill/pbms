package com.example.sinyakkirill.lab_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Используем объект CalendarView:
    CalendarView mCalendarView;
    Button addNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Связываемся с нашим календариком:
        mCalendarView = (CalendarView)findViewById(R.id.calendarView);

        //Настраиваем слушателя смены даты:
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            // Описываем метод выбора даты в календаре:
            @Override
            public void onSelectedDayChange(CalendarView view, int year,int month, int dayOfMonth) {

                // При выборе любой даты отображаем Toast сообщение с данными о выбранной дате (Год, Месяц, День):
                Toast.makeText(getApplicationContext(),
                        "Год: " + year + "\n" + "Месяц: " + month + "\n" + "День: " + dayOfMonth,
                        Toast.LENGTH_SHORT).show();

                addNote = (Button) findViewById(R.id.buttonAddNote);
                Button button = (Button) findViewById(R.id.button);
                //addNote.setVisibility(View.INVISIBLE);
                //button.setPadding(100, 100, 0, 0);
                //addNote.setEnabled(false);
                //button.



            }
        });
    }


}
