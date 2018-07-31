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

public class manH500 extends AppCompatActivity {

    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private AnimatorSet mSetDirect;
    private AnimatorSet mPutDirect;
    private boolean mIsBackVisible = false;
    private View mCardFrontLayout;
    private View mCardBackLayout;
    public int min,max;
    public String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_h500);

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
        final ImageButton mdiction=(ImageButton) findViewById(R.id.mdict);

        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();

        Integer somenum = getIntent().getIntExtra("mcounter", 0);
        final Integer[] mcount = {somenum};

        if (mcount[0] == 0) {
            min = 0;
            max = 46;
        } else if (mcount[0] == 1) {
            mcount[0] = 46;
            min = 46;
            max = 64;
        } else if(mcount[0] == 2){
            mcount[0] = 64;
            min = 64;
            max = 89;
        } else if (mcount[0] == 3) {
            mcount[0] = 89;
            min = 89;
            max = 122;
        } else if(mcount[0] == 4){
            mcount[0] = 122;
            min = 122;
            max = 146;
        } else if (mcount[0] == 5) {
            mcount[0] = 146;
            min = 146;
            max = 168;
        } else if(mcount[0] == 6){
            mcount[0] = 168;
            min = 168;
            max = 182;
        } else if (mcount[0] == 7) {
            mcount[0] = 182;
            min = 182;
            max = 197;
        } else if(mcount[0] == 8){
            mcount[0] = 197;
            min = 197;
            max = 250;
        } else if (mcount[0] == 9) {
            mcount[0] = 250;
            min = 250;
            max = 253;
        } else if(mcount[0] == 10){
            mcount[0] = 253;
            min = 253;
            max = 256;
        } else if (mcount[0] == 11) {
            mcount[0] = 256;
            min = 256;
            max = 275;
        } else if(mcount[0] == 12){
            mcount[0] = 275;
            min = 275;
            max = 304;
        } else if (mcount[0] == 13) {
            mcount[0] = 304;
            min = 304;
            max = 312;
        } else if(mcount[0] == 14){
            mcount[0] = 312;
            min = 312;
            max = 322;
        } else if (mcount[0] == 15) {
            mcount[0] = 322;
            min = 322;
            max = 383;
        } else if(mcount[0] == 16){
            mcount[0] = 383;
            min = 383;
            max = 386;
        } else if (mcount[0] == 17) {
            mcount[0] = 386;
            min = 386;
            max = 412;
        } else if(mcount[0] == 18){
            mcount[0] = 412;
            min = 412;
            max = 452;
        }  else if(mcount[0] == 19){
            mcount[0] = 452;
            min = 452;
            max = 469;
        } else if (mcount[0] == 20) {
            mcount[0] = 469;
            min = 469;
            max = 475;
        } else if(mcount[0] == 21){
            mcount[0] = 475;
            min = 475;
            max = 490;
        } else if (mcount[0] == 22) {
            mcount[0] = 490;
            min = 490;
            max = 498;
        }  else  {
            mcount[0] = 498;
            min = 498;
            max = 500;
        }



        if(mcount[0] == min){
            word.setText("Word");
            meaning.setText("Meaning");
            mdiction.setVisibility(View.GONE);
            imageButton2.setVisibility((View.GONE));
            Toast.makeText(manH500.this, "click next", Toast.LENGTH_SHORT).show();
        }

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mcount[0] < max){
                    mdiction.setVisibility(View.VISIBLE);
                    imageButton2.setVisibility((View.VISIBLE));

                    mcount[0]++;
                    if (mIsBackVisible) {
                        flipCardDirect(mCardFrontLayout);
                    }
                    firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/Manhattan500/word/" + mcount[0]).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String mword = dataSnapshot.getValue(String.class);
                            word.setText(mword);
                            url = mword;
                            mdiction.setOnClickListener(new View.OnClickListener() {
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

                    firebaseDatabase.getReferenceFromUrl("https://grevocab-5ba58.firebaseio.com/Manhattan500/meaning/" +mcount[0]).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String mmeaning = dataSnapshot.getValue(String.class);
                            meaning.setText(mmeaning);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                else if(mcount[0]==max){
                    word.setText("Word");
                    meaning.setText("Meaning");
                    imageButton1.setVisibility(View.GONE);
                    Toast.makeText(manH500.this, "end of 101", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mcount[0] > 1){
                    imageButton1.setVisibility(View.VISIBLE);

                    mcount[0]--;
                    if (mIsBackVisible) {
                        flipCardDirect(mCardFrontLayout);
                    }
                    firebaseDatabase.getReference("Manhattan500/word/" +mcount[0]).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String mword = dataSnapshot.getValue(String.class);
                            word.setText(mword);
                            url = mword;
                            mdiction.setOnClickListener(new View.OnClickListener() {
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

                    firebaseDatabase.getReference("Manhattan500/meaning/" +mcount[0]).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String mmeaning = dataSnapshot.getValue(String.class);
                            meaning.setText(mmeaning);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                else if(mcount[0]==min){
                    word.setText("Word");
                    meaning.setText("Meaning");
                    imageButton2.setVisibility(View.GONE);
                    Toast.makeText(manH500.this, "click next", Toast.LENGTH_SHORT).show();
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
