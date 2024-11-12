package com.acarpio.acarpio_calculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.acarpio.acarpio_calculator.databinding.ActivityMainBinding;

public class CalculatorListener implements View.OnClickListener  {

    private ActivityMainBinding binding;
    private TextView numberContainer;
    private StringBuilder input = new StringBuilder();
    ;

    public CalculatorListener(ActivityMainBinding binding) {
        this.binding = binding;
        this.numberContainer = binding.numberContainer;
        this.input = MainActivity.getCurrentInput();
    }
    @Override
    public void onClick(View v) {
        input = MainActivity.getCurrentInput();

        if (v == binding.b00) {
            input.append("00");
        } else if (v == binding.b0) {
            input.append("0");
        } else if (v == binding.b1) {
            input.append("1");
        } else if (v == binding.b2) {
            input.append("2");
            } else if (v == binding.b3) {
            input.append("3");
        } else if (v == binding.b4) {
            input.append("4");
            } else if (v == binding.b5) {
            input.append("5");
        } else if (v == binding.b6) {
            input.append("6");
            } else if (v == binding.b7) {
            input.append("7");
        } else if (v == binding.b8) {
            input.append("8");
            } else if (v == binding.b9) {
            input.append("9");
        }


        updateInput();
    }




    public void updateInput() {
        binding.numberContainer.setText(input.toString());
    }


}
