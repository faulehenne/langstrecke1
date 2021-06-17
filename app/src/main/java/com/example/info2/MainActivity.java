
package com.example.info2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextClassifierEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText reichw;
    private EditText strecke;
    private TextView stops;
    private TextView display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reichw = (EditText) findViewById(R.id.reichw);
        strecke = (EditText) findViewById(R.id.strecke);
        Button berechnen = (Button) findViewById(R.id.berechnen);
        display = (TextView) findViewById(R.id.ausgabe);
        stops = (TextView) findViewById(R.id.stopcounter);

        berechnen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                makeCalculations();

            }
        });

    }

    private void makeCalculations() {
        double n1 = Double.valueOf(reichw.getText().toString());
        double n2 = Double.valueOf(strecke.getText().toString());
        double reststr = n2 - n1;
        double reichwNeu = n1 * 0.8;
        double halten = Math.ceil(reststr / reichwNeu);
        double endreich = (n1 - n2);
        String language = Locale.getDefault().getLanguage();
        int hours = (int) (n2/100);
        int minutes = (int) (n2/100 * 60) % 60;
        int hourswithstop = (int) (n1 / 100 + reststr / 100 + halten * 0.5);
        int minuteswithstop = (int) ((n1 / 100 + reststr / 100 + halten * 0.5) * 60) % 60;


        DecimalFormat df = new DecimalFormat("#.##");

        if (language.equals("de")) {


            if (n2 <= n1) {
                display.setText("Fahrtdauer ist " + hours + " Stunden und " + minutes + " Minuten");
                stops.setText("Du musst nicht anhalten und hast bei Ankunft noch " + endreich + " KM Reichweite");
            } else {
                display.setText("Fahrtdauer ist " + hourswithstop + " Stunden und " + minuteswithstop + "Minuten" );
                stops.setText("Du musst " + df.format(halten) + " mal anhalten und hast bei Ankunft noch " + df.format(endreich + halten * reichwNeu) + " KM Reichweite");
            }
        }

        if (language.equals("en")) {


            if (n2 <= n1) {
                display.setText("Journey Time is  " + hours + " Hours and " + minutes + "minutes");
                stops.setText("You don't have to stop and you will still have " + endreich + " KM range left");
            } else {
                display.setText("Journey Time is " + hourswithstop + " Hours and " + minuteswithstop + " minutes");
                stops.setText("You have to stop " + df.format(halten) + " times and you will still have " + df.format(endreich + halten * reichwNeu) + " KM range left");


            }


        }

    }
}
