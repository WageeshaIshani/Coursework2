package com.example.user.cwk2;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmpListActivity extends AppCompatActivity {


    public static final String EMP_NAME = "com.example.user.cwk2.fName";
    public static final String EMP_ID = "com.example.user.cwk2.id";

    DatabaseReference readEmployees;

    ListView listViewEmployees;
    List<Employee>  employee ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_list);

        readEmployees = FirebaseDatabase.getInstance().getReference("employees");

        listViewEmployees = (ListView)findViewById(R.id.listViewEmployees);

        employee = new ArrayList<>();

        listViewEmployees.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Employee emp = employee.get(i);
                showUpdateEmpDialog(emp.getId(), emp.getfName());
                return true;
            }
        });

    }

    private boolean updateEmp(String empId, String empFName, String empmName, String empLName, String dob, String tel, String adrs, String email){

        DatabaseReference dbRefEmp = FirebaseDatabase.getInstance().getReference("employees").child(empId);

        Employee employee = new Employee(empId, empFName, empmName, empLName, dob, tel, adrs, email);

        dbRefEmp.setValue(employee);
        Toast.makeText(getApplicationContext(), "Employee details Updated", Toast.LENGTH_LONG).show();

        return true;


    }

    private void showUpdateEmpDialog(final String empId, final String empFName){

        AlertDialog.Builder dBuilderEmp = new AlertDialog.Builder(this);
        LayoutInflater inflaterEmp = getLayoutInflater();
        final View ViewEmp = inflaterEmp.inflate(R.layout.update_emp_dialog, null);
        dBuilderEmp.setView(ViewEmp);


        final EditText fName = (EditText) ViewEmp.findViewById(R.id.fName);
        final EditText lName = (EditText) ViewEmp.findViewById(R.id.lName);
        final EditText mName = (EditText) ViewEmp.findViewById(R.id.mName);
        final EditText dob = (EditText) ViewEmp.findViewById(R.id.dob);
        final EditText tel = (EditText) ViewEmp.findViewById(R.id.tel);
        final EditText adrs = (EditText) ViewEmp.findViewById(R.id.adrs);
        final EditText email = (EditText) ViewEmp.findViewById(R.id.email);

        final Button updatebutton = (Button)ViewEmp.findViewById(R.id.updatebutton);

        dBuilderEmp.setTitle(empFName);
        final AlertDialog upDEMp = dBuilderEmp.create();
        upDEMp.show();

        updatebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String fNametxt = fName.getText().toString().trim();
                String mNametxt = mName.getText().toString().trim();
                String lNametxt = lName.getText().toString().trim();
                String dobtxt = dob.getText().toString().trim();
                String teltxt = tel.getText().toString().trim();
                String adrstxt = adrs.getText().toString().trim();
                String emailtxt = email.getText().toString().trim();

                if(!TextUtils.isEmpty(fNametxt) && !TextUtils.isEmpty(lNametxt) && !TextUtils.isEmpty(teltxt) && !TextUtils.isEmpty(adrstxt)){

                    updateEmp(empId, fNametxt, mNametxt, lNametxt, dobtxt, adrstxt ,teltxt, emailtxt);
                    upDEMp.dismiss();
                }
            }
        });


    }

    @Override
    protected void onStart() {

        super.onStart();

        readEmployees.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                employee.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Employee emp1 = postSnapshot.getValue(Employee.class);
                    employee.add(emp1);

                }

                EmployeeList memberAdapter = new EmployeeList(EmpListActivity.this, employee);
                listViewEmployees.setAdapter(memberAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }


}
