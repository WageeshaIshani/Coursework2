package com.example.user.cwk2;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeleteEmpActivity extends AppCompatActivity {


    public static final String EMP_NAME = "com.example.user.cwk2.fName";
    public static final String EMP_ID = "com.example.user.cwk2.id";

    DatabaseReference deleteEmpDb;

    List<Employee> empDelete;

    ListView listViewEmpDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_emp);

        deleteEmpDb = FirebaseDatabase.getInstance().getReference("employees");

        listViewEmpDelete = (ListView)findViewById(R.id.listViewEmpDelete);

        empDelete = new ArrayList<>();

        listViewEmpDelete.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Employee emp = empDelete.get(i);
                showUpdateEmpDialog(emp.getId(), emp.getfName());
                return true;
            }
        });
    }

    private void showUpdateEmpDialog(final String Id, String fName){

        AlertDialog.Builder dBlderE = new AlertDialog.Builder(this);
        LayoutInflater inflaterE = getLayoutInflater();
        final View ViewEmpD = inflaterE.inflate(R.layout.delete_employee_form_dialog, null);
        dBlderE.setView(ViewEmpD);

        final Button DelEmpbtn = (Button)ViewEmpD.findViewById(R.id.DeleteEmpbtn);

        dBlderE.setTitle(fName);
        final AlertDialog deleteDiaE = dBlderE.create();
        deleteDiaE.show();

        DelEmpbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                deleteEmp(Id);
                deleteDiaE.dismiss();
            }

        });


    }

    private boolean deleteEmp(String id) {

        DatabaseReference deleteEmpDbItem = FirebaseDatabase.getInstance().getReference("employees").child(id);

        deleteEmpDbItem.removeValue();

        Toast.makeText(getApplicationContext(), "Employee details Deleted", Toast.LENGTH_LONG).show();

        return true;
    }

    @Override
    protected void onStart() {

        super.onStart();

        deleteEmpDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                empDelete.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Employee empnew = postSnapshot.getValue(Employee.class);
                    empDelete.add(empnew);

                }

                EmployeeList empAdapter = new EmployeeList(DeleteEmpActivity.this, empDelete);
                listViewEmpDelete.setAdapter(empAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

    }

    }



