package com.example.loginscreeenattempt;

import static com.example.loginscreeenattempt.R.id.button2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static String privateUsername;
    private static String privatePassword;
    private static boolean isRegistered;
    EditText username, password;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username = (EditText) findViewById(R.id.editUserName);
        EditText password = (EditText) findViewById(R.id.editPassword);

        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);


        //validator (naudoti register) and REGISTERING
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean cancel = false;

//                (we know that at least one account is registered during this session REMOVE THIS LATER
//                if (isValidUsername(username.getText().toString()) && isValidPass(password.getText().toString())){
//                    MainActivity.isRegistered = true;
//                    }

        if (!isValidUsername(username.getText().toString())){
                username.setError(getString(R.string.login_invalid_username));
                cancel = true;
            } else {
                MainActivity.privateUsername =  username.getText().toString();
            }

        if (!isValidPass(password.getText().toString())){
                password.setError(getString(R.string.login_invalid_password));
                cancel = true;
            } else {
                MainActivity.privatePassword = password.getText().toString();
            }
        }
        });



        //login
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View a) {
                //THIS SHIT AINT WORKING..
                // i NEED TO FIGURE OUT HOW TO STORE THE PASSWORDS AND SHIT...
                // THIS MIGHT NEED ANOTHER FILE FOR THE CLASS...
                // SO MY MIND WONT GET SO FULL...
                // BUT THIS MIGHT BE QUITE HARD. GL .

                // FIND OUT HOW TO GET THIS INFO (to get info from another class (public void or whatever)).
                if( privateUsername.equals(null) || privatePassword.equals(null)||username.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "REGISTER first!",
                            Toast.LENGTH_LONG).show();
                }
                else if (privateUsername.equals(username.getText().toString()) && privatePassword.equals(password.getText().toString())) {
                    //perejimas i kita langa.
                    Intent activityChangeIntent = new Intent(MainActivity.this, loggedInActivity.class);
                    MainActivity.this.startActivity(activityChangeIntent);

                    //TOAST'ukas "Logging in!" - tik kai tinka username ir password.
                    Toast.makeText(MainActivity.this, "Logging in!",
                            Toast.LENGTH_LONG).show();
                } else {
                    //TOAST'ukas "Loading" - jeigu netinka username ir password.
                    Toast.makeText(MainActivity.this, "WRONG PASSWORD! 🤓",
                            Toast.LENGTH_LONG).show();
                }

            }

        });
    }

    private boolean isValidUsername(String credentials) {
        final String CREDENTIAL_PATTERN_USERNAME = "^[a-zA-Z0-9_-]{3,20}$";

        Pattern pattern = Pattern.compile(CREDENTIAL_PATTERN_USERNAME);

        Matcher matcher = pattern.matcher(credentials);

        return matcher.matches();
    }

    private boolean isValidPass(String credentials) {
        final String CREDENTIAL_PATTERN_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.!@_–[{}]:;',?/*~$^+=<>]).{5,20}$";

        Pattern pattern = Pattern.compile(CREDENTIAL_PATTERN_PASSWORD);

        Matcher matcher = pattern.matcher(credentials);

        return matcher.matches();
    }
    }
