package com.example.sinyakkirill.lab_4;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by Sinyak Kirill on 13.10.2016.
 */

public class Note {

    public Note(String notes, String date){
        this.mDate = date;
        this.mNotes = notes;
    }

    protected String mNotes;
    protected String mDate;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }

}
