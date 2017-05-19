package com.example.dhanya.justjava;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.location.Address;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.security.auth.Subject;

public class MainActivity extends AppCompatActivity {
    int quantity=2;
    int price=5;
int totalprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void submitOrder(View view)
    {
         EditText namefield=(EditText) findViewById(R.id.name);
        String name=namefield.getText().toString();
        CheckBox whippedcheckbox=(CheckBox) findViewById(R.id.whippedcream_check_box);
        boolean hasWhippedCream=whippedcheckbox.isChecked();
        CheckBox chocolate=(CheckBox) findViewById(R.id.chocolate_check_box);
       boolean haschocolate=chocolate.isChecked();

        String pricemessage=createOrderSummary(name,price,hasWhippedCream,haschocolate);
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,pricemessage);
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);

        }
       displaymessage(pricemessage);

    }
    public void displayquantity(int num)
    {
        TextView text=(TextView) findViewById(R.id.txt);
        text.setText(num+"");
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void displayPrice(int num)
    {
        TextView text2=(TextView) findViewById(R.id.price);
        text2.setText("$"+num);
    }
    public void increment(View view)
    {
        if(quantity==100)
        {
            Toast.makeText(getApplicationContext(),"you cannot have more than 100 cups of coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        displayquantity(quantity);
    }
    public void decrement(View view)
    {
        if(quantity==1)
        {
            Toast.makeText(getApplicationContext(),"you cannot have less than 1 cup of coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        displayquantity(quantity);
    }
    public void displaymessage(String message)
    {
        TextView text2=(TextView) findViewById(R.id.price);
        text2.setText(message);
    }
    public int calculatePrice(boolean hasWhipped,boolean haschoc)
    {
        int baseprice=5;
        if(hasWhipped)
        {
            baseprice=baseprice+1;
        }
        if(haschoc)
        {
            baseprice=baseprice+2;
        }
        totalprice=quantity*baseprice;
        return totalprice;
    }
    public String createOrderSummary(String name,int priceofOrder,boolean haswhippedcream,boolean haschocolate)
    {
        String msg="Name: "+name+"\nAdd whipped Cream : "+haswhippedcream+"\nAdd chocolate : "+haschocolate+"\nQuantity="+quantity
+"\nTotal=$";
        int totalprice=calculatePrice(haswhippedcream,haschocolate);
        msg=msg+totalprice+"\nThankYou!";
    return msg;}


}
