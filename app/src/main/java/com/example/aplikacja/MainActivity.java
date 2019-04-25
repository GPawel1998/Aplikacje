package com.example.aplikacja;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Currency;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText Email, Haslo;
    private Button zaloguj, wyloguj,wyjscie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Email = (EditText) findViewById(R.id.editText);
        Haslo = (EditText) findViewById(R.id.editText2);
        zaloguj = (Button) findViewById(R.id.logowanie);
        wyloguj = (Button) findViewById(R.id.wylogowanie);
        wyjscie = (Button) findViewById(R.id.exit);
        mAuth = FirebaseAuth.getInstance();



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    toastMessage("Zalogowano!");

                }
                else{
                    toastMessage("NIEEEEEEEEEEEEE!");
                    }
            }

        };
        zaloguj.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String pass = Haslo.getText().toString();
                if(!email.equals("") && !pass.equals("")){
                    mAuth.signInWithEmailAndPassword(email,pass);
                    Graj(view);

                }else {
                    toastMessage("Nie wypełnione pola!");
                }


            }
        });
        wyloguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                toastMessage("Wylogowany...");
            }
        });
        wyjscie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                System.exit(0);
            }
        });
        }


    public void Graj (View view)
    {
        Intent intent = new Intent(this, Gra.class);
        startActivity(intent);
    }



    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

        //add a toast to show when successfully signed in
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}

