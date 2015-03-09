package ch.hslu.mobpro.firstApp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


/**
 * The main activity, displays some buttons.
 *
 * @author Simon Birrer
 */

public class MainActivity extends Activity {

	private static final int REQUEST_ID = 105;
    private String CurrentUrl  = "http://www.hslu.ch";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onStart() {
		super.onStart();
        UpdateURLText();
	}

    public void startLogActivity(View v) {
        Intent startLifeCycleActivity = new Intent(this,LifecycleLogActivity.class);
        startActivity(startLifeCycleActivity);
    }

    public void startBrowser(View v) {
        Intent startBrowserIntent = new Intent(Intent.ACTION_VIEW);
        startBrowserIntent.setData(Uri.parse(CurrentUrl));
        startActivity(startBrowserIntent);
    }

    public void startQuestionActivity(View v) {
        Intent startQuestion = new Intent(this,QuestionActivity.class);
        startActivityForResult(startQuestion,REQUEST_ID);
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Use return data and print to log
		if (requestCode == REQUEST_ID && resultCode == RESULT_OK) {
			TextView textView = (TextView) findViewById(R.id.main_textView_result);
			String answer = getResources().getString(R.string.main_text_gotAnswer) + "'" + data.getExtras().getString("answer") + "'";
            textView.setText(answer);

            String newUrl = data.getExtras().getString("newURL");
            if(!newUrl.isEmpty())
            {
                CurrentUrl = newUrl;
                UpdateURLText();
            }
        }
	}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("URL",CurrentUrl);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        CurrentUrl = savedInstanceState.getString("URL");
        UpdateURLText();
    }

    private  void UpdateURLText()
    {
        TextView urlView = (TextView) findViewById(R.id.main_url_result);
        urlView.setText(CurrentUrl);
    }

}
