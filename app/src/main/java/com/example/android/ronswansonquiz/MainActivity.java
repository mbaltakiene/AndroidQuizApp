package com.example.android.ronswansonquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    RadioButton radioBeef, radioTurkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // make radio buttons for burger question mutually exclusive
        radioBeef = (RadioButton) findViewById(R.id.radio_beef);
        radioTurkey = (RadioButton) findViewById(R.id.radio_turkey);

        radioBeef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioTurkey.setChecked(false);
            }
        });
        radioTurkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioBeef.setChecked(false);
            }
        });
    }

    /**
     * This method opens a new activity on a new screen
     */
    public void submit(View view) {
        int score = calculateScore();
        //open a new screen
        Intent myIntent = new Intent(view.getContext(), ResultActivity.class);
        myIntent.putExtra("score", score);
        startActivityForResult(myIntent, 0);
    }

    public int calculateScore() {
        //get the results form the selected answers
        int score = 0;
        boolean radioBeef = ((RadioButton) findViewById(R.id.radio_beef)).isChecked();
        boolean radioSilence = ((RadioButton) findViewById(R.id.radio_silence)).isChecked();
        String answerGovernment = ((EditText) findViewById(R.id.government_answer)).getText().toString();
        boolean checkChristmas = ((CheckBox) findViewById(R.id.checkbox_christmas)).isChecked();
        boolean checkBirthday = ((CheckBox) findViewById(R.id.checkbox_birthday)).isChecked();
        boolean checkValentines = ((CheckBox) findViewById(R.id.checkbox_valentines_day)).isChecked();
        boolean checkLabor = ((CheckBox) findViewById(R.id.checkbox_labor_day)).isChecked();

        //calculate correct answers
        if (radioSilence) {
            score += 1;
        }
        if (radioBeef) {
            score += 1;
        }
        if (checkBirthday && !checkChristmas && !checkValentines && !checkLabor) {
            score += 1;
        }
        if (answerGovernment.toUpperCase().equals(getString(R.string.it_doesnt).toUpperCase()) ||
                answerGovernment.toUpperCase().equals(getString(R.string.it_doesnt2).toUpperCase())) {
            score += 1;
        }
        return score;
    }
}
