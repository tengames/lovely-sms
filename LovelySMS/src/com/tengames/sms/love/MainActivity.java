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

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

@SuppressLint("ClickableViewAccessibility") public class MainActivity extends AppCompatActivity {
	// UI
	private Button btCopy = null;
	private Button btSms = null;
	private Button btHome = null;
	
	private ImageView btShare = null;
	private ImageView ivFirst = null;
	private ImageView ivLast = null;
	private ImageView ivNext = null;
	private ImageView ivPrev = null;
	
	private TextView tvInfo = null;
	private TextView tvPage = null;
	private TextView tvTitle = null;
	
	private ScrollView scrollView = null;
	
	// Animations
	private Animation animation1 = null;
	private Animation animation3 = null;
	private Animation animVuotPhai = null;
	
	// Variables
	private String titleSMS = null;
	private String data = null;
	private String[] numSMS = null;

	private int numberPage;
	private int i;
	private int sourceDraw;
	private int sumSMS;
	
	private ClipboardManager clipboardManager = null;
	
	private InterstitialAd adInter = null; // Interstitial

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// prevent lock screen and turn on light
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sms_information);
		Bundle bundle = getIntent().getExtras();
		sourceDraw = bundle.getInt("sourceDraw");
		sumSMS = bundle.getInt("sumSMS");
		titleSMS = bundle.getString("titleSMS");
		
		init();

		final GestureDetector myDetector = new GestureDetector(this,
				new MyOnGestureListener());
		scrollView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return myDetector.onTouchEvent(event);
			}
		});
		tvInfo.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return myDetector.onTouchEvent(event);
			}
		});

		// create interstital
		adInter = new InterstitialAd(this);
		adInter.setAdUnitId(MenuActivity.AD_UNIT_INTER);

		AdRequest adRequestInter = new AdRequest.Builder().build();
		adInter.loadAd(adRequestInter);

		// reload ad
		adInter.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				// close ad interstitial
				AdRequest adRequest = new AdRequest.Builder().build();
				adInter.loadAd(adRequest);
				super.onAdClosed();
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {
				super.onAdFailedToLoad(errorCode);
			}
		});
	}

	private void init() {
		Typeface typeface = Typeface.createFromAsset(getAssets(), "font.TTF");

		tvTitle = (TextView) findViewById(R.id.tvTypesms);
		tvTitle.setTypeface(typeface);

		tvInfo = (TextView) findViewById(R.id.tvInfo);
		tvPage = (TextView) findViewById(R.id.tvPage);
		tvInfo = (TextView) findViewById(R.id.tvInfo);

		btCopy = (Button) findViewById(R.id.btCopy);
		btShare = (ImageView) findViewById(R.id.btShare);
		btSms = (Button) findViewById(R.id.btSms);
		btHome = (Button) findViewById(R.id.home);
		ivFirst = (ImageView) findViewById(R.id.ivFirst);
		ivLast = (ImageView) findViewById(R.id.ivLast);
		ivNext = (ImageView) findViewById(R.id.ivNext);
		ivPrev = (ImageView) findViewById(R.id.ivPrev);
		scrollView = (ScrollView) findViewById(R.id.scrollView);

		tvInfo.setMovementMethod(ScrollingMovementMethod.getInstance());

		// AnimationUtils: animate image
		animation1 = AnimationUtils.loadAnimation(this, 17432578);
		animation3 = AnimationUtils.loadAnimation(this, 17432576);
		animVuotPhai = AnimationUtils.loadAnimation(this, R.anim.move);

		tvTitle.setAnimation(animation1);
		data = readtxt().replaceAll("\r", "");
		numSMS = data.split("====================");
		
		tvInfo.setText(numSMS[0]);
		tvPage.setText("1/" + sumSMS);
		tvTitle.setText(titleSMS);
		
		numberPage = 1;
		i = 0;
		
		clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
		
		buttonListener();
	}

	private class MyOnGestureListener implements OnGestureListener {

		@Override
		public boolean onDown(MotionEvent e) {
			return false;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			float e1X = e1.getX();
			float e2X = e2.getX();
			float distX = e2X - e1X;
			float distY = e2.getY() - e1.getY();

			System.out.println("x1=" + e1X + " y1=" + e1.getY());
			System.out.println("x2=" + e2X + " y2=" + e2.getY());
			if (Math.abs(distY) < 100) {
				if (distX > 20) {
					if (i > 0) {
						MainActivity MainActivity1 = MainActivity.this;
						MainActivity1.i = MainActivity1.i - 1;
						MainActivity MainActivity2 = MainActivity.this;
						MainActivity2.numberPage = MainActivity2.numberPage - 1;
						scrollView.scrollTo(0, 0);
						tvInfo.startAnimation(animation1);
						tvInfo.setText(numSMS[i]);
						tvPage.setText(numberPage + "/" + sumSMS);
					}
				}
				if (distX < -50) {
					if (i < sumSMS - 1) {
						MainActivity MainActivity1 = MainActivity.this;
						MainActivity1.i = 1 + MainActivity1.i;
						MainActivity MainActivity2 = MainActivity.this;
						MainActivity2.numberPage = 1 + MainActivity2.numberPage;
						scrollView.scrollTo(0, 0);
						tvInfo.startAnimation(animVuotPhai);
						tvInfo.setText(numSMS[i]);
						tvPage.setText(numberPage + "/" + sumSMS);
					}
				}
			}
			return true;
		}

		@Override
		public void onLongPress(MotionEvent e) {

		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {

		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}
	}

	private void buttonListener() {
		btHome.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent(MainActivity.this, MenuActivity.class);
				startActivity(intent2);
				
				// show interstitial
				if (new Random().nextInt(100) > 50)
					showIntertitial();
				
				finish();
			}
		});

		btSms.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ClipData clip = ClipData.newPlainText("TXT_COPY", tvInfo.getText().toString());
				clipboardManager.setPrimaryClip(clip);
				
				Intent smsIntent = new Intent(Intent.ACTION_VIEW);
				smsIntent.setType("vnd.android-dir/mms-sms");
				smsIntent.putExtra("sms_body", clipboardManager.getPrimaryClip().getItemAt(0).getText());
				startActivity(smsIntent);
			}
		});
		
		btCopy.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ClipData clip = ClipData.newPlainText("TXT_COPY", tvInfo.getText().toString());
				clipboardManager.setPrimaryClip(clip);
				
				Toast.makeText(MainActivity.this.getApplicationContext(), "Copy tin nhắn thành công !", Toast.LENGTH_SHORT).show();
			}
		});
		
		btShare.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ClipData clip = ClipData.newPlainText("TXT_COPY", tvInfo.getText().toString());
				clipboardManager.setPrimaryClip(clip);
				
				Intent localIntent = new Intent();
				localIntent.setAction(Intent.ACTION_SEND);
				localIntent.setType("text/plain");
				localIntent.putExtra(Intent.EXTRA_TEXT, clipboardManager.getPrimaryClip().getItemAt(0).getText());
				startActivity(Intent.createChooser(localIntent, "Share"));
			}
		});
		
		ivFirst.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				i = 0;
				numberPage = 1;
				scrollView.scrollTo(0, 0);
				tvInfo.startAnimation(animation3);
				tvInfo.setText(numSMS[i]);
				tvPage.setText(numberPage + "/" + sumSMS);
			}
		});
		
		ivLast.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				i = sumSMS - 1;
				numberPage = sumSMS;
				scrollView.scrollTo(0, 0);
				tvInfo.startAnimation(animation3);
				tvInfo.setText(numSMS[i]);
				tvPage.setText(numberPage + "/" + sumSMS);
			}
		});
		
		ivNext.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (i < sumSMS - 1) {
					MainActivity MainActivity1 = MainActivity.this;
					MainActivity1.i = 1 + MainActivity1.i;
					MainActivity MainActivity2 = MainActivity.this;
					MainActivity2.numberPage = 1 + MainActivity2.numberPage;
					scrollView.scrollTo(0, 0);
					tvInfo.startAnimation(animVuotPhai);
					tvInfo.setText(numSMS[i]);
					tvPage.setText(numberPage + "/" + sumSMS);
				}
			}
		});
		
		ivPrev.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (i > 0) {
					MainActivity MainActivity1 = MainActivity.this;
					MainActivity1.i = MainActivity1.i - 1;
					MainActivity MainActivity2 = MainActivity.this;
					MainActivity2.numberPage = MainActivity2.numberPage - 1;
					scrollView.scrollTo(0, 0);
					tvInfo.startAnimation(animation1);
					tvInfo.setText(numSMS[i]);
					tvPage.setText(numberPage + "/" + sumSMS);
				}
			}
		});
	}

	private String readtxt() {
		InputStream localiInputStream = getResources().openRawResource(
				sourceDraw);
		String str = "";
		byte[] arrByte = new byte[4036];
		try {
			while (true) {
				if (localiInputStream.read(arrByte) == -1) {
					localiInputStream.close();
					return str;
				}
				str = str + new String(arrByte);
				setProgress((int) (100L * 0L / str.length()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return str;
	}
	
	/**
	 * Intertitial
	 */
	private void showIntertitial() {
		if (adInter.isLoaded()) {
			// show intertitial
			adInter.show();
		} else {
			// reload ad inter
			AdRequest adRequest = new AdRequest.Builder().build();
			adInter.loadAd(adRequest);
		}
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, MenuActivity.class);
		startActivity(intent);
		
		// show interstitial
		if (new Random().nextInt(100) > 50)
			showIntertitial();
		
		finish();
	}

}
