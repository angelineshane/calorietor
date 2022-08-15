package com.example.calorietor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class result extends AppCompatActivity {

    Intent i;
    double bmr, calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView result_bmr=(TextView) findViewById(R.id.result_bmr);
        TextView result_calories=(TextView) findViewById(R.id.result_calories);
        Button back=(Button) findViewById(R.id.button2);
        i=getIntent();

        bmr=i.getDoubleExtra("bmr",0);
        bmr=Double.parseDouble(new DecimalFormat("##.##").format(bmr));
        result_bmr.setText(bmr+" kcal/day");

        calories=i.getDoubleExtra("calories",0);
        calories=Double.parseDouble(new DecimalFormat("##.##").format(calories));
        result_calories.setText(calories+" kcal/day");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent((result.this), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }
}