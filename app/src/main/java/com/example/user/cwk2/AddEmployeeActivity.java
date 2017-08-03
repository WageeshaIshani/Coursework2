package com.example.user.cwk2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;

import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class AddEmployeeActivity extends AppCompatActivity  {



    EditText fNameText;
    EditText lNameText;
    EditText mNameText;
    EditText dobText;
    EditText adrsText;
    EditText telNoText;
    EditText emailText;
    Button savebt;
    Button backButton;


    DatabaseReference databaseEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        databaseEmployee = FirebaseDatabase.getInstance().getReference("employees");


        fNameText = (EditText) findViewById(R.id.fNameText);
        lNameText = (EditText) findViewById(R.id.lNameText);
        mNameText = (EditText) findViewById(R.id.mNameText);
        dobText = (EditText ) findViewById(R.id.dobText);
        adrsText = (EditText) findViewById(R.id.adrsText);
        telNoText = (EditText) findViewById(R.id.telNoText);
        emailText = (EditText) findViewById(R.id.emailText);


        savebt = (Button) findViewById(R.id.savebt);
        backButton = (Button) findViewById(R.id.backButton);

        savebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addEmployee();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                back();
            }
        });


    }

    private void back() {

            startActivity(new Intent(this, HomePageActivity.class));

    }


    private void addEmployee() {


        String fname = fNameText.getText().toString().trim();
        String lname = lNameText.getText().toString().trim();
        String mname = mNameText.getText().toString().trim();
        String dob = dobText.getText().toString().trim();
        String adrs = adrsText.getText().toString().trim();
        String tel = telNoText.getText().toString().trim();
        String email = emailText.getText().toString().trim();

        if (!TextUtils.isEmpty(fname)) {


            String id = databaseEmployee.push().getKey();

            Employee emp = new Employee(id, fname, lname, mname, dob, adrs, tel, email);

            databaseEmployee.child(id).setValue(emp);

            fNameText.setText("");
            lNameText.setText("");
            mNameText.setText("");
            dobText.setText("");
            adrsText.setText("");
            telNoText.setText("");
            emailText.setText("");


            Toast.makeText(this, "Employee added", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }


}
