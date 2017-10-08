package com.mashjulal.android.simplexmethodresolver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private List<Equation> mEquations = new ArrayList<>();
    private EquationItemRecyclerViewAdapter mRecyclerViewAdapter;
    private static int[] sEquationSpinnerEntries;
    private static int[] sVariableSpinnerEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup recycler view and it's adapter
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_aMain_equations);
        mRecyclerViewAdapter= new EquationItemRecyclerViewAdapter(this, mEquations);
        recyclerView.setAdapter(mRecyclerViewAdapter);

        // Load from resources content for arrays
        sEquationSpinnerEntries = getResources().getIntArray(R.array.spinner_number_of_equations);
        sVariableSpinnerEntries = getResources().getIntArray(R.array.spinner_number_of_variables);

        initSpinners();
    }

    private void initSpinners() {
        // Init equation number spinner and set listener for selected item
        Spinner spnrEquations = (Spinner) findViewById(R.id.spnr_aMain_numEquations);
        spnrEquations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int count = sEquationSpinnerEntries[position];
                // if selected number is bigger than actual equation list size
                if (count > mEquations.size()) {
                    while (count > mEquations.size()) {
                        // add new equations with zero coefficients and zero value
                        // TODO: add zero filling for every new equation
                        mEquations.add(new Equation());
                    }
                    // else - remove last equations while equation
                    // list size is bigger than selected number
                } else {
                    while (count > mEquations.size()) {
                        mEquations.remove(mEquations.size() - 1);
                    }
                }
                // refresh recycler view
                mRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Init variable number spinner and set listener for selected item
        Spinner spnrVariables = (Spinner) findViewById(R.id.spnr_aMain_numVaiables);
        spnrVariables.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int count = sVariableSpinnerEntries[position];
                // TODO: what's lower
                // if count is bigger than variable count in every equation
                // then add zeroes to every equation
                // else - remove extra coefficients from every equation

                // refresh recycler view
                mRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
