package com.example.user.cwk2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import com.firebase.client.Firebase;

public class AddEmployeeActivity extends AppCompatActivity {

   // private Button addData;
   // private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
       // Firebase.setAndroidContext(this);

//        mRef =  new Firebase("https://cwk2-3c871.firebaseio.com/");
//
//        addData = (Button) findViewById(R.id.addbt);
//
//        addData.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//
//               Firebase mRefchild = mRef.child("Name");
//                mRefchild.setValue("Test Adding");
//
//            }
//        });
    }
}
