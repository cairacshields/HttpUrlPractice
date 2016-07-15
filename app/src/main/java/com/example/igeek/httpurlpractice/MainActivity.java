package com.example.igeek.httpurlpractice;

import android.os.AsyncTask;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.igeek.httpurlpractice.Flower.model;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView texty;
    List<model> flowerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texty = (TextView)findViewById(R.id.texty);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.data){
            requestData("http://services.hanselandpetal.com/feeds/flowers.json");
        }
        return super.onOptionsItemSelected(item);
    }

    public void requestData(String s){

        fetchData task = new fetchData();
        task.execute(s);
    }

    public void updateDisplay(){

        //First we check to make sure our list of flowers is not null
        if(flowerList != null){
            //then we create a for each loop that iterates through each instance of the flower list
            for(model flower: flowerList){
                //after each iteration, we grab the name of the flower and append it to our textView
                texty.append(flower.getName() + "\n");
            }
        }

    }

    public class fetchData extends AsyncTask<String, String, String>{


        @Override
        protected String doInBackground(String... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            //We set our flowerList equal to the list of data we receive after calling our custom parseFeed method
            flowerList = FlowerJSONParser.parseFeed(s);
            updateDisplay();
            super.onPostExecute(s);
        }
    }
}
