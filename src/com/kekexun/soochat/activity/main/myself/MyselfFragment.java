package com.kekexun.soochat.activity.main.myself;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kekexun.soochat.activity.BaseFragment;
import com.kekexun.soochat.R;

public class MyselfFragment extends BaseFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.activity_main_myself, null);
	}
	
}
