package com.abuafza.afzaticketsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void loginpage(View v){
        //send me to the login page
        Intent loginpageview = new Intent(this, LoginPage.class);
        startActivity(loginpageview);
    }

    //for menu code start

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.loginmenu:
                Intent loginmenupage = new Intent(this,LoginPage.class);
                startActivity(loginmenupage);
                return true;
            case R.id.registermenu:
                Intent registermenupage = new Intent(this,Register.class);
                startActivity(registermenupage);
                return true;
            case R.id.webmenu:
                Intent websitemenupage = new Intent(this,Website.class);
                startActivity(websitemenupage);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //for menu code end
}