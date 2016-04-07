package com.example.user.sqlitepreview;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    Cursor c=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {

          //  public void onClick(View v) {
                DataBaseHelper myDbHelper = new DataBaseHelper(MainActivity.this);
                // myDbHelper = new DataBaseHelper(this);

                try {

                    myDbHelper.createDataBase();

                } catch (IOException ioe) {

                    throw new Error("Unable to create database");

                }

                try {

                    myDbHelper.openDataBase();

                } catch (SQLException sqle) {

                    try {
                        throw sqle;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                c = myDbHelper.query("agency", null, null, null, null, null, null);
                if (c.moveToFirst()) {
                    do {
                        Toast.makeText(MainActivity.this,
                                "fare_id=" + c.getString(0)+
                                        "route_id=" + c.getString(1)+
                                        "origin=" + c.getString(2)+
                                        "currency_type=" + c.getString(3)+
                                         "payment_method=" + c.getString(4)
                                    /*   "transfers=" + c.getString(5)+
                                        "transfers=" + c.getString(6)+
                                        "transfers=" + c.getString(7)+
                                               "transfers=" + c.getString(8)*/
                                , Toast.LENGTH_LONG).show();
                    } while (c.moveToNext());

                }
            }
     //   });

      //  }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
