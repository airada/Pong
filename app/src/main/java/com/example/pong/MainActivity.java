package com.example.pong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.activity_main);
        //setContentView(new GamePanel(this));
        //configuringNextButton();
    }

    public void playPVP( View view ) {
        setContentView(new GamePanel2(MainActivity.this)); //Player
    }

    public void playPVAI(View view) {
        setContentView(new GamePanel(MainActivity.this)); //AI slow
    }

    public void playEndless(View view){
        setContentView(new GamePanel3(MainActivity.this)); //Endless
    }
}
