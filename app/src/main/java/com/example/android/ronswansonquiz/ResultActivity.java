package com.example.android.ronswansonquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        String evaluation;
        ImageView img = (ImageView) findViewById(R.id.image_result);
        // get the score form the quiz results of the previous screen
        int score = getIntent().getIntExtra("score", 0);
        ((TextView) findViewById(R.id.score)).setText(String.valueOf(score));
        // the score is low
        if (score == 0 || score == 1) {
            evaluation = getString(R.string.result_failed);
            img.setImageResource(R.drawable.failed);
        }
        // the score is maximum
        else if (score == 4) {
            evaluation = getString(R.string.result_well_done);
            img.setImageResource(R.drawable.welldone);
        }
        // the score is average
        else {
            evaluation = getString(R.string.result_not_good_enough);
            img.setImageResource(R.drawable.notgood);
        }
        ((TextView) findViewById(R.id.text_evaluation)).setText(String.valueOf(evaluation));
    }

    /**
     * This method restarts the quiz by launching the initial screen of the app
     */
    public void startOver(View view) {
        Intent myIntent = new Intent(view.getContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
    }


}