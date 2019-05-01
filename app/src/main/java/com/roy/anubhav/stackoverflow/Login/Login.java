package com.roy.anubhav.stackoverflow.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.roy.anubhav.stackoverflow.ApplicationClass;
import com.roy.anubhav.stackoverflow.R;
import com.roy.anubhav.stackoverflow.UserInterest.UserInterest;
import com.roy.anubhav.stackoverflow.utils.AppPreferences;


public class Login extends AppCompatActivity {

    //WebView which handles the user login
    private WebView webView;

    //User agent - Required by the webview for Google Sign in and FaceBook Sign in
    public static final String USER_AGENT= "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting up the webview for this activity. Since the webview contains the login page , setting the content view
        // to the webview
        webView = new WebView(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        webView.setLayoutParams(params);
        setContentView(webView);

        //A callback url for when the login is complete
        final String CALLBACK_URL = "https://www.google.com";

        //Setting the required properties for the webview to work
        try { webView.getSettings().setJavaScriptEnabled(true); } catch (Exception e) { }
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSavePassword(false);
        webView.getSettings().setSaveFormData(false);
        webView.getSettings().setUserAgentString(USER_AGENT);

        //Setting a webViewClient for overriding url loading so that , when the
        //login redirects the user through the login process , the process remains
        //within the webview and check when the user has completed the login , by checking when the url
        //starts with the callback url

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {

                //The user has completed login ,
                if (url != null && url.startsWith(CALLBACK_URL)) {
                    //Start User Interest Tag picking activity
                    startUserInterest();
                }
                return false;
            }
        });

        String client_id=getString(R.string.client_id);             //Client ID ( from Stack Apps)
        String scope = getString(R.string.access_token_scope);          //Scope for the login


        //Login URL to be loaded .
        //Refer https://api.stackexchange.com/docs
        String postData = "https://stackoverflow.com/oauth/dialog?" +
                "client_id="+client_id+"&" +
                "scope="+scope+"&" +
                "redirect_uri="+CALLBACK_URL;
        webView.loadUrl(postData);
    }
    private void startUserInterest() {

        //Set the app condition to - the user has logged in , by specifying isLoggedIn to true
        AppPreferences appPreferences = (((ApplicationClass) ApplicationClass.getContext())).getAppPreferences();
        SharedPreferences.Editor editor = appPreferences.sharedPrefs.edit();
        editor.putBoolean("isLoggedIn",true);
        editor.commit();

        //Start the tag picking activity
        finish();
        startActivity(new Intent(this, UserInterest.class));
    }


}
