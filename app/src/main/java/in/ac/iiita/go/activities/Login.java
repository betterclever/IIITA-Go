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
    Spinner sItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("A");
        spinnerArray.add("B");
        spinnerArray.add("C");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItems = (Spinner) findViewById(R.id.sec);
        sItems.setAdapter(adapter);

        sharedpreferences = getSharedPreferences("INFO_USR", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public void save(View view) {
        editor.putString("ROLL", ((EditText) findViewById(R.id.enr)).getText()+"");
        editor.putString("SEC", sItems.getSelectedItem().toString());
        editor.commit();
        Toast.makeText(Login.this,"Thanks..", Toast.LENGTH_LONG).show();
        Intent myIntent = new Intent(Login.this, HomeActivity.class);
        Login.this.startActivity(myIntent);
    }
}
