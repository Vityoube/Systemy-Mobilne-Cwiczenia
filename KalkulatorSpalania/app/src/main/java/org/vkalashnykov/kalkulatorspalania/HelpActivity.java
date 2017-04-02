package org.vkalashnykov.kalkulatorspalania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        TextView helper=(TextView)findViewById(R.id.helpTextLabel);
        helper.setText("Sposób obliczania spalania paliwa\n\n"
                +"Wartość tankowania w złotówkach jest dzielona przez cenę litra co daje liczbę zatankowanych litrów\n"
                +"Liczba zatankowanych litrów jest dzielona przez liczbę kilometrów\n"
                +"Wynik tego dzielenia mnoży się przez 100 aby uzyskać wynik z dwoma miejscami po przecinku\n");
    }

    public void returnClick(View view){
       finish();
    }
}
