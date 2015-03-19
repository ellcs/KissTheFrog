package com.example.kissthefrog;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private final String TAG = "Game";

    private int _score;
    private int _round;
    private int _countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showStartFragment();
    }
    
    private void newGame() {
    	Log.i(TAG, "new game");
    	_score = 0;
    	_round = 1;
    	initRound();
    }
    
    private void initRound() {
    	Log.i(TAG, "init round");
    	_countdown = 10;
    	ViewGroup container = (ViewGroup) findViewById(R.id.container);
    	container.removeAllViews();
    	WimmelView wv = new WimmelView(this);
    	container.addView(wv,
    			ViewGroup.LayoutParams.MATCH_PARENT,
    			ViewGroup.LayoutParams.MATCH_PARENT);
    	wv.setImageCount(_round);
    	update();
    }

    private void fillTextView(int id, String text) {
        TextView tv = (TextView) findViewById(id);
        if (tv == null) {
            Log.e(TAG, "TextView (id: " + id + ") is null!");
            throw new NullPointerException("TextView is null");
        }
        tv.setText(text);
    }

    private void update() {
        Log.v(TAG, "update stats");
        fillTextView(R.id.score, Integer.toString(_score));
        fillTextView(R.id.round, Integer.toString(_round));
        fillTextView(R.id.countdown, Integer.toString(_countdown * 1000));
    }

    private void showStartFragment() {
        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        container.removeAllViews();
        container.addView(getLayoutInflater().inflate(R.layout.fragment_start, null));
        container.findViewById(R.id.start_button).setOnClickListener(this);
    }

    private void showGameOverFragment() {
        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        container.addView(getLayoutInflater().inflate(R.layout.fragment_gameover, null));
        container.findViewById(R.id.play_again_button);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start_button) {
            Log.v(TAG, "start_button clicked");
            startGame();
        } else if (v.getId() == R.id.play_again_button) {
            Log.v(TAG, "show start clicked");
            showStartFragment();
        }
    }

    private void startGame() {
        newGame();
    }
}
