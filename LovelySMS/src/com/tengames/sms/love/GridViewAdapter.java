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

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridViewAdapter extends BaseAdapter {

	private Context context;
	private Integer[] listImage = { R.drawable.chaobuoisang,
			R.drawable.kitulove, R.drawable.munggiangsinh, R.drawable.valetine,
			R.drawable.nammoi, R.drawable.vukhi1, R.drawable.congcong,
			R.drawable.loichuclove, R.drawable.ngonghinha, R.drawable.nhagiao,
			R.drawable.phunu, R.drawable.sinhnhat, R.drawable.dongvat,
			R.drawable.thitot, R.drawable.thocon, R.drawable.trungthu };

	public GridViewAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return listImage.length;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(
					MenuActivity.myWidth * 220 / 480,
					MenuActivity.myHeight * 150 / 800));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(0, 0, 0, 0);
		} else {
			imageView = (ImageView) convertView;
		}
		imageView.setImageResource(listImage[arg0]);
		return imageView;
	}

}
