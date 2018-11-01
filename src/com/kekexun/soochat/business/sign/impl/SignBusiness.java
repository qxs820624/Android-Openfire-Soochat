package com.kekexun.soochat.business.sign.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.kekexun.soochat.activity.sign.SignActivity;
import com.kekexun.soochat.business.impl.BaseBusiness;
import com.kekexun.soochat.common.BusinessNameEnum;
import com.kekexun.soochat.common.K;
import com.kekexun.soochat.smack.BaseIMServer;

/**
 * 
 * @author Ke.Wang
 * @date 2015.11.25
 */
public class SignBusiness extends BaseBusiness {
	
	private static final String tag = "LoginBusiness";
	
	protected Context context;
	protected BusinessNameEnum businessUrl;
	protected SharedPreferences sharedPreferences;
	
	private BaseIMServer imServer;
	
	/**
	 * Constructor
	 */
	public SignBusiness() {
		
	}
	
	/**
	 * Constructor
	 * @param context
	 */
	public SignBusiness(Context context) {
		this.context = context;
		this.sharedPreferences = context.getSharedPreferences("prefenrences", Context.MODE_PRIVATE);
		this.imServer = BaseIMServer.getInstance();
		imServer.setSharedPreferences(sharedPreferences);
	}
	
	/**
	 * 执行登录
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public void doSignIn(final String username, final String password, final Handler handler) throws Exception {
		Log.d(tag, "------ 0 SignBusiness.doSignIn()");
		
		// 启动线程进行网络登录
		new Thread() {

			@Override
			public void run() {
				// 登录
				try {
					boolean isConnected = imServer.connect(username, password);
					Message msg = new Message();
					if (isConnected) {
						msg.what = SignActivity.CONNECT_TO_IMSERVER_SUCCESS;
					} else {
						msg.what = SignActivity.CONNECT_TO_IMSERVER_FAILURE;
						msg.obj = "登录 IMServer 失败！";
					}
					handler.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
					Log.e(tag, "@@@@@@ SignBusiness.doSignIn#new-Thread().run(), 登录IMServer出错！详细原因：" + e.getMessage());
					Message msg = new Message();
					msg.what = SignActivity.CONNECT_TO_IMSERVER_FAILURE;
					msg.obj = "登录 IMServer 失败！详细原因：" + e.getMessage();
					handler.sendMessage(msg);
				}
			}			
			
		}.start();
	}
	
	/**
	 * 通过 SharedPrefences 保存
	 * @param jsonParam
	 * @param context
	 * @return
	 */
	public boolean saveLoginInfo(String loginName, String loginPassword, boolean isLogin) {
		// 得到编辑器
		Editor editor = sharedPreferences.edit();
		editor.putString(K.Login.LOGIN_NAME, loginName);
		editor.putString(K.Login.LOGIN_PASSWORD, loginPassword);
		editor.putBoolean(K.Login.IS_LOGIN, isLogin);
		return editor.commit();
	}}
