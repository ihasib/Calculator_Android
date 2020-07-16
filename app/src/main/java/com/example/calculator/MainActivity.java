package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

interface  OperationResponseReceivable {
    void updateDisplay(String text);
//    func updateClearButton(ClearanceState ClearanceState)
//    func updateOperatorButton(with operationType: Operation)
}

public class MainActivity extends AppCompatActivity implements OperationResponseReceivable {

    StringReceivable operationManager;
    TextView displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Button clear;
        setContentView(R.layout.calculatoractivity_main);


        operationManager = new OperationManager(MainActivity.this);
        displayText = findViewById(R.id.calulatorScreen);
        displayText.addTextChangedListener(new MyWatcher());
        Button n0 = findViewById(R.id.n0);
        Button n1 = findViewById(R.id.n1);
        Button n2 = findViewById(R.id.n2);
        Button n3 = findViewById(R.id.n3);
        Button n4 = findViewById(R.id.n4);
        Button n5 = findViewById(R.id.n5);
        Button n6 = findViewById(R.id.n6);
        Button n7 = findViewById(R.id.n7);
        Button n8 = findViewById(R.id.n8);
        Button n9 = findViewById(R.id.n9);

        Button equal = findViewById(R.id.equal);
        Button decimal = findViewById(R.id.decimal);
        Button addition = findViewById(R.id.addition);
        Button subtraction = findViewById(R.id.subtraction);
        Button multiplication = findViewById(R.id.multiplication);
        Button division = findViewById(R.id.division);
        Button modulus = findViewById(R.id.modulous);
        Button negation = findViewById(R.id.negation);
        clear = findViewById(R.id.clear);

        Button.OnClickListener calculatorButtonListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("hasin1");
                Button b = (Button) view;
                operationManager.receive((String) b.getText());
//                System.out.println("has1:-"+clear.getText());
            }
        };
        n0.setOnClickListener(calculatorButtonListener);
        n1.setOnClickListener(calculatorButtonListener);
        n2.setOnClickListener(calculatorButtonListener);
        n3.setOnClickListener(calculatorButtonListener);
        n4.setOnClickListener(calculatorButtonListener);
        n5.setOnClickListener(calculatorButtonListener);
        n6.setOnClickListener(calculatorButtonListener);
        n7.setOnClickListener(calculatorButtonListener);
        n8.setOnClickListener(calculatorButtonListener);
        n9.setOnClickListener(calculatorButtonListener);
        decimal.setOnClickListener(calculatorButtonListener);
        equal.setOnClickListener(calculatorButtonListener);
        addition.setOnClickListener(calculatorButtonListener);
        subtraction.setOnClickListener(calculatorButtonListener);
        multiplication.setOnClickListener(calculatorButtonListener);
        division.setOnClickListener(calculatorButtonListener);
        modulus.setOnClickListener(calculatorButtonListener);
        negation.setOnClickListener(calculatorButtonListener);
        clear.setOnClickListener(calculatorButtonListener);
    }
    private void func() {
        System.out.println("Hasibur Rahman");
    }

    //
    @Override
    public void updateDisplay(String text) {
        displayText.setText(text);
    }
}

class MyWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        System.out.println("has1:-text changed");
    }

    @Override
    public void afterTextChanged(Editable editable) {
        System.out.println("has1:-text after changed");
    }
}

interface  StringReceivable {
    void receive(String data);
}

enum Operation {
    ADDITION {
        public String toString() {
            return "+";
        }
    },
    SUBTRACTION {
        public String toString() {
            return "-";
        }
    },
    MULTIPLICATION {
        public String toString() {
            return "*";
        }
    },
    DIVISION {
        public String toString() {
            return "/";
        }
    },
    MODULUS {
        public String toString() {
            return "%";
        }
    },
    EQUAL {
        public String toString() {
            return "=";
        }
    },
    NONE
    ;
}

class CustomString {
    private String value;
    private Observer operationManager;
    CustomString(String value, Observer operationManager) {
        this.value = value;
        this.operationManager = operationManager;
    }
    public void set(String value) {
        this.value = value;
        operationManager.updateDisplay();
    }
    public String get() {
        return value;
    }
}

