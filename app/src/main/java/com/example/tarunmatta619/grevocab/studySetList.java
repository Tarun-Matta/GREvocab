package com.example.tarunmatta619.grevocab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class studySetList extends AppCompatActivity {

        private  ImageView imageView;
        private  ImageView imageView2;
        private  ImageView imageView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_set_list);
        ImageView imageView=(ImageView) findViewById(R.id.hf101);

        String photo = "https://firebasestorage.googleapis.com/v0/b/grevocab-5ba58.appspot.com/o/hf101.png?alt=media&token=fcb3a61b-52b1-42f0-af0c-f4c573cd20ce";

        Glide.with(getApplicationContext()).load(photo).into(imageView);

        ImageView imageView2=(ImageView) findViewById(R.id.m500);

        String photo2 = "https://firebasestorage.googleapis.com/v0/b/grevocab-5ba58.appspot.com/o/m500.png?alt=media&token=a647fbd9-f71d-43b1-bcca-1c0f8baf5f75";

        Glide.with(getApplicationContext()).load(photo2).into(imageView2);

        ImageView imageView3=(ImageView) findViewById(R.id.b333);

        String photo3 = "https://firebasestorage.googleapis.com/v0/b/grevocab-5ba58.appspot.com/o/b333.png?alt=media&token=22a79a62-d3e2-4c4e-9de8-409310385291";

        Glide.with(getApplicationContext()).load(photo3).into(imageView3);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent freq= new Intent(studySetList.this,freq101.class);
                startActivity(freq);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b333= new Intent(studySetList.this,barron333.class);
                startActivity(b333);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m500= new Intent(studySetList.this,manH500.class);
                startActivity(m500);
            }
        });
    }
}
