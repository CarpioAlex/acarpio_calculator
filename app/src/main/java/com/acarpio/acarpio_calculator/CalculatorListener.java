package com.acarpio.acarpio_calculator;

import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.acarpio.acarpio_calculator.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class CalculatorListener implements View.OnClickListener {

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
        } else if (v == binding.buttonMR) {
            if (memory != 0) {
                input.append(memory);
                updateInput();
            }
        } else if (v == binding.buttonMPlus) {
            addMemory();
        } else if (v == binding.buttonMLess) {
            restarMemory();
        } else if (v == binding.buttonMC) {
            memory = 0;
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
        binding.numberContainer.setText(input.toString());
        binding.currentHistory.setText(history.toString());

    }

    public void resetHistory() {
        history.setLength(0);
        updateInput();
    }

    public void setOperator(String op) {
        if (input.length() > 0) {
            if (!operator.isEmpty()) {

                setResult();
            }

            if (operator.isEmpty()) {
                resetHistory();
            }

            operand1 = Double.parseDouble(input.toString());
            operator = op;
            history.append(operand1).append(" ").append(operator).append(" ");
            updateInput();
            input.setLength(0);
            isNewInput = true;
        }
    }

    public void setResult() {
        if (input.length() > 0 && !operator.isEmpty()) {
            operand2 = Double.parseDouble(input.toString());
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
            String resultString = operand1 + " " + operator + " " + operand2 + " = " + result;
            history.append(operand2).append(" = ").append(result);
            historyList.add(new StringBuilder(resultString));
            input.setLength(0);
            input.append(result);
            operand1 = result;


            updateInput();
            operator = "";
            isNewInput = true;

            if (historyOpened) {
                populateHistory();
            }

        }
    }

    // Memory

    public void saveMemory() {
        if (input.length() > 0) {
            memory = Double.parseDouble(input.toString());
        }
    }

    public void addMemory() {
        if (input.length() > 0) {
            memory += Double.parseDouble(input.toString());
        }

    }

    public void restarMemory() {
        if (input.length() > 0) {
            memory -= Double.parseDouble(input.toString());
        }
    }


    // History

    public void populateHistory() {



        historyOpened = true;
        historyText.setText("");


        for (StringBuilder hist : historyList) {
            historyText.append(hist.toString() + "\n");
        }


        historyContainer.post(() -> historyContainer.fullScroll(View.FOCUS_DOWN));
    }



}
