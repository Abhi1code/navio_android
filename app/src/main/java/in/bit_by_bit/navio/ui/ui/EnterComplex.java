package in.bit_by_bit.navio.ui.ui;

import androidx.appcompat.app.AppCompatActivity;
import in.bit_by_bit.navio.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnterComplex extends AppCompatActivity {

    Button entercomplex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_complex);
        entercomplex = findViewById(R.id.entercomplex);
        entercomplex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EnterComplex.this, EnterComplexIns.class));
                finish();
            }
        });
    }
}
