package com.example.andproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andproject.R;
import com.example.andproject.adapters.NoteAdapter;
import com.example.andproject.models.Note;
import com.example.andproject.viewmodels.JournalFragmentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class JournalFragment extends Fragment {
    View v;
    RecyclerView notesList;
    TextView label;
    private JournalFragmentViewModel mJournalFragmentViewModel;
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;


    public JournalFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.journal_fragment, container, false);
        notesList = v.findViewById(R.id.rv2);
        label = v.findViewById(R.id.journalLabel);
        FloatingActionButton buttonAddNote = v.findViewById(R.id.button_add_note);

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddJournalEntry.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);

            }
        });
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notesList.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        notesList.hasFixedSize();

        final NoteAdapter adapter = new NoteAdapter();
        notesList.setAdapter(adapter);
        mJournalFragmentViewModel.getAllNotes().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                adapter.setNotes(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mJournalFragmentViewModel.delete(adapter.getNoteAtPosition(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "The note has been deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(notesList);
        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(getActivity(), AddJournalEntry.class);
                intent.putExtra(AddJournalEntry.KEY_TITLE, note.getTitle());
                intent.putExtra(AddJournalEntry.KEY_TEXT, note.getText());
                intent.putExtra(AddJournalEntry.KEY_DATE, note.getDate());
                intent.putExtra(AddJournalEntry.KEY_ID, note.getId());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });
    }

    private static Date getDate(int difAmount) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MILLISECOND, difAmount);
        return calendar.getTime();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent notedata) {
        super.onActivityResult(requestCode, resultCode, notedata);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == AddJournalEntry.RESULT_OK) {
            String title = notedata.getStringExtra(AddJournalEntry.KEY_TITLE);
            String text = notedata.getStringExtra(AddJournalEntry.KEY_TEXT);
            Date date = getDate(1);

            Note note = new Note(title, text, date);
            mJournalFragmentViewModel.insert(note);

            Toast.makeText(getActivity(), "Note saved", Toast.LENGTH_LONG).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == AddJournalEntry.RESULT_OK) {
            int id = notedata.getIntExtra(AddJournalEntry.KEY_ID, -1);
            if (id == -1) {
                Toast.makeText(getActivity(), "Note could not be updated", Toast.LENGTH_LONG).show();
            }

            String title = notedata.getStringExtra(AddJournalEntry.KEY_TITLE);
            String text = notedata.getStringExtra(AddJournalEntry.KEY_TEXT);
            Date date = getDate(1);

            Note note = new Note(title, text, date);
            note.setId(id);
            mJournalFragmentViewModel.update(note);
            Toast.makeText(getActivity(), "Note updated", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getActivity(), "Note not saved", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJournalFragmentViewModel = new ViewModelProvider(this).get(JournalFragmentViewModel.class);
    }

}
