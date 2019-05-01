package com.roy.anubhav.stackoverflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.roy.anubhav.stackoverflow.Login.Login;
import com.roy.anubhav.stackoverflow.QuestionList.QuestionList;
import com.roy.anubhav.stackoverflow.UserInterest.UserInterest;
import com.roy.anubhav.stackoverflow.utils.AppPreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Retrieving appPreferences from the Application class
        AppPreferences appPreferences = (((ApplicationClass) ApplicationClass.getContext())).getAppPreferences();

        //Retrieving booleans which are defined to understand the state of the app
        // isLoggedIn specifies if the user is logged into the app
        // hasSelectedTags specifies if the user has selected tags he wishes to see for the app
        boolean isLoggedIn = appPreferences.sharedPrefs.getBoolean("isLoggedIn",false);
        boolean hasSelectedTags = appPreferences.sharedPrefs.getBoolean("hasSelectedTags",false);


        //Handles the appropriate cases
        if(!isLoggedIn) {               //User hasn't logged in
            setContentView(R.layout.activity_main);

            Glide.with(this)
                    .load(R.drawable.logo)
                    .into((ImageView)findViewById(R.id.logo));

            findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startLoginActivity();
                }
            });
        }else if(isLoggedIn && !hasSelectedTags){       //User is logged in but hasn't selected tags
            finish();
            startActivity(new Intent(this, UserInterest.class));
        }else{                                          //User has completed the registration
            finish();
            startActivity(new Intent(this, QuestionList.class));
        }

    }


    //Start the Login Activity
    private void startLoginActivity() {
        finish();
        startActivity(new Intent(MainActivity.this,Login.class));
    }
}
