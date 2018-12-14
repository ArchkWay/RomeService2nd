package com.example.archek.romeservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private static Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Инициируем активити и кнопку/instantiate activity and button */
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btnStart);
        /*запускаем сервис/start service*/
        onClickStart();

    }
    public void onClickStart() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(), MyService.class));

            }
        });
    }
    public static void changeName(String num){
        btnStart.setText(num);
    }

}