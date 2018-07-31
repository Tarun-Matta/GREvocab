package com.example.tarunmatta619.grevocab;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
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

public class barron333 extends AppCompatActivity {

    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private AnimatorSet mSetDirect;
    private AnimatorSet mPutDirect;
    private boolean mIsBackVisible = false;
    private View mCardFrontLayout;
    private View mCardBackLayout;
    public String url;
    public int min,max;
    TextView sendword,word;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barron333);

        ImageView imageView = (ImageView) findViewById(R.id.previous);

        String photo = "https://firebasestorage.googleapis.com/v0/b/grevocab-5ba58.appspot.com/o/left-297787_1280.png?alt=media&token=372e7a60-98d9-42df-a516-215e29ca335a";

        Glide.with(getApplicationContext()).load(photo).into(imageView);

        ImageView imageView2 = (ImageView) findViewById(R.id.next);

        String photo2 = "https://firebasestorage.googleapis.com/v0/b/grevocab-5ba58.appspot.com/o/right-297788_1280.png?alt=media&token=09d7f55b-68ed-44d9-89e0-cb9a46805e7b";

        Glide.with(getApplicationContext()).load(photo2).into(imageView2);

        findViews();
        loadAnimations();
        changeCameraDistance();

        word = (TextView) findViewById(R.id.fronttext);
        final TextView meaning = (TextView) findViewById(R.id.cardback);
        final ImageButton imageButton1 = (ImageButton) findViewById(R.id.next);
        final ImageButton imageButton2 = (ImageButton) findViewById(R.id.previous);
        final ImageButton diction = (ImageButton) findViewById(R.id.dict);

        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();
        Integer some = getIntent().getIntExtra("bcounter", 0);
        final Integer[] bcount = {some};

        if (bcount[0] == 0) {
            min = 0;
            max = 35;
        } else if (bcount[0] == 1) {
            bcount[0] = 35;
            min = 35;
            max = 44;
        } else if(bcount[0] == 2){
            bcount[0] = 44;
            min = 44;
            max = 67;
        } else if (bcount[0] == 3) {
            bcount[0] = 67;
            min = 67;
            max = 108;
        } else if(bcount[0] == 4){
            bcount[0] = 108;
            min = 108;
            max = 132;
        } else if (bcount[0] == 5) {
            bcount[0] = 132;
            min = 132;
            max = 146;
        } else if(bcount[0] == 6){
            bcount[0] = 146;
            min = 146;
            max = 154;
        } else if (bcount[0] == 7) {
            bcount[0] = 154;
            min = 154;
            max = 157;
        } else if(bcount[0] == 8){
            bcount[0] = 157;
            min = 157;
            max = 193;
        } else if (bcount[0] == 9) {
            bcount[0] = 193;
            min = 193;
            max = 204;
        } else if(bcount[0] == 10){
            bcount[0] = 204;
            min = 204;
            max = 216;
        } else if (bcount[0] == 11) {
            bcount[0] = 216;
            min = 216;
            max = 218;
        } else if(bcount[0] == 12){
            bcount[0] = 218;
            min = 218;
            max = 227;
        } else if (bcount[0] == 13) {
            bcount[0] = 227;
            min = 227;
            max = 266;
        } else if(bcount[0] == 14){
            bcount[0] = 266;
            min = 266;
            max = 269;
        } else if (bcount[0] == 15) {
            bcount[0] = 269;
            min = 269;
            max = 285;
        } else if(bcount[0] == 16){
            bcount[0] = 285;
            min = 285;
            max = 311;
        } else if (bcount[0] == 17) {
            bcount[0] = 311;
            min = 311;
            max = 320;
        } else if(bcount[0] == 18){
            bcount[0] = 320;
            min = 320;
            max = 328;
        } else  {
            bcount[0] = 328;
            min = 328;
            max = 333;
        }


        if (bcount[0] == min) {
            word.setText("Word");
            meaning.setText("Meaning");
            diction.setVisibility(View.GONE);
            imageButton2.setVisibility((View.GONE));
            Toast.makeText(barron333.this, "click next", Toast.LENGTH_SHORT).show();
        }


        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bcount[0] < max) {
                    diction.setVisibility(View.VISIBLE);
                    imageButton2.setVisibility((View.VISIBLE));

                    bcount[0]++;
                    if (mIsBackVisible) {
                        flipCardDirect(mCardFrontLayout);
                    }

                    firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/Barrons333/word/" + bcount[0]).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String bword = dataSnapshot.getValue(String.class);
                            word.setText(bword);
                            url = bword;
                            diction.setOnClickListener(new View.OnClickListener() {
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

                    firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/Barrons333/meaning/" + bcount[0]).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String bmeaning = dataSnapshot.getValue(String.class);
                            meaning.setText(bmeaning);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else if (bcount[0] == max) {
                    word.setText("Word");
                    meaning.setText("Meaning");
                    imageButton1.setVisibility(View.GONE);
                    Toast.makeText(barron333.this, "End", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bcount[0] > min) {
                    imageButton1.setVisibility(View.VISIBLE);

                    bcount[0]--;
                    if (mIsBackVisible) {
                        flipCardDirect(mCardFrontLayout);
                    }

                    firebaseDatabase.getReference("Barrons333/word/" + bcount[0]).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String bword = dataSnapshot.getValue(String.class);
                            word.setText(bword);
                            url = bword;
                            diction.setOnClickListener(new View.OnClickListener() {
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

                    firebaseDatabase.getReference("Barrons333/meaning/" + bcount[0]).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String bmeaning = dataSnapshot.getValue(String.class);
                            meaning.setText(bmeaning);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else if (bcount[0] == min) {
                    word.setText("Word");
                    meaning.setText("Meaning");
                    imageButton2.setVisibility(View.GONE);
                    Toast.makeText(barron333.this, "start!", Toast.LENGTH_SHORT).show();
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