interface Observer {
    void updateDisplay();
}

class OperationManager implements StringReceivable, Observer {
    private String firstOperand = "";
    private CustomString secondOperand;
    private Operation firstOperator = Operation.NONE;
    private Operation secondOperator;
    private OperationResponseReceivable mainActivity;
    OperationManager(OperationResponseReceivable mainActivity) {
        this.mainActivity = mainActivity;
        secondOperand = new CustomString("", this);
    }

    @Override
    public void receive(String data) {
        String buttonTitle = data;
        recognizeButton(buttonTitle);
    }

    private void recognizeButton(String buttonTitle) {
//        System.out.println("has1:="+buttonTitle);
//        mainActivity.updateDisplay(buttonTitle);
        switch (buttonTitle) {
            case "+":
                secondOperator = Operation.ADDITION;
                executeOperation();
                break;
            case "-":
                secondOperator = Operation.SUBTRACTION;
                executeOperation();
                break;
            case "*":
                secondOperator = Operation.MULTIPLICATION;
                executeOperation();
                break;
            case "/":
                secondOperator = Operation.DIVISION;
                executeOperation();
                break;
            case "%":
                secondOperator = Operation.MODULUS;
                executeOperation();
                break;
            case "=":
                secondOperator = Operation.EQUAL;
                executeOperation();
                break;
            case "+/-":
                negateSecondOperand();
                break;
            case ".":
                this.appendDecimalPoint();
                break;
            default:
                generateSecondOperand(buttonTitle);
        }
    }

    private void negateSecondOperand() {
        String secondOperandValue = secondOperand.get();
        if(secondOperandValue.contains(".")) {
            return;
        }
        if(secondOperandValue.startsWith("-")) {
            secondOperandValue = secondOperandValue.substring(1);
        } else {
            secondOperandValue = "-" + secondOperandValue;
        }
        secondOperand.set(secondOperandValue);
    }

    private void appendDecimalPoint() {
        String secondOperandValue = secondOperand.get();
        if(secondOperandValue.contains(".")) {
            return;
        }
        secondOperandValue = secondOperandValue + ".";
        secondOperand.set(secondOperandValue);
    }
    private void executeOperation() {
        if (firstOperator == Operation.NONE) {
            firstOperand = secondOperand.get();
            System.out.println("calculator reached"+Calculator.getCalculatedValue(firstOperand,secondOperand.get(),Operation.ADDITION));
        } else if (firstOperator != Operation.EQUAL) {
            double result = new Double(firstOperand);
            if (secondOperand.get() != "") {
                result = Calculator.getCalculatedValue(firstOperand,secondOperand.get(),firstOperator);
            }
            firstOperand = String.valueOf(result);
        }
        secondOperand.set("");
        firstOperator = secondOperator;
        secondOperator = Operation.NONE;
    }

    private void generateSecondOperand(String newChar) {
        System.out.println("hasin1+"+secondOperand.get() + newChar);
        secondOperand.set(secondOperand.get() + newChar);
    }

    @Override
    public void updateDisplay() {
        if (secondOperand.get() == "") {
            mainActivity.updateDisplay(firstOperand);
        } else {
            mainActivity.updateDisplay(secondOperand.get());
        }
    }
}

class Calculator {
    public static double getCalculatedValue(String firstOperand, String secondOperand, Operation operation) {
        Double dFirstOperand = new Double(firstOperand);
        Double dSecondOperand = new Double(secondOperand);
        switch (operation) {
            case ADDITION:
                return dFirstOperand + dSecondOperand;
            case SUBTRACTION:
                return dFirstOperand - dSecondOperand;
            case MULTIPLICATION:
                return dFirstOperand * dSecondOperand;
            case DIVISION:
                return dFirstOperand / dSecondOperand;
            case MODULUS:
                return dFirstOperand % dSecondOperand;
            default:
                return  0.0;
        }
    }
}