package edu.illinois.cs.cs125.mp7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
    The button to get directions.
     */
    Button button;
    /*
    The editable text box for current Location;
     */
    EditText currLoc;
    /*
    The editable text box for destinated location.
     */
    EditText destLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currLoc = (EditText) findViewById(R.id.currLoc);
        destLoc = (EditText) findViewById(R.id.destLoc);
    }

    /*
        Button to get Directions from Google.
     */
    public void button(View v) {
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }
}