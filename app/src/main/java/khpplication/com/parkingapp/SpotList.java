package khpplication.com.parkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SpotList extends AppCompatActivity {
ListView listview ;
    List<String> spots = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_list);
        spots.add("Spot 1");
        spots.add("Spot 2");
        spots.add("Spot 3");
        spots.add("Spot 4");
        spots.add("Spot 5");
        spots.add("Spot 6");
        spots.add("Spot 7");
        spots.add("Spot 8");
        spots.add("Spot 9");
        spots.add("Spot 10");
        listview = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,spots);
        listview.setAdapter(arrayadapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = spots.get(position);
                Intent intent = (new Intent(getApplicationContext(),Home.class));
                intent.putExtra("info",s);
                startActivity(intent);
            }
        });
    }
}
