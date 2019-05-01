package com.roy.anubhav.stackoverflow.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roy.anubhav.stackoverflow.R;
import com.roy.anubhav.stackoverflow.UserInterest.TagResultClickInterface;
import com.roy.anubhav.stackoverflow.retrofit.Tag;

import java.util.List;

public class TagResultAdapter extends  RecyclerView.Adapter<TagResultAdapter.TagResultViewHolder> {

   List<Tag> results ;
   public TagResultClickInterface callbackInterface;

   //Receives the tags as a parameter
    public TagResultAdapter(List<Tag> results){
        this.results = results;
    }

    @NonNull
    @Override
    public TagResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.single_textview_layout, parent, false);
        TagResultViewHolder viewHolder = new TagResultViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TagResultViewHolder holder, int position) {
        final Tag tag = results.get(position);

        //Sets the text to the tag name
        holder.textView.setText(tag.getName());

        //Listens for click on the tag , i.e , the user wishes to select this tag
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Triggers the callback function in the parent Activity , and passing the tag which he selected
                callbackInterface.onClick(tag);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class TagResultViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        LinearLayout parentLayout;

        public TagResultViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text);
            parentLayout= itemView.findViewById(R.id.parentLayout);
        }
    }
}
