package ch.hslu.mobpro.aufgabe3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.content.SharedPreferences.Editor;


public class MainActivity extends Activity {

    SharedPreferences privatePreferences;
    Button buttonEditPrefs;
    Button buttonSetDefaultPrefs;
    int calledAppCounter;
    TextView calledTextCounter;
    TextView teaPreferencesText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calledTextCounter = (TextView) findViewById(R.id.activity_main_calledText);
        teaPreferencesText = (TextView) findViewById(R.id.activity_main_teepreftext);
        privatePreferences = getPreferences(Context.MODE_PRIVATE);
        buttonEditPrefs = (Button) findViewById(R.id.activity_main_button_editPref);

        buttonEditPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTeaPreferences();
            }
        });
        buttonSetDefaultPrefs = (Button) findViewById(R.id.activity_main_button_setDefaultPrefs);
        buttonSetDefaultPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefaultTeaPreferences();
                setTeaPreferenceText();
            }
        });

    }

    public void showTeaPreferences()
    {
        Intent intent = new Intent(this, TeePreferencesActivity.class);
        startActivity(intent);
    }

    private void setDefaultTeaPreferences()
    {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Editor editor = preferences.edit();
        editor.clear();
        editor.putBoolean(Constants.TeaSweetenerEnabled, true);
        editor.putString(Constants.TeaSweetener,"natural");
        editor.putString(Constants.TeaFavourite,"Luzerner chrütertee");
        editor.commit();
    }

    private void setAppCounterText()
    {
        String text  = getString(R.string.textCounter);
        text = String.format(text, calledAppCounter);
        calledTextCounter.setText(text);

    }

    @Override
    protected void onResume() {
        super.onResume();
        calledAppCounter = privatePreferences.getInt(Constants.AppCounterKey,1);
        calledAppCounter++;
        setTeaPreferenceText();
        setAppCounterText();
    }

    private void setTeaPreferenceText()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean likeSweetenedTea = preferences.getBoolean(Constants.TeaSweetenerEnabled,true);
        String favouriteTea = preferences.getString(Constants.TeaFavourite,"");
        String text = String.format("I like natural tea and my favourites are %s",favouriteTea);
        if(likeSweetenedTea) {
            String sweetener = preferences.getString(Constants.TeaSweetener, "natural");
            text = String.format("I like sweetened tea with %s sugar and my favourites are %s",sweetener,favouriteTea);

        }
        teaPreferencesText.setText(text);


    }

    @Override
    protected void onPause() {
        super.onPause();
        Editor editor = privatePreferences.edit();
        editor.putInt(Constants.AppCounterKey,calledAppCounter);
        editor.apply();
    }


    public void onResetClicked(View button) {

        calledAppCounter = 1;
        Editor editor = privatePreferences.edit();
        editor.putInt(Constants.AppCounterKey,calledAppCounter);
        editor.apply();
        setAppCounterText();

    }

    public void onEditNoteClicked(View button)
    {
        Intent intent = new Intent(this,NoteActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
