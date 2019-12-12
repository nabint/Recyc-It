package com.example.recyc_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Public_form extends AppCompatActivity {

    private EditText memail,mpassword,mHouseId;
    private Button mSignup;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_form);
        memail = findViewById(R.id.pu1_email);
        mpassword = findViewById(R.id.pu1_password);
        mSignup = findViewById(R.id.pu1_sign_up);
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewFirebaseUser();
            }
        });
    }
    private void createNewFirebaseUser()
    {
        String email =memail.getText().toString();
        String password = mpassword.getText().toString();
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Public_form.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Intent intent = new Intent(Public_form.this,C_Login_Register.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
