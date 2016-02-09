package com.krzysiek.map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginScreen extends AppCompatActivity implements View.OnClickListener {


    private static final String PREFERENCES_NAME = "myPreferences";
    private static final String PASSWORD = "123";
    private static final String LOGIN = "admin";

    private SharedPreferences preferences;

    Button login;
    TextView tv1;
    TextView tv2;
    EditText username;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);

        login = (Button) findViewById(R.id.login);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        username = (EditText) findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText2);

        login.setOnClickListener(this);

        restoreData();

    }

    @Override
    public void onClick(View v) {

        String login=username.getText().toString();
        String password=pass.getText().toString();


        if (login.equals(LOGIN) && password.equals(PASSWORD)){
            saveData();
            Intent start = new Intent(LoginScreen.this, MapsActivity.class);
            startActivity(start);
        }
        else{
            Toast msg1 = Toast.makeText(getBaseContext(),
                    "NieprawidĹ‚owy login lub hasĹ‚o", Toast.LENGTH_LONG);
            msg1.show();
        }

    }
    private void saveData() {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        String editTextData1 = username.getText().toString();
        String editTextData2 = pass.getText().toString();
        preferencesEditor.putString(LOGIN, editTextData1);
        preferencesEditor.putString(PASSWORD, editTextData2);
        preferencesEditor.commit();
    }

    private void restoreData() {
        String textFromPreferences1 = preferences.getString(LOGIN, "");
        String textFromPreferences2 = preferences.getString(PASSWORD, "");
        username.setText(textFromPreferences1);
        pass.setText(textFromPreferences2);
        if (textFromPreferences1.equals(LOGIN) && textFromPreferences2.equals(PASSWORD)){
            Intent start = new Intent(LoginScreen.this, MapsActivity.class);
            startActivity(start);
        }
    }
}

