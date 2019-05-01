package com.roy.anubhav.stackoverflow.UserInterest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.roy.anubhav.stackoverflow.ApplicationClass;
import com.roy.anubhav.stackoverflow.QuestionList.QuestionList;
import com.roy.anubhav.stackoverflow.R;
import com.roy.anubhav.stackoverflow.adapter.TagResultAdapter;
import com.roy.anubhav.stackoverflow.adapter.UserInterestTagAdapter;
import com.roy.anubhav.stackoverflow.retrofit.Tag;
import com.roy.anubhav.stackoverflow.utils.AppPreferences;

import java.util.ArrayList;
import java.util.List;


public class UserInterest extends AppCompatActivity implements TagResultClickInterface , UserInterestTagClickInterface {

    //ViewModel for this activity
    UserInterestViewModel viewModel;

    //RecyclerView to hold the tags received from the server
    RecyclerView tagResults ;
    //Adapter for tags
    TagResultAdapter tagResultAdapter;

    //RecyclerView to hold the selected tags the user selects
    RecyclerView selectedTagsRecyclerView;
    //Adapter for the selected tags recyclerview.
    UserInterestTagAdapter userInterestTagAdapter=null;

    //FAB for proceeding to the home screen
    FloatingActionButton doneSelectingTags;

    //Lists for the tags received from server and selected by the user
    List<Tag> selectedTags=null;
    List<Tag> results = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interest);

        //Initializing the views
        tagResults = findViewById(R.id.searchResults);
        selectedTagsRecyclerView = findViewById(R.id.selectedTags);
        doneSelectingTags = findViewById(R.id.doneSelectingTags);

        //Hiding the FAB at the start , since the user hasn't selected 4 tags
        doneSelectingTags.hide();

        //Initializing the viewModel
        viewModel = ViewModelProviders.of(this).get(UserInterestViewModel.class);

        //Calling the viewModels.getTags() method to retrieve the popular tags from the server.
        //Since it returns a mutable object , adding an observer to listen for changes ,i.e ,
        //when the method has got the tags successfully
        viewModel.getTags().observe(this, new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
                if(tags!=null)
                    if(tags.size()>0) {
                        //Setting the global list for tags and setting up the recyclerview
                        results = tags;
                        populateResults();
                    }
            }
        });
    }

    /**
     * Populates the Recyclerview for tags got from the server .
     * Is called upon receiving values from the server
     */
    private void populateResults() {

        //Adding a divider between the results
        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_layout));

        //Setting up the adapter with a callback , for when the user wishes to select a tag
        tagResultAdapter = new TagResultAdapter(results);
        tagResultAdapter.callbackInterface = this;

        //Setting up the recyclerview
        tagResults.addItemDecoration(itemDecorator);
        tagResults.setHasFixedSize(true);
        tagResults.setLayoutManager(new LinearLayoutManager(this));
        tagResults.setAdapter(tagResultAdapter);

    }

    /**
     * Callback method from TagResultClickInterface , listens for clicks on the server tag list
     * @param tag the tag that has been clicked on and the user wishes to select
     */
    @Override
    public void onClick(Tag tag) {
        if(userInterestTagAdapter==null){   //Check if the adapter hasn't been initialized ( happens on first select)
            selectedTags =new ArrayList<>();
            selectedTags.add(tag);

            //Setting up the adapter with a callback , for when the user wishes to deselect a tag
            userInterestTagAdapter = new UserInterestTagAdapter(selectedTags);
            userInterestTagAdapter.callbackInterface = this;

            //Setting up the recyclerview
            selectedTagsRecyclerView.setHasFixedSize(true);
            selectedTagsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            selectedTagsRecyclerView.setAdapter(userInterestTagAdapter);


        }else{                          //The Adapter has already been initalized

            if(selectedTags.size()<4) {     //Check if the user has already selected 4 tags

                //Add the selected tag to the global list and notify the adapter to redraw the recyclerview
                selectedTags.add(tag);
                userInterestTagAdapter.notifyDataSetChanged();

                //Remove the selected tag from the global list and notify the adapter to redraw the recyclerview
                results.remove(tag);
                tagResultAdapter.notifyDataSetChanged();

                if(selectedTags.size()==4) {        //The user has now selected 4 tags
                    doneSelectingTags.show();         //Show the FAB
                    doneSelectingTags.setOnClickListener(new View.OnClickListener() {       //Listen for click on FAB
                        @Override
                        public void onClick(View v) {
                            //User has selected his tags and now wishes to proceed
                            startQuestionList();
                        }
                    });
                }
            }else                   //User has already selected 4 tags and is now trying to select more
                Toast.makeText(this, "You can only select upto 4 interests !", Toast.LENGTH_LONG).show();

        }



    }

    /**
     * Callback method from UserInterestTagClickInterface , listens for clicks on the selected tag list
     * @param tag the tag that has been clicked on and the user wishes to deselect
     */
    @Override
    public void onCancel(Tag tag) {

        //The user had previously selected 4 tags and is now removing one.
        //The app can only move ahead if the user selects 4 tags , hence hiding the FAB
        if(selectedTags.size()==4)
            doneSelectingTags.hide();

        //Remove the selected tag from the global list and notify the adapter to redraw the recyclerview
        selectedTags.remove(tag);
        userInterestTagAdapter.notifyDataSetChanged();

        //Add the selected tag to the global list and notify the adapter to redraw the recyclerview
        results.add(0,tag);
        tagResultAdapter.notifyDataSetChanged();

    }

    /**
     * The user has selected his four tags he is interested in
     * We now save those tags in the sharedprefs and update the state of the app to completed registration
     * by specifying hasSelectedTags to true.
     */
    private void startQuestionList(){


        AppPreferences appPreferences = (((ApplicationClass) ApplicationClass.getContext())).getAppPreferences();
        SharedPreferences.Editor editor = appPreferences.sharedPrefs.edit();
        editor.putBoolean("hasSelectedTags",true);
        editor.putString("tagOne",selectedTags.get(0).getName());
        editor.putString("tagTwo",selectedTags.get(1).getName());
        editor.putString("tagThree",selectedTags.get(2).getName());
        editor.putString("tagFour",selectedTags.get(3).getName());
        editor.commit();

        finish();
        startActivity(new Intent(this, QuestionList.class));
    }

}
