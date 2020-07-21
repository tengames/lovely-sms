/*
The MIT License

Copyright (c) 2015 kong <tengames.inc@gmail.com>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
package com.tengames.sms.love;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.tengames.sms.love.LoadingTask.LoadingTaskFinishedListener;

public class SplashActivity extends AppCompatActivity implements LoadingTaskFinishedListener {
	
	private ProgressBar progressBar = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Show the splash screen
		setContentView(R.layout.splash_screen_activity);
		// Find the progress bar
		progressBar = (ProgressBar) findViewById(R.id.progress_bar_splash);
		progressBar.setVisibility(View.INVISIBLE);

		final ImageView ivAnim = (ImageView) findViewById(R.id.ivAnima);
		ivAnim.setBackgroundResource(R.drawable.custom_progessbar);
		ivAnim.post(new Runnable() {

			@Override
			public void run() {
				AnimationDrawable frameAnimation = (AnimationDrawable) ivAnim
						.getBackground();
				frameAnimation.start();
			}
		});
		// Start your loading
		new LoadingTask(progressBar, this).execute("www.google.co.uk");
	}

	@Override
	public void onTaskFinished() {
		completeSplash();
	}

	private void completeSplash() {
		startApp();
		finish();
	}

	private void startApp() {
		Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
		startActivity(intent);
	}
}
