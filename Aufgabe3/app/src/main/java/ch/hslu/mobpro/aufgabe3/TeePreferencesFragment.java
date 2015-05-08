package ch.hslu.mobpro.aufgabe3;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Simon on 21.03.2015.
 */
public class TeePreferencesFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefrences);
    }
}
