package edu.illinois.cs.cs125.mp7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MainActivity extends AppCompatActivity {

    String YOUR_API_KEY = "AIzaSyBOpGPVyJWQCtDhTXVpFhBF1ZZNIfR6r6s";

    String start = "";
    String destination = "";

    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "MP7:Schedule Logistics";

    /** Request queue for our network requests. */
    private static RequestQueue requestQueue;
    /*
    The button to get directions.
     */
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String driveTime;
    public String distance;

    /*
        Button to get Directions from Google.
     */
    public void button(View v) {
        button = (Button) findViewById(R.id.button);

        setContentView(R.layout.activity_main);
        TextView dTime = (TextView) findViewById(R.id.estTime);
        dTime.setText(driveTime);

        setContentView(R.layout.activity_main);
        TextView dist = (TextView) findViewById(R.id.Distance);
        dist.setText(distance);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                startAPICall();
            }
        });
    }

    /**
     * get text to form URL API call.
     */
    public String getText(final String input) {
        String formatted = input.toLowerCase();
        formatted.trim();
        formatted.replace(" ","%20");
        formatted.replace(",","%2C");
        return formatted;
    }

    /**
     * Make an API call.
     */
    public void startAPICall() {
        EditText currLoc = (EditText) findViewById(R.id.currLoc);
        EditText destLoc = (EditText) findViewById(R.id.destLoc);
        start = currLoc.getText().toString();
        destination = destLoc.getText().toString();
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://maps.googleapis.com/maps/api/directions/json?origin=" + start + "&destination=" + destination + "&key=" + YOUR_API_KEY,
            null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d(TAG, response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(final VolleyError error) {
                            Log.w(TAG, error.toString());
                        }
                    });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * parse the time out.
     */
    public double getTime(final JsonObject json) {
        if (json == null) {
            return 0;
        }
        JsonObject result = json;
        JsonArray routes = result.getAsJsonArray("routes");
        JsonArray legs = routes.get(2).getAsJsonArray();
        JsonObject duration = legs.get(2).getAsJsonObject();
        double time = duration.get("text").getAsDouble();
        return time;
    }
}