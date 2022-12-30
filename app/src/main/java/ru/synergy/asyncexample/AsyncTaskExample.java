package ru.synergy.asyncexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class AsyncTaskExample extends AppCompatActivity {

    private TextView mInfoTextViev;
    private ProgressBar mProgressBar;
    private Button mStartButton;
    private ProgressBar mhorizontalProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_example);

        mInfoTextViev = (TextView) findViewById(R.id.text_info);
        mStartButton = (Button) findViewById(R.id.button_start);
        mhorizontalProgressBar = findViewById(R.id.progressBar);


    }


    public void onClick(View v) {
        CurierTask curierTask = new CurierTask();
        curierTask.execute();

    }


    class CurierTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            mInfoTextViev.setText("Доставщик зашел в ваш дом");
            mStartButton.setVisibility(View.INVISIBLE);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mInfoTextViev.setText("Этаж:   " + values[0]);
            mhorizontalProgressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
           try {
               int numberofFlors = 14;
               int counter = 0;
               for (int i =0; i<numberofFlors; i++){
                   getFlore(counter);
                   publishProgress(++counter);
               }
               TimeUnit.SECONDS.sleep(1);
           } catch (InterruptedException e){
               e.printStackTrace();
           }
           return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            mInfoTextViev.setText("Звонок в дверь.Заберите вашу пиццу :)  ");
            mStartButton.setVisibility(View.INVISIBLE);
            mhorizontalProgressBar.setProgress(0);
        }

        private void getFlore(int counter) throws InterruptedException {
            TimeUnit.SECONDS.sleep(1);
        }
    }


}



