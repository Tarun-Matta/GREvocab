package com.example.tarunmatta619.grevocab;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class freq101 extends AppCompatActivity {

    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;
    private View mCardFrontLayout;
    private View mCardBackLayout;
    public int fcount = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freq101);
        ImageView imageView=(ImageView) findViewById(R.id.previous);

        String photo = "https://firebasestorage.googleapis.com/v0/b/grevocab-5ba58.appspot.com/o/left-297787_1280.png?alt=media&token=372e7a60-98d9-42df-a516-215e29ca335a";

        Glide.with(getApplicationContext()).load(photo).into(imageView);

        ImageView imageView2=(ImageView) findViewById(R.id.next);

        String photo2 = "https://firebasestorage.googleapis.com/v0/b/grevocab-5ba58.appspot.com/o/right-297788_1280.png?alt=media&token=09d7f55b-68ed-44d9-89e0-cb9a46805e7b";

        Glide.with(getApplicationContext()).load(photo2).into(imageView2);



        findViews();
        loadAnimations();
        changeCameraDistance();

        final TextView word=(TextView) findViewById(R.id.fronttext);
        final TextView meaning = (TextView) findViewById(R.id.cardback);
        ImageButton imageButton1=(ImageButton) findViewById(R.id.next);
        ImageButton imageButton2=(ImageButton) findViewById(R.id.previous);

        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();

        if(fcount==0){
            word.setText("Word");
            meaning.setText("Meaning");
            Toast.makeText(freq101.this, "click next", Toast.LENGTH_SHORT).show();
        }

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fcount < 102){

                    fcount++;

                    firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/HighFrequency101/word/" +fcount).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String fword = dataSnapshot.getValue(String.class);
                            word.setText(fword);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/HighFrequency101/Meaning/" +fcount).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String fmeaning = dataSnapshot.getValue(String.class);
                            meaning.setText(fmeaning);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                else if(fcount==102){
                    word.setText("Word");
                    meaning.setText("Meaning");
                    Toast.makeText(freq101.this, "end of 101", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fcount > 1){

                    fcount--;
                    firebaseDatabase.getReference("HighFrequency101/word/" +fcount).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String fword = dataSnapshot.getValue(String.class);
                            word.setText(fword);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    firebaseDatabase.getReference("HighFrequency101/Meaning/" +fcount).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String fmeaning = dataSnapshot.getValue(String.class);
                            meaning.setText(fmeaning);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                else if(fcount==1){
                    word.setText("Word");
                    meaning.setText("Meaning");
                    Toast.makeText(freq101.this, "click next", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mCardFrontLayout.setCameraDistance(scale);
        mCardBackLayout.setCameraDistance(scale);
    }

    @SuppressLint("ResourceType")
    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.anim.out_anim);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.anim.in_anim);
    }

    private void findViews() {
        mCardBackLayout = findViewById(R.id.card_back);
        mCardFrontLayout = findViewById(R.id.card_front);
    }

    public void flipCard(View view) {
        if (!mIsBackVisible) {
            mSetRightOut.setTarget(mCardFrontLayout);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
        } else {
            mSetRightOut.setTarget(mCardBackLayout);
            mSetLeftIn.setTarget(mCardFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
        }
    }
}
