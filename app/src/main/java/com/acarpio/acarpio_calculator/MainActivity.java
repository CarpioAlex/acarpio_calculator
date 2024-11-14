package com.acarpio.acarpio_calculator;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.acarpio.acarpio_calculator.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    public static StringBuilder input;



    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CalculatorListener listener = new CalculatorListener(binding);
        assignListeners(binding, listener);

        input = new StringBuilder();

        binding.switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                uglyMode(binding, this);
            } else {
                niceMode(binding, this);
            }
        });

        if (savedInstanceState != null) {
            String num = savedInstanceState.getString("num");
            binding.numberContainer.setText(num);

        }


    }

    public void assignListeners(ActivityMainBinding binding, CalculatorListener listener) {
        for (int i = 0; i < binding.gridCalc.getChildCount(); i++) {
            View child = binding.gridCalc.getChildAt(i);
            if (child instanceof Button) {
                child.setOnClickListener(listener);

            }
        }
        for (int i = 0; i < binding.secondGrid.getChildCount(); i++) {
            View child = binding.secondGrid.getChildAt(i);
            if (child instanceof Button) {
                child.setOnClickListener(listener);

            }
        }

        binding.currentHistory.setOnClickListener(listener);


    }

    public static void uglyMode (ActivityMainBinding binding, Context c) {
        for (int i = 0; i < binding.gridCalc.getChildCount(); i++) {
            View child = binding.gridCalc.getChildAt(i);
            if (child instanceof Button) {
                child.setBackground(AppCompatResources.getDrawable(c, R.drawable.selector_boton));
            }
        }
        for (int i = 0; i < binding.secondGrid.getChildCount(); i++) {
            View child = binding.secondGrid.getChildAt(i);
            if (child instanceof Button) {
                child.setBackground(AppCompatResources.getDrawable(c, R.drawable.selector_memoria));
            }
        }

        binding.equals.setBackground(AppCompatResources.getDrawable(c, R.drawable.button_shape));
    }


    public static void niceMode(ActivityMainBinding binding, Context c) {
        for (int i = 0; i < binding.gridCalc.getChildCount(); i++) {
            View child = binding.gridCalc.getChildAt(i);
            if (child instanceof Button) {
                child.setBackground(AppCompatResources.getDrawable(c, android.R.drawable.btn_default));
            }
        }
        for (int i = 0; i < binding.secondGrid.getChildCount(); i++) {
            View child = binding.secondGrid.getChildAt(i);
            if (child instanceof Button) {
                child.setBackground(AppCompatResources.getDrawable(c, android.R.drawable.btn_default));
            }
        }

        binding.equals.setBackground(AppCompatResources.getDrawable(c, android.R.drawable.btn_default));
    }

    public static StringBuilder getCurrentInput() {
        return MainActivity.input;

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("num", binding.numberContainer.getText().toString());
    }
}




