package com.mashjulal.android.simplexmethodresolver;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Setter
@Getter
@Accessors(prefix = "m")
class TargetFunctionView extends View {

    private InputRow mInputRow = new InputRow();

    public TargetFunctionView(Context context) {
        super(context);
    }

    public TargetFunctionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        handleAttributes(context, attrs);
    }

    private void handleAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.stylable.TargetFunctionView);
        final int count = a.getIndexCount();

        for (int i = 0; i < count; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.stylable.TargetFunctionView_variableCount:
                    mInputRow.setSize(a.getInteger(attr, 0));
                    break;
            }
            a.recycle();
        }
    }

    private void updateView() {

    }
}
