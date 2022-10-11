package com.example.mycalculator;

import java.util.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button1, button2, button3, button4, button5, button6, button7, button8,
            buttonM, button9, button0, buttonx, buttonC, buttonD, buttonS, buttonP, buttonE;
    TextView text_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button0 = (Button) findViewById(R.id.button0);
        buttonx = (Button) findViewById(R.id.buttonx);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);
        buttonS = (Button) findViewById(R.id.buttonS);
        buttonP = (Button) findViewById(R.id.buttonP);
        buttonE = (Button) findViewById(R.id.buttonE);
        buttonM = (Button) findViewById(R.id.buttonM);

        text_display = (TextView) findViewById(R.id.textview_input_display);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);
        buttonP.setOnClickListener(this);
        buttonM.setOnClickListener(this);
        buttonx.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        buttonS.setOnClickListener(this);
        buttonE.setOnClickListener(this);
        buttonC.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                addNumber ("1");
                break;
            case R.id.button2:
                addNumber ("2");
                break;
            case R.id.button3:
                addNumber ("3");
                break;
            case R.id.button4:
                addNumber ("4");
                break;
            case R.id.button5:
                addNumber ("5");
                break;
            case R.id.button6:
                addNumber ("6");
                break;
            case R.id.button7:
                addNumber ("7");
                break;
            case R.id.button8:
                addNumber ("8");
                break;
            case R.id.button9:
                addNumber ("9");
                break;
            case R.id.button0:
                addNumber ("0");
                break;
            case R.id.buttonP:
                addNumber ("+");
                break;
            case R.id.buttonM:
                addNumber ("-");
                break;
            case R.id.buttonx:
                addNumber ("x");
                break;
            case R.id.buttonS:
                addNumber ("/");
                break;
            case R.id.buttonE:
                text_display.setText(calculate(text_display.getText().toString()));
                break;
            case R.id.buttonD:
                addNumber (".");
                break;
            case R.id.buttonC:
                clear_dispaly();
                break;

        }
    }

    private static String prevSymbol = "";
    private static String prevNum = "";

    private static String calculate(String input){
        List<String> splitIn = new ArrayList<String>();
        String num = "";

        for (int i = 0; i < input.length(); i++){
            if (input.charAt(i) == '/' || input.charAt(i) == 'x' || input.charAt(i) == '-' || input.charAt(i) == '+' ){
                splitIn.add(num);
                num = "";
                splitIn.add(Character.toString(input.charAt(i)));
            } else {
                num += input.charAt(i);
            }
        }

        splitIn.add(num);

        if (splitIn.size() == 1 && !prevNum.equals("")){
            return calculate(splitIn.get(0) + prevSymbol + prevNum);
        }

        for (int i = 0; i < splitIn.size(); i++){
            System.out.println(splitIn.get(i));
        }

        if (splitIn.size() == 3){
            prevNum = splitIn.get(2);
            prevSymbol = splitIn.get(1);
        }
        else{
            prevNum = "";
            prevSymbol = "";
        }

        try {
            for (int i = 0; i < splitIn.size(); i++){
                // System.out.println(splitIn.get(i));

                if (splitIn.get(i).equals("/")){
                    double num1 = Double.parseDouble(splitIn.get(i-1));
                    double num2 = Double.parseDouble(splitIn.get(i+1));

                    splitIn.set(i-1, Double.toString(num1 / num2));
                    splitIn.remove(i);
                    splitIn.remove(i);
                    i = i-1;
                }
                if (splitIn.get(i).equals("x")){
                    double num1 = Double.parseDouble(splitIn.get(i-1));
                    double num2 = Double.parseDouble(splitIn.get(i+1));

                    splitIn.set(i-1, Double.toString(num1 * num2));
                    splitIn.remove(i);
                    splitIn.remove(i);
                    i = i-1;
                }
            }
            for (int i = 0; i < splitIn.size(); i++){
                if (splitIn.get(i).equals("+")){
                    double num1 = Double.parseDouble(splitIn.get(i-1));
                    double num2 = Double.parseDouble(splitIn.get(i+1));

                    splitIn.set(i-1, Double.toString(num1 + num2));
                    splitIn.remove(i);
                    splitIn.remove(i);
                    i = i-1;
                }
                if (splitIn.get(i).equals("-")){
                    double num1 = Double.parseDouble(splitIn.get(i-1));
                    double num2 = Double.parseDouble(splitIn.get(i+1));

                    splitIn.set(i-1, Double.toString(num1 - num2));
                    splitIn.remove(i);
                    splitIn.remove(i);
                    i = i-1;
                }
            }

            double ans = Double.parseDouble(splitIn.get(0));

            if(ans-(int)ans == 0.0){
                return Integer.toString((int)ans);
            }

            return splitIn.get(0);
        }
        catch(Exception e) {
            prevNum = "";
            prevSymbol = "";
            return "Error";
        }
    }

    private void addNumber (String number){
        text_display.setText(text_display.getText()+number);
    }
    private void clear_dispaly(){
        text_display.setText("");
    }
}