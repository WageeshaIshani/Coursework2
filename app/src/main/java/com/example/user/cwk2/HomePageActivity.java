package com.example.user.cwk2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth firebaseAuth;


    private TextView textViewUserEmail;
    private Button buttonLogout;
    private Button addButton;
    private Button viewbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {

            finish();

            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();


        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        addButton = (Button) findViewById(R.id.addButton);
        viewbt = (Button) findViewById(R.id.viewbt);


        textViewUserEmail.setText("Welcome " + user.getEmail());


        buttonLogout.setOnClickListener(this);
        addButton.setOnClickListener(this);
        viewbt.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

        if (v == buttonLogout) {

            firebaseAuth.signOut();

            finish();

            startActivity(new Intent(this, LoginActivity.class));

        }else if(v == addButton ){

                finish();
                startActivity(new Intent(this, AddEmployeeActivity.class));
           }
        else if(v == viewbt ){

            finish();
            startActivity(new Intent(this, EmpListActivity.class));
        }


    }
}