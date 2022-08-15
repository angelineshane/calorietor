package com.example.calorietor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner physical;
    String text;
    Button calculate;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //deklarasi variabel
        EditText age = (EditText) findViewById(R.id.age);
        EditText height = (EditText) findViewById(R.id.height);
        EditText weight = (EditText) findViewById(R.id.weight);
        RadioGroup sex = (RadioGroup) findViewById(R.id.radioGroup);
        String[] pilihan = {"Little/no exercise","Light exercise (1-2 times/week)", "Moderate exercise (2-3 times/week)", "Hard exercise (3-5 times/week",
        "Physical Job (6-7 times/week", "Professional Athlete"};
        //deklarasi spinner
        physical = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.activity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        physical.setAdapter(adapter);
        physical.setOnItemSelectedListener(this);

        //calculation
        calculate=(Button) findViewById(R.id.button);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent((MainActivity.this), result.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                double calories=0;
                double bmr=0;

                int radioid=sex.getCheckedRadioButtonId();
                double berat=Double.parseDouble(String.valueOf(weight.getText()));
                double tinggi=Double.parseDouble(String.valueOf(height.getText()));
                double umur=Double.parseDouble(String.valueOf(age.getText()));

                if (radioid==R.id.male){
                    bmr=(10*berat)+(6.25*tinggi)-(5*umur)+5;
                }
                else if (radioid==R.id.female){
                    bmr=(10*berat)+(6.25*tinggi)-(5*umur)-161;
                }

                if (text.equals("Little/no exercise")){
                    calories=bmr*1.2;
                }
                else if (text.equals("Light exercise (1-2 times/week)")){
                    calories=bmr*1.4;
                }
                else if (text.equals("Moderate exercise (2-3 times/week)")){
                    calories=bmr*1.6;
                }
                else if (text.equals("Hard exercise (3-5 times/week)")){
                    calories=bmr*1.75;
                }
                else if (text.equals("Physical Job (6-7 times/week)")){
                    calories=bmr*2;
                }
                else {
                    calories=bmr*2.4;
                }

                intent.putExtra("calories", calories);
                intent.putExtra("bmr", bmr);

                startActivity(intent);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        text=adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}