package com.example.andproject.repositories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.andproject.adapters.DateConverter;
import com.example.andproject.models.Note;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Database(entities = {Note.class}, version = 1)
@TypeConverters({DateConverter.class})

public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Welcome to your journal", "you can swipe right on this note to delete it", getDate(1)));
            return null;
        }

        private static Date getDate(int difAmount) {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.add(Calendar.MILLISECOND, difAmount);
            return calendar.getTime();

        }
    }
}