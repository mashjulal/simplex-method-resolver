package com.mashjulal.android.simplexmethodresolver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<InputRow> mInput = new ArrayList<>();
    private InputRowItemRecyclerViewAdapter mRecyclerViewAdapter;
    private int equationCount = 2;
    private int variableCount = 2;
    private static String[] sEquationSpinnerEntries;
    private static String[] sVariableSpinnerEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup recycler view and it's adapter
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_aMain_inputRows);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewAdapter= new InputRowItemRecyclerViewAdapter(this, mInput);
        recyclerView.setAdapter(mRecyclerViewAdapter);

//         Load from resources content for arrays
        sEquationSpinnerEntries = getResources().getStringArray(R.array.spinner_number_of_equations);
        sVariableSpinnerEntries = getResources().getStringArray(R.array.spinner_number_of_variables);

        addRows();
        initSpinners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    private void initSpinners() {
        // Init equation number spinner and set listener for selected item
        Spinner spnrEquations = (Spinner) findViewById(R.id.spnr_aMain_numEquations);
        spnrEquations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                equationCount = Integer.valueOf(sEquationSpinnerEntries[position]);
                if (equationCount> mInput.size()) {
                    addRows();
                } else {
                    removeRows();
                }
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
                variableCount = Integer.valueOf(sVariableSpinnerEntries[position]);
                if (variableCount > mInput.get(0).getVariables().size()) {
                    addVariables();
                } else {
                    removeVariables();
                }
                // refresh recycler view
                mRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void addRows() {
        while (mInput.size() < equationCount) {
            InputRow inputRow = new InputRow();
            for (int j = 0; j < variableCount; j++)
                inputRow.addVariable(new Variable());
            mInput.add(inputRow);
        }
    }

    private void removeRows() {
        while (equationCount > mInput.size()) {
            mInput.remove(mInput.size() - 1);
        }
    }

    private void addVariables() {
        for (InputRow inputRow : mInput) {
            while (inputRow.size() < variableCount)
                inputRow.addVariable(new Variable());
        }
    }

    private void removeVariables() {
        while (mInput.get(0).getVariables().size() > variableCount) {
            for (InputRow inputRow : mInput)
                inputRow.removeLastVariable();
        }
    }
}
