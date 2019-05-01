package com.roy.anubhav.stackoverflow.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roy.anubhav.stackoverflow.QuestionList.ViewQuestion.ViewQuestion;
import com.roy.anubhav.stackoverflow.R;
import com.roy.anubhav.stackoverflow.retrofit.Question;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

    List<Question> questions;
    Context mContext;

    //Receives the list of questions to be loaded
    public QuestionsAdapter(List<Question> questions, Context context){
        this.questions = questions;
        this.mContext = context;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        final Question question = questions.get(position);

        //Sets the text to the question's title
        holder.textView.setText(question.getTitle());

        //Listens for click on the question , i.e , the user wishes to view this question
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Call ViewQuestion Activity and pass the question's link and title in the intent
                Intent intent = new Intent(mContext, ViewQuestion.class);
                intent.putExtra("link",question.getLink());
                intent.putExtra("title",question.getTitle());
                mContext.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.question_layout, parent, false);
        QuestionViewHolder viewHolder = new QuestionViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        LinearLayout parentLayout;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            //Initialize the views
            textView = itemView.findViewById(R.id.question);
            parentLayout= itemView.findViewById(R.id.parentLayout);
        }
    }
}
