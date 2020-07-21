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

import android.os.AsyncTask;
import android.widget.ProgressBar;

public class LoadingTask extends AsyncTask<String, Integer, Integer> {

	public interface LoadingTaskFinishedListener {
		void onTaskFinished(); // If you want to pass something back to the
								// listener add a param to this method
	}

	// This is the progress bar you want to update while the task is in progress
	private final ProgressBar progressBar;
	// This is the listener that will be told when this task is finished
	private final LoadingTaskFinishedListener finishedListener;

	/**
	 * A Loading task that will load some resources that are necessary for the
	 * app to start
	 * 
	 * @param progressBar
	 *            - the progress bar you want to update while the task is in
	 *            progress
	 * @param finishedListener
	 *            - the listener that will be told when this task is finished
	 */
	public LoadingTask(ProgressBar progressBar,
			LoadingTaskFinishedListener finishedListener) {
		this.progressBar = progressBar;
		this.finishedListener = finishedListener;

	}

	@Override
	protected Integer doInBackground(String... params) {
		if (resourcesDontAlreadyExist()) {
			downloadResources();
		}
		// Perhaps you want to return something to your post execute
		return 1234;
	}

	private boolean resourcesDontAlreadyExist() {
		// Here you would query your app's internal state to see if this
		// download had been performed before
		// Perhaps once checked save this in a shared preference for speed of
		// access next time
		return true; // returning true so we show the splash every time
	}

	private void downloadResources() {
		// We are just imitating some process thats takes a bit of time (loading
		// of resources / downloading)
		int count = 14;
		for (int i = 0; i < count; i++) {

			// Update the progress bar after every step
			int progress = (int) ((i / (float) count) * 100);
			publishProgress(progress);

			// Do some long loading things
			try {
				Thread.sleep(100);
			} catch (InterruptedException ignore) {
			}
		}
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		progressBar.setProgress(values[0]); // This is ran on the UI thread so
											// it is ok to update our progress
											// bar ( a UI view ) here
	}

	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		finishedListener.onTaskFinished(); // Tell whoever was listening we have
											// finished
	}
}
