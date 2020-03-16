package in.bit_by_bit.navio.ui.ui;

import androidx.appcompat.app.AppCompatActivity;
import in.bit_by_bit.navio.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChooseLocation extends AppCompatActivity {

    Button chooseLocation, currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);
        chooseLocation = findViewById(R.id.chooselocation);
        currentLocation = findViewById(R.id.currentlocation);
        chooseLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChooseLocation.this, "Feature not added", Toast.LENGTH_SHORT).show();
            }
        });
        currentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseLocation.this, EnterComplex.class));
                finish();
            }
        });
    }
}
