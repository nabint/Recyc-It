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

import de.hdodenhof.circleimageview.CircleImageView;

public class Collec_form extends AppCompatActivity {

    private Button SignUp;
    private EditText mEmail,mPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collec_reg_form);

        SignUp = (Button) findViewById(R.id.colex_sign_up);
        mEmail = (EditText) findViewById(R.id.colex_email);
        mPassword = (EditText) findViewById(R.id.colex_password);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewFirebaseUser();
            }
        });
    }
    private void createNewFirebaseUser()
    {
        String email =mEmail.getText().toString();
        String password = mPassword.getText().toString();
        mAuth =FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Collec_form.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Intent intent = new Intent(Collec_form.this,C_Login_Register.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
