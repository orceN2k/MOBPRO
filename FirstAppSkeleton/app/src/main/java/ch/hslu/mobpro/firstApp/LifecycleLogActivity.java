package ch.hslu.mobpro.firstApp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Logs lifecycle transitions into the LogCat view of the ADT-Debugger.
 *
 * @author Simon Birrer
 */

public class LifecycleLogActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lifecycle_logger);
        Log.i("hslu_mobApp", "onCreate() aufgerufen");
    }

    @Override
    public  void onStart()
    {
        super.onStart();
        Log.i("hslu_mobApp", "onStart() aufgerufen");
    }

    @Override
    public  void onResume()
    {
        super.onResume();
        Log.i("hslu_mobApp", "onResume() aufgerufen");
    }

    @Override
    public  void onPause()
    {
        super.onPause();
        Log.i("hslu_mobApp", "onPause() aufgerufen");
    }

    @Override
    public  void onStop()
    {
        super.onStop();
        Log.i("hslu_mobApp", "onStop() aufgerufen");
    }
    @Override
    public  void onDestroy()
    {
        super.onDestroy();
        Log.i("hslu_mobApp", "onDestroy() aufgerufen");
    }



}

