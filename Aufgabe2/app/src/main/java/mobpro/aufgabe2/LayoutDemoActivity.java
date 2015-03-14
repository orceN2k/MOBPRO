package mobpro.aufgabe2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by orceN on 14.03.2015.
 */
public class LayoutDemoActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        boolean isLinear = intent.getBooleanExtra("isLinear", true);
        if (isLinear) {
            setContentView(R.layout.layoutdemo_linearlayout);
        } else {
            setContentView(R.layout.layoutdemo_relativelayout);
        }
    }
}
