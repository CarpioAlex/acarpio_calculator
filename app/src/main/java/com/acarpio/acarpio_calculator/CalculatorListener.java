package com.acarpio.acarpio_calculator;

import android.view.View;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

import com.acarpio.acarpio_calculator.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class CalculatorListener implements View.OnClickListener, View.OnLayoutChangeListener {

    private ActivityMainBinding binding;
    private TextView numberContainer;
    private TextView currentHistory;
    private ScrollView historyContainer;
    private TextView historyText;
    private StringBuilder input = new StringBuilder();
    private StringBuilder history = new StringBuilder();

    private ArrayList<StringBuilder> historyList = new ArrayList<>();

    private double operand1 = 0;
    private double operand2 = 0;
    private double memory = 0;
    private String operator = "";
    private boolean isNewInput = true;
    private boolean historyOpened = false;

    public CalculatorListener(ActivityMainBinding binding) {
        this.binding = binding;
        this.input = MainActivity.getCurrentInput();
        this.numberContainer = binding.numberContainer;
        this.currentHistory = binding.currentHistory;
        this.historyContainer = binding.historyContainer;
        this.historyText = binding.historyText;
        this.memory = 0;
        binding.getRoot().addOnLayoutChangeListener(this);
        this.binding = binding;
        this.input = MainActivity.getCurrentInput();
        this.numberContainer = binding.numberContainer;
        this.currentHistory = binding.currentHistory;
        this.historyContainer = binding.historyContainer;
        this.historyText = binding.historyText;
        this.memory = 0;
    }

    @Override
    public void onClick(View v) {
        input = MainActivity.getCurrentInput();

        if (isNewInput) {
            input.setLength(0);
            isNewInput = false;
        }

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
        } else if (v == binding.suma) {
            setOperator("+");
        } else if (v == binding.resta) {
            setOperator("-");
        } else if (v == binding.division) {
            setOperator("/");
        } else if (v == binding.multiplication) {
            setOperator("*");
        } else if (v == binding.equals) {
            setResult();
        } else if (v == binding.buttonDelete) {
            if (input.length() > 0) {
                input.setLength(input.length() - 1);
                updateInput();
            }
        } else if (v == binding.buttonClear) {
            input.setLength(0);
            updateInput();
        } else if (v == binding.buttonMS) {
            saveMemory();
            isNewInput = true;
        } else if (v == binding.buttonMR) {
            input.setLength(0);
            input.append(formatValue(memory));
            updateInput();
            operand1 = memory;
            operator = "";
            isNewInput = false;
        } else if (v == binding.buttonMPlus) {
            addMemory();
            isNewInput = true;
        } else if (v == binding.buttonMLess) {
            restarMemory();
            isNewInput = true;
        } else if (v == binding.buttonMC) {
            clearMemory();
            binding.buttonMR.setEnabled(false);
            binding.buttonMLess.setEnabled(false);
            binding.buttonMPlus.setEnabled(false);
            binding.buttonMC.setEnabled(false);
            isNewInput = true;
        } else if (v == binding.currentHistory) {
            if (!historyOpened) {
                historyContainer.setVisibility(View.VISIBLE);
                populateHistory();
            } else {
                historyContainer.setVisibility(View.GONE);
                historyOpened = false;
            }
        }

        updateInput();
    }

    public void updateInput() {
        if (input.length() > 0) {
            binding.numberContainer.setText(formatValue(Double.parseDouble(input.toString())));
        } else {
            binding.numberContainer.setText("");
        }
        binding.currentHistory.setText(history.toString());
    }

    public void resetHistory() {
        history.setLength(0);
        updateInput();
    }

    public void setOperator(String op) {
        if (input.length() > 0 || !operator.isEmpty()) {
            if (!operator.isEmpty()) {
                setResult();
            }

            if (operator.isEmpty()) {
                resetHistory();
            }

            operand1 = input.length() > 0 ? Double.parseDouble(input.toString()) : 0;
            input.setLength(0);
            input.append(formatValue(operand1));
            isNewInput = false;

            operator = op;
            history.append(formatValue(operand1)).append(" ").append(operator).append(" ");
            updateInput();
            input.setLength(0);
            isNewInput = true;
        }
    }

    public void setResult() {
        if (input.length() > 0 || !operator.isEmpty()) {
            operand2 = input.length() > 0 ? Double.parseDouble(input.toString()) : 0;
            double result;

            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        binding.numberContainer.setText(">:(");
                        return;
                    }
                    break;
                default:
                    return;
            }

            input.setLength(0);
            input.append(formatValue(result));

            String resultString = formatValue(operand1) + " " + operator + " " + formatValue(operand2) + " = " + formatValue(result);
            history.append(formatValue(operand2)).append(" = ").append(formatValue(result));
            historyList.add(new StringBuilder(resultString));
            operand1 = result;
            operator = "";
            isNewInput = false;
            input.setLength(0);
            input.append(formatValue(result));

            updateInput();
            operator = "";
            isNewInput = false;

            if (historyOpened) {
                populateHistory();
            }
        }
    }

    // Memory

    public void saveMemory() {
        if (input != null && input.length() > 0) {
            if (input != null && input.length() > 0) {
                memory = Double.parseDouble(input.toString());
            }
            updateMemoryButtons();
        }
    }

    public void addMemory() {
        if (input.length() > 0) {
            memory += Double.parseDouble(input.toString());
            updateMemoryButtons();
        }
    }

    public void restarMemory() {
        if (input.length() > 0) {
            memory -= Double.parseDouble(input.toString());
            updateMemoryButtons();
        }
    }

    public void clearMemory() {
        memory = 0;
        updateMemoryButtons();
        memory = 0;
        updateMemoryButtons();
    }

    public ArrayList<StringBuilder> getHistoryList() {
        return historyList;
    }

    private void updateMemoryButtons() {
        boolean memoryNotEmpty = memory != 0;
        binding.buttonMR.setEnabled(memoryNotEmpty);
        binding.buttonMLess.setEnabled(memoryNotEmpty);
        binding.buttonMPlus.setEnabled(memoryNotEmpty);
        binding.buttonMC.setEnabled(memoryNotEmpty);
    }

    // History

    public void populateHistory() {
        historyOpened = true;
        historyText.setText("");
        StringBuilder completeHistory = new StringBuilder();
        for (StringBuilder hist : historyList) {
            completeHistory.append(hist.toString()).append("\n");
        }
        historyText.setText(completeHistory.toString());

        historyContainer.post(() -> historyContainer.fullScroll(View.FOCUS_DOWN));
    }

    // Extras

    public boolean entero(Double n) {
        Log.d("entero", n.toString());
        return n % 1 == 0;
    }

    private String formatValue(double value) {
        return (value % 1 == 0) ? String.format("%.0f", value) : String.valueOf(value);
    }
    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (v == binding.getRoot()) {
            saveMemory();
        }
    }
}
