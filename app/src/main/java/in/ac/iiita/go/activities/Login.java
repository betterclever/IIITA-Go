package in.ac.iiita.go.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.ac.iiita.go.HomeActivity;
import in.ac.iiita.go.R;

public class Login extends AppCompatActivity {

    SharedPreferences.Editor editor;
    SharedPreferences sharedpreferences;
    Spinner sItemsSec,sItemsCourse,sItemsSem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        List<String> spinnerArraysection =  new ArrayList<String>();
        spinnerArraysection.add("A");
        spinnerArraysection.add("B");
        spinnerArraysection.add("C");

        List<String> spinnerArraycourse =  new ArrayList<String>();
        spinnerArraycourse.add("B.Tech");
        spinnerArraycourse.add("M.Tech");

        List<String> spinnerArraysemester =  new ArrayList<String>();
        spinnerArraysemester.add("1");
        spinnerArraysemester.add("2");
        spinnerArraysemester.add("3");
        spinnerArraysemester.add("4");
        spinnerArraysemester.add("5");
        spinnerArraysemester.add("6");
        spinnerArraysemester.add("7");
        spinnerArraysemester.add("8");

        ArrayAdapter<String> adapterSection = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArraysection);
        ArrayAdapter<String> adapterCourse = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArraycourse);
        ArrayAdapter<String> adapterSemester = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArraysemester);

        adapterSemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterSection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sItemsSec = (Spinner) findViewById(R.id.sec);
        sItemsSec.setAdapter(adapterSection);

        sItemsCourse = (Spinner) findViewById(R.id.course);
        sItemsCourse.setAdapter(adapterCourse);

        sItemsSem = (Spinner) findViewById(R.id.sem);
        sItemsSem.setAdapter(adapterSemester);
        sharedpreferences = getSharedPreferences("INFO_USR", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public void save(View view) {
        editor.putString("NAME", ((EditText) findViewById(R.id.name)).getText()+"");
        editor.putString("ROLL", ((EditText) findViewById(R.id.enr)).getText()+"");
        editor.putString("SEC", sItemsSec.getSelectedItem().toString()+"");
        editor.putString("COURSE", sItemsCourse.getSelectedItem().toString()+"");
        editor.putString("SEMESTER", sItemsSem.getSelectedItem().toString());
        editor.commit();
        Toast.makeText(Login.this,"Thanks..", Toast.LENGTH_LONG).show();
        Intent myIntent = new Intent(Login.this, HomeActivity.class);
        Login.this.startActivity(myIntent);
    }
}
