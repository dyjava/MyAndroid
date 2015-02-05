package com.aba.main.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ReadWebUtil {
	private static int ConnectTimeout = 3000 ;
	private static int ReadTimeout = 3000 ;

	public static String readUrlContent(String url) throws IOException{
		return readUrlContent(url, "utf-8") ;
	}
	public static String readUrlContent(String url,String code) throws IOException{
		BufferedReader in = null;
		HttpURLConnection conn = null;
		StringBuffer result = new StringBuffer();
		try {
			URL u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
//			���ó�ʱ
			conn.setConnectTimeout(ConnectTimeout);
			conn.setReadTimeout(ReadTimeout);
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(),code));
			
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				result.append(inputLine);
//				result.append("\n");
			}
		} catch (IOException ex) {
			StringWriter sw=new StringWriter();
			ex.printStackTrace(new PrintWriter(sw,true));
			throw new IOException(sw.toString()) ;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (conn != null){
				conn.disconnect();
			}
		}
		return result.toString();
	}

	public static String getContent(String url){
		String result = "" ;
		try {
			HttpGet get = new HttpGet(url);// ����http get����
			HttpResponse httpResponse = new DefaultHttpClient().execute(get);
			// ����http����
			if (httpResponse.getStatusLine().getStatusCode() == 200){
			    result = EntityUtils.toString(httpResponse.getEntity());// ��ȡ��Ӧ���ַ���
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;
	}
}
