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

        displayText = findViewById(R.id.calulatorScreen);
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
        Button modulous = findViewById(R.id.modulous);
        Button negation = findViewById(R.id.negation);
        clear = findViewById(R.id.clear);

        Button.OnClickListener calculatorButtonListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayText.addTextChangedListener(new MyWatcher());

                Button b = (Button) view;
                operationManager = new OperationManager(MainActivity.this);
                operationManager.receive((String) b.getText());
                System.out.println("has1:-"+clear.getText());
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
        modulous.setOnClickListener(calculatorButtonListener);
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

class OperationManager implements StringReceivable {
    private OperationResponseReceivable mainActivity;
    OperationManager(OperationResponseReceivable mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void receive(String data) {
        String buttonTitle = data;
        recognizeButton(buttonTitle);
    }

    private void recognizeButton(String buttonTitle) {
        System.out.println("has1:="+buttonTitle);
        mainActivity.updateDisplay(buttonTitle);
    }
}