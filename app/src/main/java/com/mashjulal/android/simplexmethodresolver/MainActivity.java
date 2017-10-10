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

import com.mashjulal.android.simplexmethodresolver.simplex_method.SimplexMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int equationCount = 2;
    private int variableCount = 2;
    private InputTable mSystemInput = new InputTable(equationCount, variableCount);
    private InputRow mTargetInput = new InputRow();
    private InputRowItemRecyclerViewAdapter mSystemRecyclerViewAdapter;
    private static String[] sEquationSpinnerEntries;
    private static String[] sVariableSpinnerEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup recycler view and it's adapter
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_aMain_inputRows);
        rv.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mSystemRecyclerViewAdapter = new InputRowItemRecyclerViewAdapter(this, mSystemInput);
        rv.setAdapter(mSystemRecyclerViewAdapter);

        TargetFunctionView tfv = (TargetFunctionView) findViewById(R.id.tfv_aMain_target);
        tfv.setInputRow(mTargetInput);

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
        switch (item.getItemId()) {
            case (R.id.menu_done):
                simplexMethod();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void initSpinners() {
        // Init equation number spinner and set listener for selected item
        Spinner spnrEquations = (Spinner) findViewById(R.id.spnr_aMain_numEquations);
        spnrEquations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                equationCount = Integer.valueOf(sEquationSpinnerEntries[position]);
                if (equationCount> mSystemInput.size()) {
                    addRows();
                } else {
                    removeRows();
                }
                mSystemRecyclerViewAdapter.notifyDataSetChanged();
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
                if (variableCount > mSystemInput.get(0).getVariables().size()) {
                    addVariables();
                } else {
                    removeVariables();
                }
                // refresh recycler view
                mSystemRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void addRows() {
        mSystemInput.setSize(equationCount);
    }

    private void removeRows() {
        mSystemInput.setSize(equationCount);
    }

    private void addVariables() {
        mSystemInput.setVariableCount(variableCount);
    }

    private void removeVariables() {
        mSystemInput.setVariableCount(variableCount);
    }

    private void simplexMethod() {
        List<List<Integer>> equationsCoefficients = new ArrayList<>();
        List<String> comparisonSings = new ArrayList<>();
        List<Integer> equationsConstants = new ArrayList<>();
        List<Integer> targetCoefficients = new ArrayList<>();
        Integer targetConstant = 0;

        for (InputRow inputRow : mSystemInput) {
            List<Integer> cofs = new ArrayList<>();
            for (Variable v : inputRow.getVariables())
                cofs.add(v.getValue());
            equationsCoefficients.add(cofs);
            comparisonSings.add(inputRow.getSign());
            equationsConstants.add(inputRow.getValue().getValue());
        }

        for (Variable v : mTargetInput) {
            targetCoefficients.add(v.getValue());
        }


        SimplexMethod si = new SimplexMethod(equationsCoefficients, comparisonSings,
                equationsConstants, targetCoefficients, targetConstant);
//        si.start();
    }
}
