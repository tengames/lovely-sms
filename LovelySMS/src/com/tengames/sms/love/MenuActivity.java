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

import java.util.Random;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MenuActivity extends AppCompatActivity {
	// ads
	public static final String AD_UNIT_INTER = "xx-xxx-xx";

	private AdView adView = null; // The view to show the ad
	private InterstitialAd adInter = null; // Interstitial

	// size
	public static int myWidth = 0;
	public static int myHeight = 0;
	
	private GridView gridView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// prevent lock screen and turn on light
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		super.onCreate(savedInstanceState);
		
		//================= MAIN ====================//
		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		myWidth = size.x;
		myHeight = size.y;
		
		setContentView(R.layout.menu_activity);

		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(new GridViewAdapter(this));

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				
				switch (position) {
				case 0:
					Intent intent = new Intent(MenuActivity.this,
							MainActivity.class);
					intent.putExtra("titleSMS", "Chào buổi sáng");
					intent.putExtra("sumSMS", 44);
					intent.putExtra("sourceDraw", R.raw.chaobuoisang);
					startActivity(intent);
					
					// show interstitial
					 if (new Random().nextInt(100) > 70)
						showIntertitial();
					
					finish();
					
					break;
					
				case 1:
					Intent intent1 = new Intent(MenuActivity.this,
							MainActivity.class);
					intent1.putExtra("titleSMS", "Ký tự tình yêu");
					intent1.putExtra("sumSMS", 138);
					intent1.putExtra("sourceDraw", R.raw.kitutinhyeu);
					startActivity(intent1);
					
					// show interstitial
					 if (new Random().nextInt(100) > 70)
						showIntertitial();
					 
					finish();
					
					break;
					
				case 2:
					Intent intent2 = new Intent(MenuActivity.this,
							MainActivity.class);
					intent2.putExtra("titleSMS", "Giáng sinh an lành");
					intent2.putExtra("sumSMS", 80);
					intent2.putExtra("sourceDraw", R.raw.giangsinh);
					startActivity(intent2);
					
					// show interstitial
					 if (new Random().nextInt(100) > 70)
						showIntertitial();
					 
					finish();
					
					break;
					
				case 3:
					Intent intent3 = new Intent(MenuActivity.this,
							MainActivity.class);
					intent3.putExtra("titleSMS", "Lễ tình nhân");
					intent3.putExtra("sumSMS", 27);
					intent3.putExtra("sourceDraw", R.raw.valentine);
					startActivity(intent3);
					
					// show interstitial
					 if (new Random().nextInt(100) > 70)
						showIntertitial();
					 
					finish();
					
					break;
					
				case 4:
					Intent intent4 = new Intent(MenuActivity.this,
							MainActivity.class);
					intent4.putExtra("titleSMS", "Chúc mừng năm mới");
					intent4.putExtra("sumSMS", 83);
					intent4.putExtra("sourceDraw", R.raw.chucmungnammoi);
					startActivity(intent4);
					
					// show interstitial
					 if (new Random().nextInt(100) > 70)
						showIntertitial();
					 
					finish();
					
					break;
					
				case 5:
					Intent intent5 = new Intent(MenuActivity.this,
							MainActivity.class);
					intent5.putExtra("titleSMS", "Hình vũ khí");
					intent5.putExtra("sumSMS", 34);
					intent5.putExtra("sourceDraw", R.raw.vukhi);
					startActivity(intent5);
					
					// show interstitial
					 if (new Random().nextInt(100) > 70)
						showIntertitial();
					 
					finish();
					
					break;
					
				case 6:
					Intent intent6 = new Intent(MenuActivity.this,
							MainActivity.class);
					intent6.putExtra("titleSMS", "Hình người");
					intent6.putExtra("sumSMS", 30);
					intent6.putExtra("sourceDraw", R.raw.people);
					startActivity(intent6);
					
					// show interstitial
					 if (new Random().nextInt(100) > 70)
						showIntertitial();
					 
					finish();
					
					break;
					
				case 7:
					Intent intent7 = new Intent(MenuActivity.this,
							MainActivity.class);
					intent7.putExtra("titleSMS", "Lời chúc tình yêu");
					intent7.putExtra("sumSMS", 145);
					intent7.putExtra("sourceDraw", R.raw.loichuctinhyeu);
					startActivity(intent7);
					
					// show interstitial
					 if (new Random().nextInt(100) > 70)
						showIntertitial();
					 
					finish();
					
					break;
					
				case 8:
					Intent intent8 = new Intent(MenuActivity.this,
							MainActivity.class);
					intent8.putExtra("titleSMS", "Hình ngộ nghĩnh");
					intent8.putExtra("sumSMS", 39);
					intent8.putExtra("sourceDraw", R.raw.smsngonghinh);
					startActivity(intent8);
					
					// show interstitial
					 if (new Random().nextInt(100) > 70)
						showIntertitial();
					 
					finish();
					
					break;
					
				case 9:
					Intent intent9 = new Intent(MenuActivity.this,
							MainActivity.class);
					intent9.putExtra("titleSMS", "Nhớ ơn thầy cô");
					intent9.putExtra("sumSMS", 8);
					intent9.putExtra("sourceDraw", R.raw.smsngaynhagiaovn);
					startActivity(intent9);
					
					// show interstitial
					 if (new Random().nextInt(100) > 70)
						showIntertitial();
					 
					finish();
					
					break;
					
				case 10:
					Intent intent10 = new Intent(MenuActivity.this,
							MainActivity.class);
					intent10.putExtra("titleSMS", "Ngày phụ nữ");
					intent10.putExtra("sumSMS", 18);
					intent10.putExtra("sourceDraw", R.raw.ngaypnvn);
					startActivity(intent10);
					
					// show interstitial
					 if (new Random().nextInt(100) > 70)
						showIntertitial();
					 
					finish();
					
					break;
					
				case 11:
					Intent intent11 = new Intent(MenuActivity.this,
							MainActivity.class);
					intent11.putExtra("titleSMS", "Chúc mừng sinh nhật");
					intent11.putExtra("sumSMS", 32);
					intent11.putExtra("sourceDraw", R.raw.mungsinhnhat);
					startActivity(intent11);
					
					// show interstitial
					 if (new Random().nextInt(100) > 70)
						showIntertitial();
					 
					finish();
					
					break;
					
				case 12:
					Intent intent12 = new Intent(MenuActivity.this,
							MainActivity.class);
					intent12.putExtra("titleSMS", "Hình động vật");
					intent12.putExtra("sumSMS", 47);
					intent12.putExtra("sourceDraw", R.raw.dongvat);
					startActivity(intent12);
					
					// show interstitial
					 if (new Random().nextInt(100) > 70)
						showIntertitial();
					 
					finish();
					
					break;
					
				case 13:
					Intent intent13 = new Intent(MenuActivity.this,
							MainActivity.class);
					intent13.putExtra("titleSMS", "Chúc thi tốt");
					intent13.putExtra("sumSMS", 28);
					intent13.putExtra("sourceDraw", R.raw.thitot);
					startActivity(intent13);
					
					// show interstitial
					 if (new Random().nextInt(100) > 70)
						showIntertitial();
					 
					finish();
					
					break;
					
				case 14:
					Intent intent14 = new Intent(MenuActivity.this,
							MainActivity.class);
					intent14.putExtra("titleSMS", "Thỏ con");
					intent14.putExtra("sumSMS", 50);
					intent14.putExtra("sourceDraw", R.raw.thocon);
					startActivity(intent14);
					
					// show interstitial
					 if (new Random().nextInt(100) > 70)
						showIntertitial();
					 
					finish();
					
					break;
					
				case 15:
					Intent intent15 = new Intent(MenuActivity.this,
							MainActivity.class);
					intent15.putExtra("titleSMS", "Tết trung thu");
					intent15.putExtra("sumSMS", 18);
					intent15.putExtra("sourceDraw", R.raw.mungtrungthu);
					startActivity(intent15);
					
					// show interstitial
					 if (new Random().nextInt(100) > 70)
						showIntertitial();
					 
					finish();
					
					break;
					
				default:
					break;
				}
			}
		});
		
		//============= ADS ================//
		/******************** ADMOB ************************/
		// Create an ad.
		adView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
		
		// create intertital
		adInter = new InterstitialAd(this);
		adInter.setAdUnitId(AD_UNIT_INTER);

		AdRequest adRequestInter = new AdRequest.Builder().build();
		adInter.loadAd(adRequestInter);

		// reload ad
		adInter.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				// show admob
				showAdmob(true);

				// close ad inter
				AdRequest adRequest = new AdRequest.Builder().build();
				adInter.loadAd(adRequest);
				super.onAdClosed();
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {
				super.onAdFailedToLoad(errorCode);
			}
		});

		// Add the AdView to the view hierarchy. The view will have no size
		// until the ad is loaded.
		final RelativeLayout container = new RelativeLayout(this);
		container.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		// define ad view parameters
		final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				if (adView.getParent() == null) {
					container.addView(adView, layoutParams);
				}
				super.onAdLoaded();
			}
		});
	}

	/**
	 * Admob
	 */
	private void showAdmob(final boolean visible) {
		if (visible) { // show admob
			adView.setVisibility(View.VISIBLE);
		} else { // hide admob
			adView.setVisibility(View.INVISIBLE);
		}
	}

	/**
	 * Intertitial
	 */
	private void showIntertitial() {
		if (adInter.isLoaded()) {
			// hide admob
			showAdmob(false);

			// show intertitial
			adInter.show();
		} else {
			// reload ad inter
			AdRequest adRequest = new AdRequest.Builder().build();
			adInter.loadAd(adRequest);

			// show admob
			showAdmob(true);
		}
	}

	/**
	 * check Internet connection
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (activeNetwork != null && activeNetwork.isConnected()) {
			return true;
		}

		return false;
	}
	
	@Override
	public void onBackPressed() {
		// show interstitial
		if (new Random().nextInt(100) > 50)
			showIntertitial();
		
		// show dialog
		 new AlertDialog.Builder(this)
         .setMessage("Bạn muốn thoát ứng dụng ?")
         .setCancelable(false)
         .setPositiveButton("Có", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int id) {
                  finish();
             }
         })
         .setNegativeButton("Không", null)
         .show();
	}

}
