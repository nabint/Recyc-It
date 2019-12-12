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

public class Public_Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText memail,mpassword,mHouse;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public__login);

        mHouse = findViewById(R.id.pub_house);
        mButton = (Button) findViewById(R.id.pub_login1);
        memail = (EditText) findViewById(R.id.pub_email);
        mpassword = (EditText) findViewById(R.id.pub_password);
        mAuth=FirebaseAuth.getInstance();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = memail.getText().toString();
                final String password = mpassword.getText().toString();
                Toast.makeText(Public_Login.this, "hey " + email + " " + password, Toast.LENGTH_SHORT).show();
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Public_Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful() || mHouse.getText().toString().equals(""))
                        {
                            Toast.makeText(Public_Login.this,"Sign In Error",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Intent intent = new Intent(Public_Login.this, Public_main.class);
                            intent.putExtra("Housenum",mHouse.getText().toString());
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }
}
