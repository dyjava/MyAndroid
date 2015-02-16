package com.aba.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReadWebUtil {
	private static int ConnectTimeout = 3000 ;
	private static int ReadTimeout = 3000 ;
	
	public String readUrlContent(String url) throws IOException{
		return readUrlContent(url, "utf-8") ;
	}
	public String readUrlContent(String url,String code) throws IOException{
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer("ReadWebUtil|readUrlContent") ;
		
		BufferedReader in = null;
		HttpURLConnection conn = null;
		StringBuffer result = new StringBuffer();
		try {
			buf.append("|").append(url) ;
			URL u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
//			…Ë÷√≥¨ ±
			conn.setConnectTimeout(ConnectTimeout);
			conn.setReadTimeout(ReadTimeout);
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(),code));
			
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				result.append(inputLine);
			}
		} catch (IOException ex) {
			StringWriter sw=new StringWriter();
			ex.printStackTrace(new PrintWriter(sw,true));
			buf.append("|").append(sw.toString()) ;
			Loggers.error(buf) ;
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
		buf.append("|").append(System.currentTimeMillis()-start) ;
		Loggers.info(buf) ;
		return result.toString();
	}
}
