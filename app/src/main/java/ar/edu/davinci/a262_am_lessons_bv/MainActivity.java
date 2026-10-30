package ar.edu.davinci.a262_am_lessons_bv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
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

        if(!(data != null && data.hasExtra("newValue"))) return;
        String newValue = data.getStringExtra("newValue");

        TextView textViewer = findViewById(R.id.textViewer);
        textViewer.setText(newValue);
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

        this.mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = this.mAuth.getCurrentUser();
        if(user != null) {
            // existe un usuario
            Log.i("login-test", "usuario encontrado");
            //llamado a /api/character
            String query = "https://rickandmortyapi.com/api/character";
            new ApiRequest().execute(query);
        } else {
            // redireccion al activity login -> Intent
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}