package amalhichri.androidprojects.com.androidforegroundservicesexample;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        
        
        (findViewById(R.id.startBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int lState = MyForegroundService.getState();
                if (lState == Statics.STATE_SERVICE.NOT_INIT) {
                    if (!NetworkHelper.isInternetAvailable(v.getContext())) {
                        showError(v);
                        return;
                    }
                    Intent startIntent = new Intent(v.getContext(), MyForegroundService.class);
                    startIntent.setAction(Statics.ACTION.START_ACTION);
                    startService(startIntent);
                } else if (lState == Statics.STATE_SERVICE.PREPARE ||
                        lState == Statics.STATE_SERVICE.PLAY) {
                    Intent lPauseIntent = new Intent(v.getContext(), MyForegroundService.class);
                    lPauseIntent.setAction(Statics.ACTION.PAUSE_ACTION);
                    PendingIntent lPendingPauseIntent = PendingIntent.getService(v.getContext(), 0, lPauseIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    try {
                        lPendingPauseIntent.send();
                    } catch (PendingIntent.CanceledException e) {
                        e.printStackTrace();
                    }
                } else if (lState == Statics.STATE_SERVICE.PAUSE) {
                    if (!NetworkHelper.isInternetAvailable(v.getContext())) {
                        showError(v);
                        return;
                    }
                    Intent lPauseIntent = new Intent(v.getContext(), MyForegroundService.class);
                    lPauseIntent.setAction(Statics.ACTION.PLAY_ACTION);
                    PendingIntent lPendingPauseIntent = PendingIntent.getService(v.getContext(), 0, lPauseIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    try {
                        lPendingPauseIntent.send();
                    } catch (PendingIntent.CanceledException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    private void showError(View v) {
        Toast.makeText(getApplicationContext(),"No internet access!",
                Toast.LENGTH_SHORT).show();
    }

}