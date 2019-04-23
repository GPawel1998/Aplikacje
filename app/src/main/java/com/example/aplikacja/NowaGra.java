package com.example.aplikacja;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NowaGra extends AppCompatActivity {


    Button b1, b2, b3, b_tura;
    TextView pytanie, text1, text2, text3;

    boolean _b1 = false;
    boolean _b2 = false;
    boolean _b3 = false;

    boolean _b1c = false;
    boolean _b2c = false;
    boolean _b3c = false;

    boolean _cz = false;
    String PlayerA = "A";
    String PlayerB = "B";
    int pkt_a = 0,nr=1;
    int pkt_b = 0;
    int nr_p = 1;

    FirebaseDatabase database;
    DatabaseReference ref;
    int total = 0;
    Pytania pytania;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowa_gra);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b_tura = (Button) findViewById(R.id.tura);
        pytanie = (TextView) findViewById(R.id.text4);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Pytania");
        pytania = new Pytania();
        pokazGra();


        text1 = (TextView) findViewById(R.id.text1);
        text1.setText("Gracz "+PlayerA+": "+pkt_a+" pkt.");
        text2 = (TextView) findViewById(R.id.text2);
        text2.setText("Gracz "+PlayerB+": "+pkt_b+" pkt.");
        text3 = findViewById(R.id.text3);
        text3.setText("Nr pytania:"+nr_p);

        /**/

    }


    public void pokazGra() {
        total++;
        if(total>10) {
        }
        else
        {
        ref = database.getReference("Pytania/"+total).child("odp1");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String odp1 = dataSnapshot.getValue().toString();
                b1.setText(odp1);
                nr++;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref = database.getReference("Pytania/"+total).child("odp2");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String odp2 = dataSnapshot.getValue().toString();
                b2.setText(odp2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref = database.getReference("Pytania/"+total).child("odp3");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String odp3 = dataSnapshot.getValue().toString();
                b3.setText(odp3);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref = database.getReference("Pytania/"+total).child("pytanie");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String pytaniee = dataSnapshot.getValue().toString();
                pytanie.setText(pytaniee);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }}

    public void click1(View view) {
        switch (view.getId()) {
            case R.id.button1:
                if (_b1c) {
                    _b1 = false;
                    _b1c = false;
                    b1.setBackgroundColor(Color.WHITE);

                } else {
                    _b1 = true;
                    _b1c = true;
                    b1.setBackgroundColor(Color.BLUE);

                }

        }


    }

    public void click2(View view) {
        switch (view.getId()) {

            case R.id.button2:
                if (_b2c) {
                    _b2 = false;
                    _b2c = false;
                    b2.setBackgroundColor(Color.WHITE);
                } else {
                    _b2 = true;
                    _b2c = true;
                    b2.setBackgroundColor(Color.BLUE);
                }
        }


    }

    public void click3(View view) {
        switch (view.getId()) {

            case R.id.button3:
                if (_b3c) {
                    _b3 = false;
                    _b3c = false;
                    b3.setBackgroundColor(Color.WHITE);
                } else {
                    _b3 = true;
                    _b3c = true;
                    b3.setBackgroundColor(Color.BLUE);
                }

        }


    }

    public void click_tura(View view) {
        if (_cz == false) {
            zeruj_kolor();

            _cz = true;

            b_tura.setText("Sprawdz");
            text1.setText("Gracz " + PlayerA + ": " + pkt_a + " pkt.");

        } else if (_cz == true) {

            if (spr()) {
                if (nr_p % 2 == 0) {
                    pkt_a++;
                } else
                    pkt_b++;

                zeruj();
                nr_p++;
            } else {
                zeruj();
                nr_p++;
            }
            b_tura.setText(">");
            _cz = false;

            text1.setText("Gracz " + PlayerA + ": " + pkt_a + " pkt.");
            text2.setText("Gracz " + PlayerB + ": " + pkt_b + " pkt.");

            text3.setText("Nr pytania:" + nr_p);
            pokazGra();


        }
    }
    public void zeruj_kolor(){
        b1.setBackgroundColor(Color.WHITE);
        b2.setBackgroundColor(Color.WHITE);
        b3.setBackgroundColor(Color.WHITE);
        _b1c=false;
        _b2c=false;
        _b3c=false;

    }
    public void zeruj(){
        b1.setBackgroundColor(Color.WHITE);
        b2.setBackgroundColor(Color.WHITE);
        b3.setBackgroundColor(Color.WHITE);
        _b1=false;
        _b2=false;
        _b3=false;

        _b1c=false;
        _b2c=false;
        _b3c=false;
    }

    boolean spr(){

        if(_b1==_b1c && _b2==_b2c && _b3==_b3c)
            return true;
        else
            return false;


    }
}