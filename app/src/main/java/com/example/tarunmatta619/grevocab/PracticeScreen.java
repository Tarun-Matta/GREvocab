package com.example.tarunmatta619.grevocab;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PracticeScreen extends AppCompatActivity {

    public int pcount,resultnum=1;
    GridLayout mainGrid;
    public String resultstr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_screen);

        ImageView imageView2=(ImageView) findViewById(R.id.next);

        String photo2 = "https://firebasestorage.googleapis.com/v0/b/grevocab-5ba58.appspot.com/o/right-297788_1280.png?alt=media&token=09d7f55b-68ed-44d9-89e0-cb9a46805e7b";

        Glide.with(getApplicationContext()).load(photo2).into(imageView2);

        final TextView word=(TextView) findViewById(R.id.word);
        final TextView option1=(TextView) findViewById(R.id.opt1);
        final TextView option2=(TextView) findViewById(R.id.opt2);
        final TextView option3=(TextView) findViewById(R.id.opt3);
        final TextView option4=(TextView) findViewById(R.id.opt4);
        final TextView result=(TextView) findViewById(R.id.result);
        mainGrid = (GridLayout) findViewById(R.id.maingrid);

        final Integer s1 = getIntent().getIntExtra("setvalue",0);
        //final int setnum = s1;
        pcount=(s1*15);
        final int max=(s1+1)*15;




        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();





            firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/practice/set1/"+ (pcount+1)+ "/0").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String word1=dataSnapshot.getValue(String.class);
                    word.setText(word1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/practice/set1/"+ (pcount+1)+ "/1").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String word1=dataSnapshot.getValue(String.class);
                    option1.setText(word1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/practice/set1/"+ (pcount+1)+ "/2").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String word1=dataSnapshot.getValue(String.class);
                    option2.setText(word1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/practice/set1/"+ (pcount+1)+ "/3").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String word1=dataSnapshot.getValue(String.class);
                    option3.setText(word1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/practice/set1/"+ (pcount+1)+ "/4").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String word1=dataSnapshot.getValue(String.class);
                    option4.setText(word1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/practice/set1/"+ (pcount+1)+ "/5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer word1=dataSnapshot.getValue(Integer.class);
                resultnum= word1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //pcount=0;
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pcount++;
                setSomeEvent(mainGrid);
                if (pcount < max) {
                    firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/practice/set1/" + (pcount + 1) + "/0").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String word1 = dataSnapshot.getValue(String.class);
                            word.setText(word1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/practice/set1/" + (pcount + 1) + "/1").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String word1 = dataSnapshot.getValue(String.class);
                            option1.setText(word1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/practice/set1/" + (pcount + 1) + "/2").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String word1 = dataSnapshot.getValue(String.class);
                            option2.setText(word1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/practice/set1/" + (pcount + 1) + "/3").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String word1 = dataSnapshot.getValue(String.class);
                            option3.setText(word1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/practice/set1/" + (pcount + 1) + "/4").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String word1 = dataSnapshot.getValue(String.class);
                            option4.setText(word1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

                firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/practice/set1/" + (pcount + 1) + "/5").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Integer word1 = dataSnapshot.getValue(Integer.class);
                        resultnum = word1;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        //result.setText("Correct Answer");
        setToggleEvent(mainGrid);
    }

    private void setSomeEvent(GridLayout mainGrid) {
        for(int i=0;i<mainGrid.getChildCount();i++){

            final CardView cv=(CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cv.setCardBackgroundColor(-1);
        }

    }

    private void setToggleEvent(final GridLayout mainGrid) {

        for(int i=0;i<mainGrid.getChildCount();i++){

            final CardView cv=(CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(finalI == 0){}
                   else if(cv.getCardBackgroundColor().getDefaultColor()==-1 && finalI ==(resultnum)){
                        cv.setCardBackgroundColor(Color.parseColor("#006400"));

                    }
                    else if(cv.getCardBackgroundColor().getDefaultColor()==-1) {
                        cv.setCardBackgroundColor(Color.parseColor("#8B0000"));
                        Toast.makeText(PracticeScreen.this, "Try again", Toast.LENGTH_SHORT).show();
                    }
                    else {

                    }

                    //Toast.makeText(PracticeScreen.this, "yooo!!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
