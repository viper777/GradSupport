package com.example.a21701125.testingitout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by 21701125 on 13/05/2017.
 */

public class Display extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.display);

        String username=getIntent().getStringExtra("Username");
        TextView tv=(TextView)findViewById(R.id.TVusername);
   tv.setText(username);
        GridView gridview = (GridView)findViewById(R.id.gridview);
        gridview.setAdapter(new imageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int i, long id) {
              if(i==0)
              {
               if(googleServicesAvailable()){
                    Toast.makeText(Display.this,"lllllll" + i,
                        Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Display.this,mapDemo.class);
                startActivity(intent);           }
                else
                {
                    Toast.makeText(Display.this,"no map",Toast.LENGTH_LONG).show();

                }}
                else {
                  Toast.makeText(Display.this,"lllllll" + i,
                          Toast.LENGTH_SHORT).show();

              }
            }
        });
    }
   public boolean googleServicesAvailable()
    {
        GoogleApiAvailability api=GoogleApiAvailability.getInstance();
        int isAvailable=api.isGooglePlayServicesAvailable(this);
        if(isAvailable== ConnectionResult.SUCCESS)
        {
            return true;
        }
        else if(api.isUserResolvableError(isAvailable))
        {
            Dialog dialog=api.getErrorDialog(this,isAvailable,0);
            dialog.show();
        }
        else
        {
            Toast.makeText(this,"con't connect",Toast.LENGTH_LONG).show();

        }
        return false;
    }

}
