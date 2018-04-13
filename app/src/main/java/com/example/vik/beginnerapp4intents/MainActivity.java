package com.example.vik.beginnerapp4intents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int price = 0;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        price = (5 * count);
        displayPrice(price);
    }

    public void displayPrice(int price) {
        TextView amountTextView = (TextView) findViewById(R.id.amount_text_view);
        // "\u20ac" symbol for euro
        amountTextView.setText("\u20ac" + price);
    }

    public void decrementCount(View view){
        count--;
        displayCount(count);
    }

    public void incrementCount(View view){
        count++;
        displayCount(count);
    }


    public void cancelOrder(View view) {
        price = 0;
        count = 0;
        displayCount(count);
        displayPrice(price);
    }

    public void displayCount(int count) {
        TextView countTextView = (TextView) findViewById(R.id.count_text_view);
        // "\u20ac" symbol for euro
        countTextView.setText("" + count);
    }
}
