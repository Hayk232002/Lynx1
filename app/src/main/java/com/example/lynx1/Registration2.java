package com.example.lynx1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration2 extends AppCompatActivity {

    Button btn_next_reg2;
    EditText et_first_reg2;
    EditText et_last_reg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);

        btn_next_reg2 = (Button) findViewById(R.id.btn_next_reg2);
        et_first_reg2 = (EditText) findViewById(R.id.et_first_reg2);
        et_last_reg2 = (EditText) findViewById(R.id.et_last_reg2);

        btn_next_reg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration2.this,Registration3.class);
                intent.putExtra("first",et_first_reg2.getText().toString());
                intent.putExtra("last",et_last_reg2.getText().toString());
                startActivity(intent);
            }
        });

    }
}
