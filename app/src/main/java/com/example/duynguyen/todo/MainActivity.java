package com.example.duynguyen.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button button, button2;
    private Intent intent,intent2;
    private DatabaseReference mFirebaseDatabase;
    private ArrayList<doi_tuong> doituongs = new ArrayList<doi_tuong>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.click);
        button2 = (Button)findViewById(R.id.register);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2 = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent2);
            }
        });

    }
    private void writeFoods(String userId,String id, boolean name) {
        doi_tuong user = new doi_tuong(id,name);
        mFirebaseDatabase.child(userId).setValue(user);
    }
    public void frebase(){
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("list");
//        writeFoods("123","nguyen duy thuong", true);
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                recyclerView.setVisibility(View.VISIBLE);
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    doituongs.add(dsp.getValue(doi_tuong.class));
                }
                System.out.println(doituongs.get(0).getTitle());
                setRecyclerView();
            }
            @Override
            public void onCancelled(DatabaseError error) {
//                Log.w(TAG, "Failed to read value.", error.toException());
            }

        });
    }
}
