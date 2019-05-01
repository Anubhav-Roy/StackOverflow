package com.roy.anubhav.stackoverflow.QuestionList.hot;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roy.anubhav.stackoverflow.QuestionList.QuestionListViewModel;
import com.roy.anubhav.stackoverflow.R;
import com.roy.anubhav.stackoverflow.adapter.QuestionsAdapter;
import com.roy.anubhav.stackoverflow.retrofit.Question;

import java.util.List;

public class HotTab extends Fragment {

    private QuestionListViewModel mViewModel;

    private RecyclerView recyclerView ;

    private String tag;

    public static HotTab newInstance() {
        return new HotTab();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_tab_fragment, container, false);

        //Initalize the recyclerview to hold the questions
        recyclerView = view.findViewById(R.id.questionResultsHot);

        return view;
    }

    //Setup the RecyclerView with the questions
    private void setupRecyclerview(List<Question> questions){

        //Set the adapter with the questions received
        QuestionsAdapter questionsAdapter = new QuestionsAdapter(questions,getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(questionsAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(QuestionListViewModel.class);

        if (getArguments() != null) {
            //Get the tag
            tag = getArguments().getString("tag");

            //Load the appropriate Questions
            mViewModel.getQuestions(tag,"hot").observe(this, new Observer<List<Question>>() {
                @Override
                public void onChanged(List<Question> questions) {
                    setupRecyclerview(questions);
                }
            });

        }
    }

}
