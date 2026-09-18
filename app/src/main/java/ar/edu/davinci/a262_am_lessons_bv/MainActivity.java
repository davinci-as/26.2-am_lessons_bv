package ar.edu.davinci.a262_am_lessons_bv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public int counter = 0;
    public final int DETAILS_UPDATE = 1;

    public void openPreview(View view) {
        Intent intent = new Intent(this, PreviewActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode != DETAILS_UPDATE) return;
        if(resultCode != RESULT_OK) return;

        //TextView textViewer = findViewById(R.id.textViewer);

        Log.i("activity-result", "ok");

    }

    public void addNewEntry(View view) {
        Log.i("new-view", "Testing view");
        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        counter++;
        for (int i = 0; i < 20; i++) {
            TextView textView = new TextView(this);
            textView.setText(
                    String.format("Counter: %d - %d", counter, i)
            );
            int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), PreviewActivity.class);
                    intent.putExtra("counter", counter);
                    intent.putExtra("index", finalI);
                    startActivityForResult(intent, DETAILS_UPDATE);

                }
            });
            container.addView(textView);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}