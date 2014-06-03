package com.example.twittertest;

import com.example.twittertest.utils.Defines;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class UserTimeLineActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getIntent()
						.getStringArrayListExtra(Defines.TIMELINE_DATA)));
		getListView().setTextFilterEnabled(true);
	}

}
