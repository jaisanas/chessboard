package com.example.jais.chessboard;

import android.os.Bundle;
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
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTextView.setText("");
                                // update TextView here!
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
                                        mTextView.setText("Something Happened On Connection!");
                                    }
                                });
                                // Add the request to the RequestQueue.
                                queue.add(stringRequest);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();


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
