package com.jessicathornsby.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;




public class MainActivity extends Activity {
    private Button button;
    private EditText enterSeconds;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterSeconds = (EditText) findViewById(R.id.enter_seconds);
        button = (Button) findViewById(R.id.button);
        message = (TextView) findViewById(R.id.message);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String asyncTaskRuntime = enterSeconds.getText().toString();
                runner.execute(asyncTaskRuntime);
            }
        });
    }



    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String results;



        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this,
                    "onPreExecute", Toast.LENGTH_LONG).show();

        }



        @Override
        protected String doInBackground(String... params) {
            publishProgress("Asynctask is running..."); //


            try {

                int time = Integer.parseInt(params[0])*1000;

                Thread.sleep(time);
                results = "Asynctask ran for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return results;
        }



        @Override
        protected void onProgressUpdate(String... text) {

            message.setText(text[0]);

        }




        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(MainActivity.this,
                    "onPostExecute", Toast.LENGTH_LONG).show();
            message.setText(result);
        }


    }
}
