package com.example.tarunmatta619.grevocab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

public class mLetterList extends AppCompatActivity {
    GridLayout newGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_letter_list);

        newGrid=(GridLayout) findViewById(R.id.manGrid);

        for(int i=0;i<newGrid.getChildCount();i++){

            final CardView cv=(CardView)newGrid.getChildAt(i);
            final int finalI = i;

            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i2=new Intent(mLetterList.this,manH500.class);
                    i2.putExtra("mcounter",finalI);
                    startActivity(i2);
                    //Toast.makeText(PracticeScreen.this, "yooo!!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
