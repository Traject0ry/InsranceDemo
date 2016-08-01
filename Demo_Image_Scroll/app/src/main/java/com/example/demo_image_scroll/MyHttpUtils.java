/**
 * 
 */
package com.example.demo_image_scroll;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * @author wxy
 * 
 */
public class MyHttpUtils {

	private static MyHttpUtils mInstance;

	public static MyHttpUtils getInstance() {
		if (mInstance == null) {
			synchronized (MyHttpUtils.class) {
				if (mInstance == null) {
					mInstance = new MyHttpUtils();
				}
			}
		}
		return mInstance;
	}

	public interface GetCallBack {
		void getCallBack(String result);

		void getErroy(String error);
	}

	public void getAsy(String url, final GetCallBack getcallback) {
		HttpUtils http = new HttpUtils();
		http.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				getcallback.getErroy(arg0.getExceptionCode() + ":" + arg1);
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				getcallback.getCallBack(arg0.result);
			}
		});
	}

	public void getAsyLong(String url, final GetCallBack getcallback, int time) {
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(time);
		http.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				getcallback.getCallBack(arg0.getExceptionCode() + ":" + arg1);
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				getcallback.getCallBack(arg0.result);
			}
		});
	}

	public void postAsy(String url, final GetCallBack getcallback,
			RequestParams params) {
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(0);
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				getcallback.getCallBack(arg0.getExceptionCode() + ":" + arg1);
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				getcallback.getCallBack(arg0.result);
			}
		});
	}

	public void postAsyLong(String url, final GetCallBack getcallback,
			RequestParams params, int time) {
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(time);
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				getcallback.getCallBack(arg0.getExceptionCode() + ":" + arg1);
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				getcallback.getCallBack(arg0.result);
			}
		});
	}
}
