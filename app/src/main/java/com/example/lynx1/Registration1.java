package com.example.lynx1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Registration1 extends AppCompatActivity {

    Button btn_next_reg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration1);

        btn_next_reg1 = (Button) findViewById(R.id.btn_next_reg1);

        btn_next_reg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration1.this, Registration2.class);
                startActivity(intent);
            }
        });
    }
}
