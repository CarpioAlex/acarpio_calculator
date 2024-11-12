package com.acarpio.acarpio_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.acarpio.acarpio_calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static StringBuilder input;
    String operator;
    Double result;

    boolean numberInMemory;
    double memoryValue;





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
        operator = "";
        result = 0.0;
        numberInMemory = false;
        memoryValue = 0.0;

    }

    public void assignListeners(ActivityMainBinding binding, CalculatorListener listener) {
        for (int i = 0; i < binding.gridCalc.getChildCount(); i++) {
            View child = binding.gridCalc.getChildAt(i);
            if (child instanceof Button) {
                child.setOnClickListener(listener);
            }
        }
    }

    public static StringBuilder getCurrentInput() {
        return MainActivity.input;
    }




}