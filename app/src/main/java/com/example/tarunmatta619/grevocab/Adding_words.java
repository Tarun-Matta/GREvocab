package com.example.tarunmatta619.grevocab;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Adding_words extends AppCompatActivity {

    public int count=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_words);

        final EditText word=(EditText) findViewById(R.id.wordid);
        final EditText meaning=(EditText) findViewById(R.id.meaningid);
        Button add=(Button) findViewById(R.id.addid);
        final String content = word.getText().toString();
        String content2 = meaning.getText().toString();
        final TextView wordCount=(TextView) findViewById(R.id.wordcount);

        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;
                wordCount.setText("word-"+count);
                final String content = word.getText().toString();
                final String content2 = meaning.getText().toString();

                firebaseDatabase.getReference("Barrons333").child("word").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child(String.valueOf(count)).setValue(content);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                word.setText("");

                firebaseDatabase.getReference("Barrons333").child("meaning").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child(String.valueOf(count)).setValue(content2);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                meaning.setText("");
            }
        });



    }
}
