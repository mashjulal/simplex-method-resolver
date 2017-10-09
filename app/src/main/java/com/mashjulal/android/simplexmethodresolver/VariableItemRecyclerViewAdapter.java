package com.mashjulal.android.simplexmethodresolver;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.RelativeSizeSpan;
import android.text.style.SubscriptSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;


class VariableItemRecyclerViewAdapter extends
        RecyclerView.Adapter<VariableItemRecyclerViewAdapter.VariableViewHolder> {

    private static final int VIEW_VARIABLE = 0;
    private static final int VIEW_LAST_VARIABLE = 1;

    private Context mContext;
    private List<Variable> mVariableList;

    VariableItemRecyclerViewAdapter(Context context, List<Variable> variables) {
        mContext = context;
        mVariableList = variables;
    }

    @Override
    public VariableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View v;
        switch (viewType) {
            case VIEW_VARIABLE:
                v = li.inflate(R.layout.item_variable, null);
                break;
            case VIEW_LAST_VARIABLE:
                v = li.inflate(R.layout.item_last_variable, null);
                break;
            default:
                v = li.inflate(R.layout.item_variable, null);
                break;
        }
        return new VariableViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mVariableList.size() - 1)
            return VIEW_LAST_VARIABLE;
        return VIEW_VARIABLE;
    }

    @Override
    public void onBindViewHolder(VariableViewHolder h, int position) {
        final Variable variable = mVariableList.get(position);

        if (variable.getValue() == 0)
            h.etValue.setText("");
        else
            h.etValue.setText(String.format(Locale.getDefault(), "%d", variable.getValue()));
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
        SpannableStringBuilder cs = new SpannableStringBuilder(
                mContext.getString(R.string.title_variable_template, position + 1));
        cs.setSpan(new SubscriptSpan(), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        cs.setSpan(new RelativeSizeSpan(0.75f), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        h.tvIndex.setText(cs);
    }

    @Override
    public int getItemCount() {
        return mVariableList.size();
    }

    static class VariableViewHolder extends RecyclerView.ViewHolder {

        EditText etValue;
        TextView tvIndex;

        VariableViewHolder(View itemView) {
            super(itemView);
            etValue = (EditText) itemView.findViewById(R.id.et_iVariable_value);
            tvIndex = (TextView) itemView.findViewById(R.id.tv_iVariable_index);
        }
    }
}
