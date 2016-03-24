package com.chengfeng.common.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import com.thinkjf.core.config.GlobalConfig;

public class HttpClientUtils {

	private static HttpClient client = null;
	
	/**
	 * 控制httpclient连接数
	 * 
	 * @return
	 */
	private static HttpClient initHttpClient() {

		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		// 指定向每个host发起的最大连接数，默认是2，太少了
		params.setDefaultMaxConnectionsPerHost(1000);
		// 指定总共发起的最大连接数，默认是20，太少了
		params.setMaxTotalConnections(5000);
		// 连接超时时间-10s
		params.setConnectionTimeout(60 * 1000);
		// 读取数据超时时间-60s
		params.setSoTimeout(60 * 1000);

		MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
		manager.setParams(params);

		return new HttpClient(manager);
	}
	
	public static String sendHttpReq(List<String> jsonlist,String url) throws Exception{
		final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();
		if (client == null) {
			client = initHttpClient();
		}
		PostMethod postMethod = new PostMethod(url);
		try {
			JSONArray jsonArray = new JSONArray();
			jsonArray = JSONArray.fromObject(jsonlist);
			nameValueList.add(new NameValuePair("params", jsonArray.toString()));
			postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			postMethod.setRequestBody(nameValueList.toArray(new NameValuePair[nameValueList.size()]));
			postMethod.addRequestHeader("Connection", "close");
			client.executeMethod(postMethod);
			if(postMethod.getStatusLine().getStatusCode() == 200){
				String responsess = postMethod.getResponseBodyAsString();
				JSONObject jsonresult = JSONObject.fromObject(responsess);
				String code = jsonresult.getString("code");
				if(code.equals("200")){
					String message = jsonresult.getString("message");
					return "code:"+code+"  message:"+message;
				}
			}
			return postMethod.getStatusLine().getStatusCode()+"";
		} catch (Exception e) {
			throw e;
		} finally {
			postMethod.releaseConnection();
		}
	}
}
