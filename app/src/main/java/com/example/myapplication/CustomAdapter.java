package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList vocabulary_id, vocabulary_eng, vocabulary_ch, vocabulary_none;
    Animation translate_anim;

    CustomAdapter(Activity activity, Context context, ArrayList vocabulary_id, ArrayList vocabulary_eng, ArrayList vocabulary_ch){
        this.activity = activity;
        this.context = context;
        this.vocabulary_id = vocabulary_id;
        this.vocabulary_eng = vocabulary_eng;
        this.vocabulary_ch = vocabulary_ch;
        this.vocabulary_none = vocabulary_none;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.vocabulary_id_txt.setText(String.valueOf(vocabulary_id.get(position)));
        holder.vocabulary_eng_txt.setText(String.valueOf(vocabulary_eng.get(position)));
        holder.vocabulary_ch_txt.setText(String.valueOf(vocabulary_ch.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(vocabulary_id.get(position)));
                intent.putExtra("單字", String.valueOf(vocabulary_eng.get(position)));
                intent.putExtra("翻譯", String.valueOf(vocabulary_ch.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return vocabulary_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView vocabulary_id_txt, vocabulary_eng_txt, vocabulary_ch_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            vocabulary_id_txt = itemView.findViewById(R.id.vocabulary_id_txt);
            vocabulary_eng_txt = itemView.findViewById(R.id.vocabulary_eng_txt);
            vocabulary_ch_txt = itemView.findViewById(R.id. vocabulary_ch_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }

}
