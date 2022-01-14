package com.abuafza.afzaticketsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Booking extends AppCompatActivity {
    TextView mName;
    EditText mDate, mTime, mPassener, mSeat;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    int hour, minute;
    Spinner mySpinner,mySpinner1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        mName           = findViewById(R.id.textViewName);
        mDate           = findViewById(R.id.editTextDate);
        fAuth           = FirebaseAuth.getInstance();
        fStore          = FirebaseFirestore.getInstance();
        userID          = fAuth.getCurrentUser().getUid();
        mTime           = findViewById(R.id.editTime);
        mPassener       = findViewById(R.id.passengercount);
        mSeat           = findViewById(R.id.selectseat);
        mySpinner       = findViewById(R.id.fromlist);
        mySpinner1      = findViewById(R.id.destlist);

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);



        //for user name start
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                mName.setText(documentSnapshot.getString("fName"));
            }
        });
        //for user name end

        //for date picker start
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Booking.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = dayOfMonth+"/"+month+"/"+year;
                mDate.setText(date);
            }
        };

        //for date picker end

        //for from list start

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Booking.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.fromlist));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        //for from list end

        //for Destination start

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(Booking.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.destlist));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner1.setAdapter(myAdapter1);

        //for Destination end

    }

    //for time picker start
    public void popTimePicker(View v){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                mTime.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
    //for time picker end
    // for ticket fair page start
    public void ticketFair(View v){
        String data1 = mDate.getText().toString();
        String data2 = mTime.getText().toString();
        String data3 = mySpinner.getSelectedItem().toString();
        String data4 = mySpinner1.getSelectedItem().toString();
        String data5 = mPassener.getText().toString();
        String data6 = mSeat.getText().toString();

        Intent ticketpageview = new Intent(this, TicketFair.class);
        ticketpageview.putExtra("data1",data1);
        ticketpageview.putExtra("data2",data2);
        ticketpageview.putExtra("data3",data3);
        ticketpageview.putExtra("data4",data4);
        ticketpageview.putExtra("data5",data5);
        ticketpageview.putExtra("data6",data6);

        startActivity(ticketpageview);
    }
    // for ticket fair page end

    // for login page start
    public void logout(View v){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LoginPage.class));
        finish();
    }
    // for login page end


}