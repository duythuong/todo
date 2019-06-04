package com.example.duynguyen.todo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Timer;

public class recy extends RecyclerView.Adapter<recy.ViewHolder>{
    private ArrayList<doi_tuong> mContacts;
    private Context mContext;
    private doi_tuong doituong;
    Intent intent;
    private DatabaseReference mFirebaseDatabase;

    public recy(ArrayList<doi_tuong> mContacts, Context mContext) {
        this.mContacts = mContacts;
        this.mContext = mContext;
        System.out.println(mContacts.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recy, viewGroup, false);
        return new recy.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(recy.ViewHolder holder, final int i) {
        doituong = mContacts.get(i);
        holder.text1.setText(doituong.getTitle());
        holder.toggleButton.setChecked(doituong.getHeart());
        holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("list");
                writeFoods("123",doituong.getTitle(), isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text1;
        ToggleButton toggleButton;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            text1 = (TextView) itemView.findViewById(R.id.text);
            toggleButton = (ToggleButton)itemView.findViewById(R.id.button_favorite);
        }
    }
    private void writeFoods(String userId,String id, boolean name) {
        doi_tuong user = new doi_tuong(id,name);
        mFirebaseDatabase.child(userId).setValue(user);
    }
}
