package khpplication.com.parkingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    TextView spot;
    TextView hours;
    TextView Total;
    Button button;
    TextView message;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Home");
        hours = (TextView) findViewById(R.id.hours);
        Total = (TextView) findViewById(R.id.Total);
        message = (TextView) findViewById(R.id.message);
        spot = (TextView) findViewById(R.id.spot);
        button = (Button) findViewById(R.id.button);

        final String s = getIntent().getStringExtra("info");
        spot.setText(s);

        button.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        if (hours.getText().toString().isEmpty() || Total.getText().toString().isEmpty()) {
                            message.setTextColor(Color.RED);
                            message.setText("Fill in everything !");
                        }
                        else{
                            DatabaseReference ref = database.getReference("users");
                            ref.child(mAuth.getCurrentUser().getUid()).child("numSpot").setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    message.setText("Parking confirmed");
                                }
                            });
                            }
                            Total.setText(Double.toString(calculateTotal()));
                        }

                }
        );
    }
    private double calculateTotal(){
        double i = Double.parseDouble( hours.getText().toString()) * 3.5 ;
        return i ;
    }
}
