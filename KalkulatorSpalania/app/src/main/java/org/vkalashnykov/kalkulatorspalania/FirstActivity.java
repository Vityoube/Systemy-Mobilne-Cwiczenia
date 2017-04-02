package org.vkalashnykov.kalkulatorspalania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.GetChars;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {
    public EditText result;
    public EditText job;
    float eq;
    float num1;
    float num2;
    float num3;
    float num4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void calculateClick(View view ){
        EditText numA=(EditText)findViewById(R.id.liczba1Text);
        EditText numB=(EditText)findViewById(R.id.liczba2Text);
        EditText numC=(EditText)findViewById(R.id.liczba3Text);
        result=(EditText)findViewById(R.id.spalanieText);
        if (numA.length()==0){
            num1=0;
        } else {
            num1=Float.parseFloat(numA.getText().toString());
        }
        if (numB.length()==0){
            num2=0;
        } else {
            num2=Float.parseFloat(numB.getText().toString());
        }
        if (numC.length()==0){
            num3=0;
        } else {
            num3=Float.parseFloat(numC.getText().toString());
        }
        switch (view.getId()){
            case R.id.obliczButton:
                num4=num1/num2/num3;
                eq=num4*100;
                break;
        }
        result.setText(String.format("=%.2f",eq));
    }

    public void helpClick(View view){
        Intent intent=new Intent(getApplicationContext(),HelpActivity.class);
        startActivity(intent);
    }
}
