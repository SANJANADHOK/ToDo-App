package co.in.nextgencoder.logintask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.in.nextgencoder.logintask.Model.User;
import co.in.nextgencoder.logintask.Model.Validation;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nameET, mailET , passET;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private Validation  validation = new Validation();

    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        nameET = findViewById(R.id.nameET);
        mailET = findViewById(R.id.mailregisterET);
        passET = findViewById(R.id.passregisterET);
    }

    public void RegisterUser(View view) {
        String name = nameET.getText().toString().trim();
        String mail = mailET.getText().toString().trim();
        String password = passET.getText().toString().trim();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        String validationMsg = validation.Validation(mail, password);

        if (validationMsg.equals("successful")) {
            firebaseAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            DatabaseReference userReference = databaseReference.child("User");
                            String id = firebaseAuth.getUid();
                            User user = new User(id, name, mail);
                            userReference.child(id).setValue(user);

                            Toast.makeText(RegistrationActivity.this, validationMsg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                        }
                        else {
                            Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        }
    }

    public void goToLogin(View view) {

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}