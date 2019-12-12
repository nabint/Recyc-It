package com.example.recyc_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class C_Login_Register extends AppCompatActivity {
    private String type;
    private Intent mIntent;
    private Button Register,Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c__login__register);
        Register = (Button) findViewById(R.id.coll_register);
        Login = (Button) findViewById(R.id.coll_login);
        Bundle bundle = getIntent().getExtras();
        if(bundle !=null)
        {
            type = bundle.getString("name");
            Toast.makeText(this, " " + type, Toast.LENGTH_SHORT).show();
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.matches("Public"))
                {
                    mIntent = new Intent(C_Login_Register.this,Public_Login.class);
                    startActivity(mIntent);
                }
                else
                {
                    mIntent = new Intent(C_Login_Register.this,Collec_login.class);
                    startActivity(mIntent);
                }
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(type.matches("Public")) {
                        mIntent = new Intent(C_Login_Register.this, Public_form.class);
                        startActivity(mIntent);
                    }
                    else
                    {
                        mIntent = new Intent(C_Login_Register.this, Collec_form.class);
                        startActivity(mIntent);
                    }
            }
        });
    }
}
