package com.example.actionbarrefreshlibary;

import uk.co.senab.actionbarpulltorefresh.extras.actionbarcompat.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;


import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements OnRefreshListener {

	private PullToRefreshLayout mPullToRefreshLayout;
	private ListView mListView;
	private static String[] ITEMS = { "Abbaye de Belloc",
	"Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
	"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu",
	"Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler",
	"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam",
	"Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis",
	"Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
	"Allgauer Emmentaler" };
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	// Now find the PullToRefreshLayout to setup
	mPullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.ptr_layout);
	 
	// Now setup the PullToRefreshLayout
	ActionBarPullToRefresh.from(this)
	// Mark All Children as pullable
	.allChildrenArePullable()
	// Set a OnRefreshListener
	.listener(this)
	// Finally commit the setup to our PullToRefreshLayout
	.setup(mPullToRefreshLayout);
	 
	mListView = (ListView) findViewById(R.id.listview);
	mListView.setAdapter(new ArrayAdapter<String>(this,
	android.R.layout.simple_list_item_1, ITEMS));
	}
	 
	@Override
	public void onRefreshStarted(View view) {
	// Hide the list
	// setListShown(false);
	 
	/**
	42.
	* Simulate Refresh with 4 seconds sleep
	43.
	*/
	new AsyncTask<Void, Void, Void>() {
	 
	@Override
	protected Void doInBackground(Void... params) {
	try {
	Thread.sleep(5000);
	} catch (InterruptedException e) {
	e.printStackTrace();
	}
	return null;
	}
	 
	@Override
	protected void onPostExecute(Void result) {
	super.onPostExecute(result);
	 
	// Notify PullToRefreshLayout that the refresh has finished
	mPullToRefreshLayout.setRefreshComplete();
	}
	}.execute();
	}
}
