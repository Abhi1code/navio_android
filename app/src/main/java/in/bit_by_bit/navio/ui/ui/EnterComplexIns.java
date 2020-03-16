package in.bit_by_bit.navio.ui.ui;

import androidx.appcompat.app.AppCompatActivity;
import in.bit_by_bit.navio.R;
import in.bit_by_bit.navio.ui.ui.scanner.BarcodeCaptureActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnterComplexIns extends AppCompatActivity {

    Button entercomplexins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_complex_ins);
        entercomplexins = findViewById(R.id.entercomplexins);
        entercomplexins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EnterComplexIns.this, BarcodeCaptureActivity.class));
                finish();
            }
        });
    }
}
