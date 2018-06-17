package amalhichri.androidprojects.com.androidforegroundservicesexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        (findViewById(R.id.startBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MainActivity.this, Statics.class);
                startIntent.setAction(Statics.ACTION.STARTFOREGROUND_ACTION);
                startService(startIntent);
            }
        });

        (findViewById(R.id.stopBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopIntent = new Intent(MainActivity.this, MyForegroundService.class);
                stopIntent.setAction(Statics.ACTION.STOPFOREGROUND_ACTION);
                startService(stopIntent);
            }
        });
    }
}