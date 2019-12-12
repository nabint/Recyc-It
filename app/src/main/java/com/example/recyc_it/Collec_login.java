package com.example.recyc_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class Collec_login extends AppCompatActivity {

    private EditText memail,mpassword;
    private Button Login;
    private FirebaseAuth mAuth;
    private CircleImageView mCircleImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collec_login);
        memail = (EditText) findViewById(R.id.c_l_email);
        mpassword = (EditText) findViewById(R.id.c_l_password);
        mCircleImageView = findViewById(R.id.garbage_logo);
        mCircleImageView.setBackgroundResource(R.drawable.garbage_collector);
        mAuth = FirebaseAuth.getInstance();
        Login = (Button) findViewById(R.id.c_l_login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = memail.getText().toString();
                final String password = mpassword.getText().toString();
                Toast.makeText(Collec_login.this, "hey " + email + " " + password, Toast.LENGTH_SHORT).show();
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Collec_login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(Collec_login.this,"Sign In Error",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Intent intent = new Intent(Collec_login.this, Collec_mainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });

    }
}
