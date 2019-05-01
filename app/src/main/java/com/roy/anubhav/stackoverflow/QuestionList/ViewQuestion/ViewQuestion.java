package com.roy.anubhav.stackoverflow.QuestionList.ViewQuestion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.roy.anubhav.stackoverflow.R;

/**
 * Load the selected Question
 */
public class ViewQuestion extends AppCompatActivity {

    private ViewQuestionViewModel viewModel;

    //Title and Link of the question being viewed
    private String title,link;

    //Variable indicating if the user has stored this question offline
    private boolean hasSavedOffline;

    //Menu variable , used to manipulate the downloaded icon as per the state - if the question is stored offline or not
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_question);

        //Setting up the actionbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Display the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = ViewModelProviders.of(this).get(ViewQuestionViewModel.class);

        //Retrieve the link and title of the question
        link = getIntent().getStringExtra("link");
        title = getIntent().getStringExtra("title");

        //Load the question's url into the webView
        WebView webView = findViewById(R.id.webView);
        webView.loadUrl(link);
    }

    /**
     * Update the downloaded Icon as per the state
     * @param result    true - if the user has stored the variable , false- if the user doesn't have the question stored
     */
    private void updateDownloadedIcon(Boolean result) {
        hasSavedOffline = result;


            if (result)
                menu.findItem(R.id.download).setIcon(ContextCompat.getDrawable(this, R.drawable.baseline_bookmark_black_18));
            else
                menu.findItem(R.id.download).setIcon(ContextCompat.getDrawable(this, R.drawable.baseline_bookmark_border_white_18));



    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_question_action_bar, menu);

        //store the menu in the variable
        this.menu = menu;

        //CHecking for the question in the db , with the title being the parameter
        viewModel.checkIfQuestionIsAvailableOffline(title);
        viewModel.questionIsAvailableOffline.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                //Received if the user has the question or not , update the UI with the result
                updateDownloadedIcon(aBoolean);
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.share:
                shareQuestion();        //User wishes to share the question
                break;
            case R.id.download:
                if(!hasSavedOffline) {      //User wishes to save the question offline
                    viewModel.saveQuestionOffline(title, link);
                    updateDownloadedIcon(true);
                }else{                      //User wishes to delete the question from the device
                    viewModel.deleteQuestionFromDB(title);
                    updateDownloadedIcon(false);
                }
                break;

            case android.R.id.home:          //User wishes to go back
                this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void shareQuestion() {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");        //MIME type
        i.putExtra(Intent.EXTRA_SUBJECT, title);
        i.putExtra(Intent.EXTRA_TEXT, link);
        startActivity(Intent.createChooser(i, "Share this Question"));

    }
}
