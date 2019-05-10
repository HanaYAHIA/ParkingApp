package khpplication.com.parkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

        private FirebaseAuth mAuth;
    public void button(View view) {
        startActivity(new Intent(getApplicationContext(),SpotList.class));
    }

    public void button2(View view) {
        startActivity(new Intent(getApplicationContext(),SignUp.class));

    }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            getSupportActionBar().setTitle("MainActivity");

            mAuth = FirebaseAuth.getInstance();

            final EditText password = findViewById(R.id.editText12);
            final EditText email = findViewById(R.id.editText11);
            Button btn = findViewById(R.id.button);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(MainActivity.this, "You are welcome ", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(MainActivity.this, "Try again ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });
        }
    }

