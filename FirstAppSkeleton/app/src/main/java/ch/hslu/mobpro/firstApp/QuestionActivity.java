package ch.hslu.mobpro.firstApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Asks the user a question and returns the answer to the caller.
 *
 * @author Ruedi Arnold
 */
public class QuestionActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        registerListeners();
        
        // Read parameter and show on view.
        String question = getIntent().getStringExtra("question");
        setQuestionText(question);
    }

    private void buttonClicked() {
       	// Read answer and set result on view.
    	String answer = getAnswerText();
    	
    	Intent answerData = new Intent();
    	answerData.putExtra("answer", answer);
        answerData.putExtra("newURL",getNewURL());
    	setResult(RESULT_OK, answerData);
    	
    	finish();
    }

    // ---------------- Helper Methods ------------------
    
	private void registerListeners() {
		/*
		 *  Here you see a second way for how to get the click of a button.
		 *  Namely using a Listener, which is attached to the button view.
		 *  (This is an alternative to onClick="methodenName" from the
		 *  layout xml of the MainActivity.)
		 */
		
		Button button = (Button) findViewById(R.id.question_button_done);
        button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				buttonClicked();
			}
		});
	}

	private void setQuestionText(String question) {
		TextView textView = (TextView) findViewById(R.id.question_label);
		textView.setText(question);
	}
	
	private String getAnswerText() {
		EditText editText = (EditText) findViewById(R.id.question_text_answer);
		String answerText = editText.getText().toString();
		return answerText;
	}

    private  String getNewURL(){
        EditText editText = (EditText) findViewById(R.id.url_text);
        String answerText = editText.getText().toString();
        return answerText;
    }
	
}