package co.edu.unipiloto.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;
    private int lapCount = 0;

    private TextView timeView;
    private TextView lapCountTextView;
    private TextView lapTimesTextView;
    private Button lapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    private void initializeViews() {
        timeView = findViewById(R.id.time_view);
        lapCountTextView = findViewById(R.id.nLap_textView);
        lapTimesTextView = findViewById(R.id.time_textView);
        lapButton = findViewById(R.id.lap_button);
    }

    public void onClickStart(View view){
        running = true;
    }

    public void onClickStop(View view){
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
        lapCount = 0;
        lapButton.setEnabled(true);
        lapCountTextView.setText("");
        lapTimesTextView.setText("");
    }

    public void onClickLap(View view) {
        if (lapCount == 0) {
            lapCountTextView.setText("Numero De Vueltas \n");
            lapTimesTextView.setText("Tiempo Vuelta\n");
        }
        lapCount++;
        lapCountTextView.append(lapCount + "\n");
        lapTimesTextView.append(timeView.getText() + "\n");
        if (lapCount == 5) {
            lapButton.setEnabled(false);
        }
    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
