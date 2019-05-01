package com.roy.anubhav.stackoverflow.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roy.anubhav.stackoverflow.R;
import com.roy.anubhav.stackoverflow.UserInterest.UserInterest;
import com.roy.anubhav.stackoverflow.UserInterest.UserInterestTagClickInterface;
import com.roy.anubhav.stackoverflow.retrofit.Tag;

import java.util.ArrayList;
import java.util.List;

public class UserInterestTagAdapter  extends RecyclerView.Adapter<UserInterestTagAdapter.UserInterestTagViewHolder> {

    List<Tag> selectedTags ;
    public UserInterestTagClickInterface callbackInterface;

    //Receives the selected tags as a paremeter
    public UserInterestTagAdapter(List<Tag> selectedTags){
        this.selectedTags = selectedTags;
    }

    @NonNull
    @Override
    public UserInterestTagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.user_interest_tag_adapter_layout, parent, false);
        UserInterestTagViewHolder viewHolder = new UserInterestTagViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserInterestTagViewHolder holder, final int position) {
        String tagName = selectedTags.get(position).getName();

        //Sets the text to the tag name
        holder.tagName.setText(tagName);

        //Listens for click on the tag's cancel button , i.e , the user wishes to deselect this tag
        holder.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Triggers the callback function in the parent Activity , and passing the tag which he deselected
                callbackInterface.onCancel(selectedTags.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return selectedTags.size();
    }

    class UserInterestTagViewHolder extends RecyclerView.ViewHolder {

        TextView tagName;
        ImageView cancelButton;


        public UserInterestTagViewHolder(@NonNull View itemView) {
            super(itemView);

            tagName = itemView.findViewById(R.id.tagName);
            cancelButton = itemView.findViewById(R.id.cancelButton);
        }
    }
}
