package com.example.jais.chessboard;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GridView chessboardGridView = (GridView) findViewById(R.id.gridview);
        chessboardGridView.setAdapter(new SquareAdapter(this));
        final TextView mTextView = (TextView) findViewById(R.id.textView);
        final SquareAdapter sq = new SquareAdapter(this);
        final RequestQueue queue = Volley.newRequestQueue(this);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                // Instantiate the RequestQueue.

                String url ="http://mobile.suitmedia.com/bl/chess.php";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                String anotherResponse = response.replace("<br/>","-");
                                String [] splits = anotherResponse.split("-");
                                Piece p = new Piece(splits);
                                final HashMap<Integer,Integer> dataParameter = p.getData();
                                sq.setDataParameter(dataParameter);
                                chessboardGridView.setAdapter(sq);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work!");
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        }, 3000);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
