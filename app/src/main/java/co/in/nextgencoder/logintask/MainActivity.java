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

import co.in.nextgencoder.logintask.Model.Todo;
import co.in.nextgencoder.logintask.Model.Validation;

public class MainActivity extends AppCompatActivity {


    private DatabaseReference databaseReference;
    private EditText editToDo;
     private FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editToDo = findViewById(R.id.editToDO);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void addTodo(View view) {
        String todoText = editToDo.getText().toString().trim();

        DatabaseReference toDoReference = databaseReference.child("User").child(firebaseAuth.getUid()).child("Task");
        String id = toDoReference.push().getKey();
        Todo todo = new Todo(id,  false,todoText );
        toDoReference.child(id).setValue(todo );

    }
}


