package com.example.kissthefrog;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private final String TAG = "Game";

    private 	ImageView	_frog;
    private 	int 		_score;
    private 	int 		_round;
    private 	int 		_countdown;
    private		Random 		_rnd 		= new Random((int) System.currentTimeMillis());		
    private 	int 		_frog_id 	= 13371337;

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
    	_frog = new ImageView(this);
    	_frog.setId(_frog_id);
    	_frog.setImageResource(R.drawable.real_frog);
    	_frog.setScaleType(ImageView.ScaleType.CENTER);
    	_frog.setAdjustViewBounds(true);
    	float scale = getResources().getDisplayMetrics().density;
    	FrameLayout.LayoutParams lp = 
    		new FrameLayout.LayoutParams(Math.round(scale * 64), 
    									 Math.round(scale * 51));
    	lp.leftMargin = _rnd.nextInt(container.getWidth()  - 64);
    	lp.topMargin  = _rnd.nextInt(container.getHeight() - 51);
    	lp.gravity 	  = Gravity.TOP + Gravity.LEFT;
    	_frog.setOnClickListener(this);
    	container.addView(_frog, lp);
    	Log.d(TAG, "Frog width: " + _frog.getWidth());
    	Log.d(TAG, "Frog height: " + _frog.getHeight());
    	Log.d(TAG, "Frog position x: " + container.findViewById(_frog_id).getWidth());
    	Log.d(TAG, "Frog position y: " + container.findViewById(_frog_id).getHeight());
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
        } else if (v.getId() == _frog_id) {
        	Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
        }
    }

    private void startGame() {
        newGame();
    }
}
