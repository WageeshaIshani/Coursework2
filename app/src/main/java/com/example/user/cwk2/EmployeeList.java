package com.example.user.cwk2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by USER on 7/30/2017.
 */
public class EmployeeList extends ArrayAdapter<Employee> {

    private Activity context;
    List<Employee> employees;

    public EmployeeList(Activity context, List<Employee> employees) {
        super(context, R.layout.layout_emp_list, employees);
        this.context = context;
        this.employees = employees;
    }

    @Override
    public View getView(int index, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View EmplistItem = inflater.inflate(R.layout.layout_emp_list, null, true);

        TextView fName = (TextView) EmplistItem.findViewById(R.id.fName);
        TextView lName = (TextView) EmplistItem.findViewById(R.id.lName);
        TextView mName = (TextView) EmplistItem.findViewById(R.id.mName);
        TextView dob = (TextView) EmplistItem.findViewById(R.id.dob);
        TextView tel = (TextView) EmplistItem.findViewById(R.id.tel);
        TextView adrs = (TextView) EmplistItem.findViewById(R.id.adrs);
        TextView email = (TextView) EmplistItem.findViewById(R.id.email);


        Employee employee = employees.get(index);
        fName.setText(employee.getfName());
        mName.setText(employee.getmName());
        lName.setText(employee.getlName());
        dob.setText(employee.getDob());
        tel.setText(employee.getTel());
        adrs.setText(employee.getAddress());
        email.setText(employee.getEmail());

        return EmplistItem;
    }
}
