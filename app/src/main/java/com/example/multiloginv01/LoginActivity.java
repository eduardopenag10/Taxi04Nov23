package com.example.multiloginv01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText IngEmail;
    TextInputEditText IngPassword;
    Button btnIngresar;

    FirebaseAuth vAuth;
    DatabaseReference vDatabase;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





        IngEmail = findViewById(R.id.txtEmail);
        IngPassword = findViewById(R.id.txtPassword);
        btnIngresar =  findViewById(R.id.btnIngresar);

        vAuth = FirebaseAuth.getInstance();
        vDatabase = FirebaseDatabase.getInstance().getReference();
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IngresarApp();
            }
        });

    }


    private void IngresarApp() {

        String email =  IngEmail.getText().toString();
        String password = IngPassword.getText().toString();

            if (!email.isEmpty() && !password.isEmpty()){
                if (password.length() >= 6){
                    vAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "INGRESO CORRECTO", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                Toast.makeText(LoginActivity.this, "PASSWORD INCORRECTO", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            else {
                Toast.makeText(this, "La contraseña debe tener mas de 6 digitos", Toast.LENGTH_SHORT).show();
            }

            }

            else {
                Toast.makeText(this, "Email y Password obligatorios!!!", Toast.LENGTH_SHORT).show();
    }
    }
}
