package com.gobletsoft.whatshouldidotoday;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    ActionBar actionBar;
    public String[] answers = new String[25];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        answers[0] = "";
        answers[1] = "";

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#262626")));

        final GifImageView giv = findViewById(R.id.gifImageView);

        giv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                LayoutInflater layoutInflater
                        = (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.popuplayout, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        DrawerLayout.LayoutParams.FILL_PARENT,
                        DrawerLayout.LayoutParams.FILL_PARENT);

                Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
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