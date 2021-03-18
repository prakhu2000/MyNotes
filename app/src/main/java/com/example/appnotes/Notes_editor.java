package com.example.appnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

public class Notes_editor extends AppCompatActivity {
   int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_editor);
        EditText editText=findViewById(R.id.editText);
        Intent intent=getIntent();
        noteId=intent.getIntExtra("NoteId",-1);
        if(noteId!=-1){
            editText.setText(MainActivity.notes.get(noteId));
        }
        else {
            MainActivity.notes.add("");
            noteId=MainActivity.notes.size()-1;
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//Log.i("changed",String.valueOf(s));
MainActivity.notes.set(noteId,String.valueOf(s));
MainActivity.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.example.appnotes", Context.MODE_PRIVATE);
                Set<String> set=new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}