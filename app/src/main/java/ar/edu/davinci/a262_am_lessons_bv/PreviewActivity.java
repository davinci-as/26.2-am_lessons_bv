package ar.edu.davinci.a262_am_lessons_bv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PreviewActivity extends AppCompatActivity {

    public void saveData(View view) {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preview);
        Intent intent = getIntent();
        Log.i("intent", "ok");
        if(intent.hasExtra("index") && intent.hasExtra("counter")) {
            int counter = intent.getIntExtra("counter", 0);
            int index = intent.getIntExtra("index", 0);
            String value = String.format("%d - %d", counter, index);
            TextView textView = findViewById(R.id.textView);
            textView.setText(String.valueOf(value));
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}