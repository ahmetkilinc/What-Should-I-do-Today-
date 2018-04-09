package com.gobletsoft.whatshouldidotoday;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    ActionBar actionBar;
    public String[] answers = new String[25];
    public String[] urls = new String[25];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        answers[0] = getString(R.string.answer1);
        answers[1] = getString(R.string.answer2);
        answers[2] = getString(R.string.answer3);
        answers[3] = getString(R.string.answer4);
        answers[4] = getString(R.string.answer5);
        answers[5] = getString(R.string.answer6);
        answers[6] = getString(R.string.answer7);
        answers[7] = getString(R.string.answer8);
        answers[8] = getString(R.string.answer9);
        answers[9] = getString(R.string.answer10);
        answers[10] = getString(R.string.answer11);
        answers[11] = getString(R.string.answer12);
        answers[12] = getString(R.string.answer13);

        urls[0] = getString(R.string.url_answer1);
        urls[1] = getString(R.string.url_answer2);
        urls[2] = getString(R.string.url_answer3);
        urls[3] = getString(R.string.url_answer4);
        urls[4] = getString(R.string.url_answer5);
        urls[5] = getString(R.string.url_answer6);
        urls[6] = getString(R.string.url_answer7);
        urls[7] = getString(R.string.url_answer8);
        urls[8] = getString(R.string.url_answer9);
        urls[9] = getString(R.string.url_answer10);
        urls[10] = getString(R.string.url_answer11);
        urls[11] = getString(R.string.url_answer12);
        urls[12] = getString(R.string.url_answer13);

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        Random rand = new Random();
        final int  n = rand.nextInt(13) + 0;



        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#262626")));

        final GifImageView giv = findViewById(R.id.gifImageView);

        giv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Random rand = new Random();
                int  n = rand.nextInt(13) + 0;

                LayoutInflater layoutInflater
                        = (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.activity_popup, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        DrawerLayout.LayoutParams.FILL_PARENT,
                        DrawerLayout.LayoutParams.FILL_PARENT);

                Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
                Button btnRefresh = popupView.findViewById(R.id.buttonRefresh);
                final WebView wvActivity = (WebView) popupView.findViewById(R.id.webViewActivity);

                WebSettings webSettings = wvActivity.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setLoadsImagesAutomatically(true);

                final ConnectivityManager connectivityManager
                        = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();




                if (activeNetworkInfo != null) {

                    wvActivity.setWebViewClient(new WebViewClient() {

                        public boolean shouldOverrideUrlLoading(WebView view, String url) {

                            String url2 = "https://ahmetkilinc.net";

                            if (url != null && url.startsWith(url2)) {

                                view.getContext().startActivity(
                                        new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

                                return true;

                            } else {

                                return false;
                            }
                        }
                    });

                    wvActivity.loadUrl(urls[n]);
                }

                else {

                    for (int i = 0; i < 3; i++){

                        Toast.makeText(getApplicationContext(), getString(R.string.internet_error), Toast.LENGTH_LONG).show();
                    }

                    finish();
                }





                final TextView txtActivity = popupView.findViewById(R.id.textViewActivity);

                txtActivity.setText(answers[n]);

                btnRefresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Random rand = new Random();
                        int  rnd = rand.nextInt(13) + 0;
                        txtActivity.setText(answers[rnd]);



                        if (activeNetworkInfo != null) {

                            wvActivity.setWebViewClient(new WebViewClient() {

                                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                                    String url2 = "https://ahmetkilinc.net";

                                    if (url != null && url.startsWith(url2)) {

                                        view.getContext().startActivity(
                                                new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

                                        return true;

                                    } else {

                                        return false;
                                    }
                                }
                            });

                            wvActivity.loadUrl(urls[rnd]);
                        }

                        else {

                            for (int i = 0; i < 3; i++){

                                Toast.makeText(getApplicationContext(), getString(R.string.internet_error), Toast.LENGTH_LONG).show();
                            }

                            finish();
                        }



                    }
                });

                btnDismiss.setOnClickListener(new Button.OnClickListener(){

                    @Override
                    public void onClick(View v){
                        // TODO Auto-generated method stub
                        popupWindow.dismiss();
                    }});

                popupWindow.showAsDropDown(giv, 50, 50);
            }
        });
    }
}