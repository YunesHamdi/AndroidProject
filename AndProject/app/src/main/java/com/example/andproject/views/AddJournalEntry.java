package com.example.andproject.views;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.andproject.R;

public class AddJournalEntry extends AppCompatActivity {
    public static final String KEY_ID =
            "com.example.andproject.KEY_ID";
    public static final String KEY_TITLE =
            "com.example.andproject.KEY_TITLE";
    public static final String KEY_TEXT =
            "com.example.andproject.KEY_TEXT";
    public static final String KEY_DATE =
            "com.example.andproject.KEY_DATE";

    private EditText Title;
    private EditText NoteText;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journalentry);

        Title = findViewById(R.id.edit_title);
        NoteText = findViewById(R.id.edit_text);

        Toolbar toolbar = findViewById(R.id.toolbar_AddNote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent.hasExtra(KEY_ID)) {
            setTitle("Edit journal entry");
            Title.setText(intent.getStringExtra(KEY_TITLE));
            NoteText.setText(intent.getStringExtra(KEY_TEXT));
        } else
            setTitle("Add journal entry");
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note_menu, menu);
        return true;
    }

    private void saveNote() {
        String title = Title.getText().toString();
        String text = NoteText.getText().toString();

        if (title.trim().isEmpty() || text.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and text", Toast.LENGTH_LONG).show();
            return;
        }
        Intent notedata = new Intent();
        notedata.putExtra(KEY_TITLE, title);
        notedata.putExtra(KEY_TEXT, text);
        int id = getIntent().getIntExtra(KEY_ID, -1);
        if (id != -1) {
            notedata.putExtra(KEY_ID, id);
        }
        setResult(RESULT_OK, notedata);
        finish();

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                finish();
                return true;
        }
    }
}
