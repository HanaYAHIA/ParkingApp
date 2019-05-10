package khpplication.com.parkingapp;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    public void button3(View view) {
        startActivity(new Intent(getApplicationContext(),SpotList.class));
    }


        private FirebaseAuth mAuth;
        private FirebaseDatabase database;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_up);
            getSupportActionBar().setTitle("SignUp");

            mAuth = FirebaseAuth.getInstance();
            database = FirebaseDatabase.getInstance();


            final EditText password = findViewById(R.id.editText8);
            final EditText email = findViewById(R.id.editText);
            Button btn = findViewById(R.id.button3);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Task<AuthResult> authResultTask = mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        insertUser();
                                    }
                                }
                            });
                }
            });
        }
        private void insertUser(){
            User user = new User();
            final EditText name = findViewById(R.id.editText6);
            user.name = name.getText().toString();
            final EditText  username= findViewById(R.id.editText7);
            user.username = username.getText().toString();
            final EditText confpsd = findViewById(R.id.editText9);
            user.confpsd = confpsd.getText().toString();
            user.numspot=0;

            DatabaseReference ref = database.getReference("users");
            ref.child(mAuth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignUp.this, "USER ADDED", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
}
