package com.mashjulal.android.simplexmethodresolver;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ElementItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_HOLDER_VARIABLE = 0;
    public static final int VIEW_HOLDER_PLUS = 1;

    private Context mContext;
    private List<Element> mElementList = new ArrayList<>();

    public ElementItemRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View v;
        switch (viewType) {
            case VIEW_HOLDER_VARIABLE:
                v = li.inflate(R.layout.item_variable, null);
                return new VariableViewHolder(v);
            case VIEW_HOLDER_PLUS:
                v = li.inflate(R.layout.item_plus, null);
                return new PlusViewHolder(v);
            default:
                v = li.inflate(R.layout.item_variable, null);
                return new VariableViewHolder(v);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0)
            return VIEW_HOLDER_VARIABLE;
        return VIEW_HOLDER_PLUS;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PlusViewHolder)
            return;

        VariableViewHolder h = (VariableViewHolder) holder;
        Variable variable = (Variable) mElementList.get(position);
        int index = (position / 2) + 1;

        if (variable.getValue() == 0)
            h.etValue.setText("");
        else
            h.etValue.setText(variable.getValue());
        h.etValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty())
                    variable.setValue(0);
                else
                    variable.setValue(Integer.valueOf(s.toString()));
            }
        });

        h.tvIndex.setText(mContext.getString(R.string.title_variable_template, index));
    }

    @Override
    public int getItemCount() {
        return mElementList.size();
    }

    static class VariableViewHolder extends RecyclerView.ViewHolder {

        EditText etValue;
        TextView tvIndex;

        public VariableViewHolder(View itemView) {
            super(itemView);
            etValue = (EditText) itemView.findViewById(R.id.et_iVariable_value);
            tvIndex = (TextView) itemView.findViewById(R.id.tv_iVarable_index);
        }
    }

    static class PlusViewHolder extends RecyclerView.ViewHolder {

        public PlusViewHolder(View itemView) {
            super(itemView);
        }
    }
}
