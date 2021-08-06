package com.example.postcontrol.services;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Objects;

public class MaskCurrencyService implements TextWatcher {

    private String current;
    private final EditText inputValue;

    public MaskCurrencyService(EditText inputValue) {
        this.current = "";
        this.inputValue = inputValue;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!s.toString().equals(current)) {
            inputValue.removeTextChangedListener(this);

            String replaceable = String.format("[%s,.\\s]", Objects.requireNonNull(
                    NumberFormat.getCurrencyInstance().getCurrency()).getSymbol());
            String cleanString = s.toString().replaceAll(replaceable, "");

            double parsed;
            try {
                parsed = Double.parseDouble(cleanString);
            } catch (NumberFormatException e) {
                parsed = 0.00;
            }
            String formatted = NumberFormat.getCurrencyInstance().format((parsed / 100));

            current = formatted;
            inputValue.setText(formatted);
            inputValue.setSelection(formatted.length());
            inputValue.addTextChangedListener(this);
        }
    }
}
