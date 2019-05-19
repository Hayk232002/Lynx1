package com.example.lynx1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration3 extends AppCompatActivity {

    String first;
    String last;

    Button btn_next_reg3;
    EditText et_day_reg3;
    EditText et_month_reg3;
    EditText et_year_reg3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration3);

        Log.wtf("oncreate","onCreate");

        Intent intent = getIntent();
        first=intent.getStringExtra("first");
        last=intent.getStringExtra("last");

        btn_next_reg3 = (Button) findViewById(R.id.btn_next_reg3);
//        et_day_reg3 = (EditText) findViewById(R.id.et_day_reg3);
        et_month_reg3 = (EditText) findViewById(R.id.et_month_reg3);
        et_year_reg3 = (EditText) findViewById(R.id.et_year_reg3);

        btn_next_reg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration3.this,Registration4.class);
//                intent.putExtra("first",first);
//                intent.putExtra("last",last);
//                intent.putExtra("day",et_day_reg3.getText().toString());
//                intent.putExtra("month",et_month_reg3.getText().toString());
//                intent.putExtra("year",et_year_reg3.getText().toString());
//                intent.putExtra("sex","male");
                startActivity(intent);
            }
        });
    }
}
