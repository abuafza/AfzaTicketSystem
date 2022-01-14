package com.abuafza.afzaticketsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class PaymentPage extends AppCompatActivity {
    TextView myName;
    Button myBtn;
    String userID;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);

        myName          = findViewById(R.id.myname);
        myBtn           = findViewById(R.id.lastpage);
        fAuth           = FirebaseAuth.getInstance();
        fStore          = FirebaseFirestore.getInstance();
        userID          = fAuth.getCurrentUser().getUid();

        //for user name start
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                myName.setText(documentSnapshot.getString("fName"));
            }
        });
        //for user name end

        //for get data from TicketFair page start
        String data8 = "Pay " + getIntent().getExtras().getString("data8");
        myBtn.setText(data8);

        //for get data from TicketFair page end

    }

    // for final payment and final page start
    public void pay(View v){
        Intent finalpageview = new Intent(this, FinalPage.class);
        startActivity(finalpageview);
    }
    // for final payment and final page end

    // for login page start
    public void abacktologin(View v){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LoginPage.class));
        finish();
    }
    // for login page end
}