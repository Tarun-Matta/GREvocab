package com.example.tarunmatta619.grevocab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

public class bLetterList extends AppCompatActivity {

    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_letter_list);
        gridLayout=(GridLayout) findViewById(R.id.letterGrid);

        for(int i=0;i<gridLayout.getChildCount();i++){

            final CardView cv=(CardView)gridLayout.getChildAt(i);
            final int finalI = i;

            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i2=new Intent(bLetterList.this,barron333.class);
                    i2.putExtra("bcounter",finalI);
                    startActivity(i2);
                    //Toast.makeText(PracticeScreen.this, "yooo!!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
