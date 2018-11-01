package com.kekexun.soochat.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

/**
 * 
 * @author Ke.Wang
 * @date 2015.11.25
 *
 */
public class BaseActivity extends Activity {

	protected static final int LIFTCYCLE_CREATE = 0;
	protected static final int LIFTCYCLE_START = 1;

	// 竖屏
	protected static final int WINDOW_VERTICAL = 1;
	// 横屏
	protected static final int WINDOW_HORIZONTAL = 0;
	
	// 配置选项
	protected SharedPreferences sharedPreferences;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		sharedPreferences = getSharedPreferences("prefenrences", Context.MODE_PRIVATE);
	}

	/**
	 * 检查网络状态
	 */
	protected boolean getNetState(int liftcycle) {
		ConnectivityManager conectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = conectivityManager.getActiveNetworkInfo();
		
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 横屏或竖屏
	 * @return
	 */
	@SuppressWarnings("deprecation")
	protected int horizontalOrVertical() {
		int width = getWindowManager().getDefaultDisplay().getWidth(); //TODO 方法过期
		int height = getWindowManager().getDefaultDisplay().getHeight();
		
		if (width < height) {
			return WINDOW_VERTICAL; // 竖屏
		} else {
			return WINDOW_HORIZONTAL; // 横屏
		}
	}
	
}
