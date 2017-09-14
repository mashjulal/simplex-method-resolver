package com.mashjulal.android.simplexmethodresolver;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by master on 14.09.17.
 */

public class EquationItemRecyclerViewAdapter
        extends RecyclerView.Adapter<EquationItemRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Equation> mEquations;

    EquationItemRecyclerViewAdapter(Context context, List<Equation> equationList) {
        mContext = context;
        mEquations = equationList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_equation, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mEquations.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llVariables;
        Spinner spnrSigns;
        EditText etValue;

        public ViewHolder(View itemView) {
            super(itemView);

            llVariables = (LinearLayout) itemView.findViewById(R.id.ll_iEquation_variables);
            spnrSigns = (Spinner) itemView.findViewById(R.id.spnr_iEquation_signs);
            etValue = (EditText) itemView.findViewById(R.id.)
        }
    }
}