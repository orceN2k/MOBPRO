package ch.hslu.mobpro.aufgabe3;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Simon on 21.03.2015.
 */
public class TeePreferencesActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content,
               new TeePreferencesFragment()).commit();
    }
}
