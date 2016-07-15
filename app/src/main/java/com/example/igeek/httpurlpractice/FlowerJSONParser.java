package com.example.igeek.httpurlpractice;

import com.example.igeek.httpurlpractice.Flower.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by igeek on 7/15/16.
 */

//This is the custom class that will Parse our JSON. Remember that, when we parse JSON, we will
    //utilize a set of classes known as JsonArray and JsonObject.
public class FlowerJSONParser {

    public static List<model> parseFeed(String content){

        try {

            //First we store the data from our parameter String in a JSONArray object
            JSONArray ar = new JSONArray(content);
            //Then we declare a new list of models/flowers
            List<model> flowerList = new ArrayList<>();

            //Create a for loop the iterates through the array and grabs an instance of each object
            for(int i =0 ; i< ar.length(); i++){

                //store each object in a JSON object called obj
                JSONObject obj =ar.getJSONObject(i);
                //Create in instance of our model/flower class so that we can store each property in its designated instance
                model flower = new model();

                //Remember that in our JSON data each flower is stored as an individual object, so by using the for loop to iterate through our array and then through our objects
                //We can grab the key value pairs for each particular flower and store them in our Pojo instances one flower at a time.
                flower.setProductId(obj.getInt("productId"));
                flower.setName(obj.getString("name"));
                flower.setCategory(obj.getString("category"));
                flower.setInstructions(obj.getString("instructions"));
                flower.setPhoto(obj.getString("photo"));
                flower.setPrice(obj.getDouble("price"));

                //Here we add our current flower to the list of flowers!
                flowerList.add(flower);
            }
            //Of course we need to return our list so that we can do something with the data!
            return flowerList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
