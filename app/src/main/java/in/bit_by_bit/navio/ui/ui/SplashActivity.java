package in.bit_by_bit.navio.ui.ui;

import androidx.appcompat.app.AppCompatActivity;
import in.bit_by_bit.navio.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, ChooseLocation.class));
                finish();
            }
        }, 2000);
    }
}
