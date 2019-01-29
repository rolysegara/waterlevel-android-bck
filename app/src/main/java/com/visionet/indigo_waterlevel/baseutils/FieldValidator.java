package com.visionet.indigo_waterlevel.baseutils;

import android.content.Context;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.visionet.indigo_waterlevel.R;

public class FieldValidator {

    public static boolean validateFields(View... views) {
        View firstInvalidView = null;

        for (View view : views) {
            if (!view.isShown()) {
                continue;
            }

            boolean viewIsVaild = true;

            if (view instanceof EditText) {
                viewIsVaild = validateEditText((EditText) view);
            }

            if (!viewIsVaild && firstInvalidView == null) {
                firstInvalidView = view;
            }
        }

        if (firstInvalidView != null) {
            firstInvalidView.requestFocus();
            return false;
        }

        return true;
    }

    private static boolean validateEditText(EditText editText) {
        boolean valid = true;

        String text = editText.getText().toString();

        boolean isEmail   = (editText.getInputType() & InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
        boolean isNumeric = (editText.getInputType() & InputType.TYPE_NUMBER_FLAG_DECIMAL) == InputType.TYPE_NUMBER_FLAG_DECIMAL;

        if (TextUtils.isEmpty(text)) {
            if (!isNumeric || !TextUtils.isDigitsOnly(editText.getHint())) {
                valid = false;
            }

        } else if (isEmail) {
            valid = android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches();
        }

        if (!valid) {
            Context context = editText.getContext();
            if (isEmail) {
                editText.setError(context.getString(R.string.error_invalid_email));
            } else {
                editText.setError(context.getString(R.string.error_blank));
            }
            return false;
        }

        editText.setError(null);
        return true;
    }
}
