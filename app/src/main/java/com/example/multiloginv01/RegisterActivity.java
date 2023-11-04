package com.example.multiloginv01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.multiloginv01.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    SharedPreferences variablePref;
    FirebaseAuth variableAuth;
    
    DatabaseReference variableDatabase;

    Button btnRegistroNuevo;
    TextInputEditText txtRegNuevoNombre;
    TextInputEditText txtRegNuevoEmail;
    TextInputEditText txtRegNuevoPassword;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        variableAuth = FirebaseAuth.getInstance();
        
        variableDatabase = FirebaseDatabase.getInstance().getReference();
        variablePref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);

        btnRegistroNuevo = findViewById(R.id.btnRegistrarUsuario);
        txtRegNuevoNombre = findViewById(R.id.txtRegistroNombre);
        txtRegNuevoEmail = findViewById(R.id.txtRegistroEmail);
        txtRegNuevoPassword = findViewById(R.id.txtRegistroPassword);
        btnRegistroNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistroNuevoUsuario();
            }
        });
            
        }

    private void RegistroNuevoUsuario() {
        final String name = txtRegNuevoNombre.getText().toString();
        final String email = txtRegNuevoEmail.getText().toString();
        final String password = txtRegNuevoPassword.getText().toString();


        variableAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                saveUser(name, email);
            }
        });


    }

    private void saveUser(String name, String email) {

        String selectedUser = variablePref.getString("user", "");

        User user = new User();
        user.setEmail(email);
        user.setName(name);

        if (selectedUser.equals("drivers")){

        variableDatabase.child("Users").child("Drivers").push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "FALLO EL REGISTRO", Toast.LENGTH_SHORT).show();
                }
            }
        });
            }

        else if(selectedUser.equals("clients")){
            variableDatabase.child("Users").child("Clients").push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "FALLO EL REGISTRO", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

}
}