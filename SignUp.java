package com.example.a21701125.testingitout;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 21701125 on 14/05/2017.
 */

public class SignUp extends Activity {
    Databasehelper helper = new Databasehelper(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void onSignUpClick(View v) {
        if (v.getId() == R.id.Bsignupbutton) {
            EditText name = (EditText) findViewById(R.id.TFname);
            EditText email = (EditText) findViewById(R.id.TFemail);
            EditText uname = (EditText) findViewById(R.id.TFuname);
            EditText pass1 = (EditText) findViewById(R.id.TFpass1);
            EditText pass2 = (EditText) findViewById(R.id.TFpass2);
            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();


            if (!pass1str.equals(pass2str)) {
                Toast pass = Toast.makeText(SignUp.this, "Passwords don't match", Toast.LENGTH_SHORT);
                pass.show();
                return;
            } else if (name.length() < 5) {
                Toast pass = Toast.makeText(SignUp.this, "name should be more then 5 character", Toast.LENGTH_SHORT);
                pass.show();
                return;
            } else if (uname.length() < 3) {
                Toast pass = Toast.makeText(SignUp.this, "uname should be more then 5 character", Toast.LENGTH_SHORT);
                pass.show();
                return;
            } else if (email.length() < 5) {
                Toast pass = Toast.makeText(SignUp.this, "email should be more then 5 character", Toast.LENGTH_SHORT);
                pass.show();
                return;
            } else {
                Contact c = new Contact();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUname(unamestr);
                c.setPass(pass1str);
                helper.insertContact(c);
            }
            Intent i = new Intent(SignUp.this, MainActivity.class);
            Toast pass = Toast.makeText(SignUp.this, "congrats on you new account Please login with your ID and password", Toast.LENGTH_LONG);
            startActivity(i);
        }
    }

}

