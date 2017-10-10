package com.mashjulal.android.simplexmethodresolver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SolutionActivity extends AppCompatActivity {

    public static final String PARAM_SOLUTION = "param_solution";

    private String solution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);

        if (getIntent() == null)
            finish();

        solution = getIntent().getStringExtra(PARAM_SOLUTION);
        TextView textView = (TextView) findViewById(R.id.tv_aSolution_solution);
        textView.setText(solution);
    }
}
