package ch.hslu.mobpro.aufgabe3;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by Simon on 23.03.2015.
 */
public class NoteActivity extends Activity {

    CheckBox checkBoxSaveExternal;
    EditText textViewNoteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        checkBoxSaveExternal = (CheckBox) findViewById(R.id.activity_note_checkBox_saveexternal);
        textViewNoteText = (EditText) findViewById(R.id.activity_note_notetext);
    }

        @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private File GetFileDirectoryContext()
    {
        File directory;
        if(checkBoxSaveExternal.isChecked())
        {
            directory = Environment.getExternalStorageDirectory();
        }
        else
        {
            directory = this.getApplicationContext().getFilesDir();
        }
        return  directory;
    }

    public void onSaveClicked(View button)
    {

        String noteText = textViewNoteText.getText().toString();
        File file = new File(GetFileDirectoryContext(), Constants.NoteFileName);
        Writer writer = null;
        try
        {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(noteText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("HSLU-MobPro-Persistenz","Got a problem while writing file");
        }
        finally {
            if(writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("HSLU-MobPro-Persistenz","Got a problem while writing file");
                }
        }

    }


    public void onLoadClicked(View button) {

        String noteText = "";
        File file = new File(GetFileDirectoryContext(), Constants.NoteFileName);
        BufferedReader reader;
        try {
            StringBuilder fileData = new StringBuilder();
            reader = new BufferedReader(new FileReader(file));
            char[] buf = new char[1024];
            int numRead;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
            }
            reader.close();
            noteText = fileData.toString();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("HSLU-MobPro-Persistenz", "Got a problem while reading file");
        }
        textViewNoteText.setText(noteText);
    }


}
