package com.example.tarunmatta619.grevocab;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
    private AnimatorSet mSetDirect;
    private AnimatorSet mPutDirect;
    private boolean mIsBackVisible = false;
    private View mCardFrontLayout;
    private View mCardBackLayout;
    public String url;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freq101);

        //String s= getIntent().getStringExtra("count");
        final int[] fcount = {0};

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
        final ImageButton imageButton1=(ImageButton) findViewById(R.id.next);
        final ImageButton imageButton2=(ImageButton) findViewById(R.id.previous);
        final ImageButton fdiction=(ImageButton) findViewById(R.id.fdict);

        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();



        if(fcount[0] ==0){
            word.setText("Word");
            meaning.setText("Meaning");
            fdiction.setVisibility(View.GONE);
            imageButton2.setVisibility((View.GONE));
            Toast.makeText(freq101.this, "click next", Toast.LENGTH_SHORT).show();
        }

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fcount[0] < 102){
                    fdiction.setVisibility(View.VISIBLE);
                    imageButton2.setVisibility((View.VISIBLE));

                    fcount[0]++;
                    if (mIsBackVisible) {
                        flipCardDirect(mCardFrontLayout);
                    }
                    firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/HighFrequency101/word/" + fcount[0]).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String fword = dataSnapshot.getValue(String.class);
                            word.setText(fword);
                            url = fword;
                            fdiction.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Uri uri = Uri.parse("https://www.dictionary.com/browse/" + url); // missing 'http://' will cause crashed
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(intent);
                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/HighFrequency101/Meaning/" + fcount[0]).addValueEventListener(new ValueEventListener() {
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
                else if(fcount[0] ==102){
                    word.setText("Word");
                    meaning.setText("Meaning");
                    imageButton1.setVisibility(View.GONE);
                    Toast.makeText(freq101.this, "end of 101", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fcount[0] > 1){

                    fcount[0]--;
                    imageButton1.setVisibility(View.VISIBLE);
                    if (mIsBackVisible) {
                        flipCardDirect(mCardFrontLayout);
                    }
                    firebaseDatabase.getReference("HighFrequency101/word/" + fcount[0]).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String fword = dataSnapshot.getValue(String.class);
                            word.setText(fword);
                            url = fword;
                            fdiction.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Uri uri = Uri.parse("https://www.dictionary.com/browse/" + url); // missing 'http://' will cause crashed
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(intent);
                                }
                            });


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    firebaseDatabase.getReference("HighFrequency101/Meaning/" + fcount[0]).addValueEventListener(new ValueEventListener() {
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
                else if(fcount[0] ==1){
                    word.setText("Word");
                    meaning.setText("Meaning");
                    imageButton2.setVisibility(View.GONE);
                    Toast.makeText(freq101.this, "click next", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Intent returnIntent = new Intent(this,studySetList.class);
        //returnIntent.putExtra("result", fcount[0]);

        //finish();



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
        mSetDirect = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.anim.direct_word);
        mPutDirect =(AnimatorSet) AnimatorInflater.loadAnimator(this,R.anim.word_direct);
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
    public void flipCardDirect(View view){
        mPutDirect.setTarget(mCardBackLayout);
        mSetDirect.setTarget(mCardFrontLayout);
        mPutDirect.start();
        mSetDirect.start();
        mIsBackVisible = false;
    }

}
