package com.example.aplikacja;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DodPytanie extends AppCompatActivity {

    EditText odp1, odp2, odp3, pytanie;
    Button zapisz;
    int nr=1;
    FirebaseDatabase database;
    DatabaseReference ref;

    Pytania pytania;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dod_pytanie);
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

        odp1 = (EditText) findViewById(R.id.odpText1);
        odp2 = (EditText) findViewById(R.id.odpText2);
        odp3 = (EditText) findViewById(R.id.odpText3);
        pytanie = (EditText) findViewById(R.id.pytanieText);
        zapisz = (Button) findViewById(R.id.zapiszPytanie);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Pytania");
        pytania = new Pytania();
    }

    private void getValues()
    {
        pytania.setOdp1(odp1.getText().toString());
        pytania.setOdp2(odp2.getText().toString());
        pytania.setOdp3(odp3.getText().toString());
        pytania.setPytanie(pytanie.getText().toString());
    }

    public void zapiszPytanie(View view)
    {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                getValues();
                ref.child(String.valueOf(nr)).setValue(pytania);
                Toast.makeText(DodPytanie.this, "Udało sie!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        nr++;
    }


}
