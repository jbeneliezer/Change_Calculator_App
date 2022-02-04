package com.example.hw2;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clearPrice();
    }

    static final int MAX_PRICE = 100000000; // $1 million
    int price; // price in cents
    HashMap<String, Integer> change = new HashMap<String, Integer>()
    {{
        put("twenties", 0);
        put("tens", 0);
        put("fives", 0);
        put("ones", 0);
        put("quarters", 0);
        put("dimes", 0);
        put("nickels", 0);
        put("pennies", 0);
    }};

    public void setPrice(View view) {
        Button b = findViewById(view.getId());
        price = ((price * 10) + (Integer.parseInt(String.valueOf(b.getText())))) % MAX_PRICE;
        setChange();
        displayPrice();
        displayChange();
    }

    public void clearPrice(View view) {
        clearPrice();
    }

    protected void clearPrice() {
        price = 0;
        setChange();
        displayPrice();
        displayChange();
    }

    protected void setChange() {
        int value = price;
        change.put("twenties", value/ 2000);
        value %= 2000;
        change.put("tens", value/ 1000);
        value %= 1000;
        change.put("fives", value/ 500);
        value %= 500;
        change.put("ones", value/ 100);
        value %= 100;
        change.put("quarters", value/ 25);
        value %= 25;
        change.put("dimes", value/ 10);
        value %= 10;
        change.put("nickels", value/ 5);
        value %= 5;
        change.put("pennies", value);
    }

    @SuppressLint("DefaultLocale")
    protected void displayPrice() {
        TextView tv = findViewById(R.id.price);
        double realValue = price/ 100.0;
        tv.setText(String.format("Price: %.02f", realValue));
    }

    @SuppressLint("DefaultLocale")
    protected void displayChange() {
        for (HashMap.Entry<String, Integer> element: change.entrySet()) {
            int id = getResources().getIdentifier(element.getKey(), "id", getPackageName());
            TextView keyView = findViewById(id);
            String unit = String.valueOf(keyView.getText()).replaceFirst("\\s*(\\w+).*", "$1");
            keyView.setText(String.format("%s %d", unit, element.getValue()));
        }
    }

}