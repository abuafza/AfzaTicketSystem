package com.abuafza.afzaticketsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class TicketFair extends AppCompatActivity {
    TextView mName, mydate,mytime,myfrom,mydest,mypasse,myseat,myfare;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_fair);

        mName           = findViewById(R.id.textViewName1);
        mydate          = findViewById(R.id.datefillup);
        mytime          = findViewById(R.id.timefillup);
        myfrom          = findViewById(R.id.fromfillup);
        mydest          = findViewById(R.id.destfillup);
        mypasse         = findViewById(R.id.passefillup);
        myseat          = findViewById(R.id.seatfillup);
        myfare          = findViewById(R.id.farefillup);
        fAuth           = FirebaseAuth.getInstance();
        fStore          = FirebaseFirestore.getInstance();
        userID          = fAuth.getCurrentUser().getUid();


        //for user name start
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                mName.setText(documentSnapshot.getString("fName"));
            }
        });
        //for user name end

        //for get data from Booking page start
        String data1 = getIntent().getExtras().getString("data1");
        String data2 = getIntent().getExtras().getString("data2");
        String data3 = getIntent().getExtras().getString("data3");
        String data4 = getIntent().getExtras().getString("data4");
        String data5 = getIntent().getExtras().getString("data5");
        String data6 = getIntent().getExtras().getString("data6");
        String data7 = "$" + String.valueOf(Integer.parseInt(data5)*20);

        mydate.setText(data1);
        mytime.setText(data2);
        myfrom.setText(data3);
        mydest.setText(data4);
        mypasse.setText(data5);
        myseat.setText(data6);
        myfare.setText(data7);


        //for get data from Booking page end
    }
    // for edit page start
    public void ticketFair(View v){
        Intent bookingpageview = new Intent(this, Booking.class);
        startActivity(bookingpageview);
    }
    // for edit page end

    // for payment page start
    public void payment(View v){
        String data8 = myfare.getText().toString();

        Intent paymentpageview = new Intent(this, PaymentPage.class);
        paymentpageview.putExtra("data8",data8);

        startActivity(paymentpageview);
    }
    // for payment page end

    // for login page start
    public void backtologin(View v){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LoginPage.class));
        finish();
    }
    // for login page end
}