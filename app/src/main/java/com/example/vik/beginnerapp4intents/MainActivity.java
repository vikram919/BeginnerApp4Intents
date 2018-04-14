package com.example.vik.beginnerapp4intents;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int price = 5;
    int count = 1;
    CheckBox whippedcreamCheckBox;
    CheckBox chocolateCheckBox;
    EditText editText;
    TextView amountTextView;
    TextView countTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv = (ImageView)findViewById(R.id.coffee_imageview);
        iv.setColorFilter(Color.BLACK, PorterDuff.Mode.LIGHTEN);
        iv.setBackgroundColor(Color.rgb(100, 100, 50));
        whippedcreamCheckBox = (CheckBox) findViewById(R.id.whippedcream_checkbox);
        chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        editText = (EditText) findViewById(R.id.edit_text_input);
        amountTextView = (TextView) findViewById(R.id.amount_text_view);
        countTextView = (TextView) findViewById(R.id.count_text_view);
    }

    public void submitOrder(View view) {
        String name = editText.getText().toString();
        String orderSummary = createOrderSummary(name, price, whippedcreamCheckBox.isChecked(), chocolateCheckBox.isChecked());
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"coffeeOrder@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for: " + name);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void displayPrice(int price) {
        // "\u20ac" symbol for euro
        amountTextView.setText("\u20ac" + price);
    }

    public void decrementCount(View view) {
        if (count > 1) {
            count--;
        }
        calculatePrice();
        displayPrice(price);
        displayCount(count);
    }

    public void incrementCount(View view) {
        count++;
        calculatePrice();
        displayPrice(price);
        displayCount(count);
    }

    public void calculatePrice() {
        price = (5 * count);
        if (whippedcreamCheckBox.isChecked()) {
            price += 1 * count;
        }
        if (chocolateCheckBox.isChecked()) {
            price += 2 * count;
        }
    }


    public void cancelOrder(View view) {
        price = 5;
        count = 1;
        editText.getText().clear();
        whippedcreamCheckBox.setChecked(false);
        chocolateCheckBox.setChecked(false);
        displayCount(count);
        displayPrice(price);
    }

    public void displayCount(int count) {
        // "\u20ac" symbol for euro
        countTextView.setText("" + count);
    }

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + name;
        if (addWhippedCream) {
            priceMessage += "\nWith whipped cream ";
        }
        if (addChocolate) {
            priceMessage += "\nWith chocolate ";
        }
        priceMessage += "\nQuantity: " + count;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }
}
