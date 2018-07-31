package com.example.tarunmatta619.grevocab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv=(ImageView) findViewById(R.id.studyset);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this,studySetList.class);
                startActivity(i1);
            }
        });

        ImageView iv2=(ImageView) findViewById(R.id.practice);
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pl= new Intent(MainActivity.this,practiceList.class);
                startActivity(pl);
            }
        });



    }
}
