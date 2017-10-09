package com.mashjulal.android.simplexmethodresolver;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;


class InputRowItemRecyclerViewAdapter
        extends RecyclerView.Adapter<InputRowItemRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<InputRow> mEquations;

    InputRowItemRecyclerViewAdapter(Context context, List<InputRow> equationList) {
        mContext = context;
        mEquations = equationList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_input_row, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final InputRow inputRow = mEquations.get(position);
        holder.rvVariables.setLayoutManager(
                new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        VariableItemRecyclerViewAdapter recyclerViewAdapter =
                new VariableItemRecyclerViewAdapter(mContext, inputRow.getVariables());
        holder.rvVariables.setAdapter(recyclerViewAdapter);

        if (inputRow.getValue().getValue() == 0)
            holder.etValue.setText("");
        else
            holder.etValue.setText(inputRow.getValue().toString());

        holder.spnrSigns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Resources resources = mContext.getResources();
                inputRow.setSign(resources.getStringArray(R.array.spinner_comparison_signs)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mEquations.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView rvVariables;
        Spinner spnrSigns;
        EditText etValue;

        ViewHolder(View itemView) {
            super(itemView);

            rvVariables = (RecyclerView) itemView.findViewById(R.id.rv_iInputRow_variables);
            spnrSigns = (Spinner) itemView.findViewById(R.id.spnr_iInputRow_signs);
            etValue = (EditText) itemView.findViewById(R.id.et_iInputRow_constant);
        }
    }
}
