package com.mybatis.demo.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http请求工具类
 * @author caspar.chen
 * @date 2017-5-19
 */
public class HttpRequestUtil {

	private static final Logger log = LoggerFactory
			.getLogger(HttpRequestUtil.class);
	private CloseableHttpClient httpClient;
	private static final String DEFAULT_ENCODING = StandardCharsets.UTF_8.name();
	
	private final static Map<String, String> DEFAULT_HEADERS_FORM_UTF8 = new HashMap<String, String>();
	static {
		DEFAULT_HEADERS_FORM_UTF8.put(HttpHeaders.CONTENT_TYPE,
				"application/x-www-form-urlencoded");
		DEFAULT_HEADERS_FORM_UTF8.put(HttpHeaders.CONTENT_ENCODING,
				DEFAULT_ENCODING);
	}

	private final static Map<String, String> DEFAULT_HEADERS_JSON_UTF8 = new HashMap<String, String>();
	static {
		DEFAULT_HEADERS_JSON_UTF8.put(HttpHeaders.CONTENT_TYPE,
				"application/json");
		DEFAULT_HEADERS_JSON_UTF8.put(HttpHeaders.CONTENT_ENCODING,
				DEFAULT_ENCODING);
	}

	public static HttpRequestUtil createDefault() {
		return new HttpRequestUtil(5000, 5000, 5000,
				DEFAULT_ENCODING);
	}

	public HttpRequestUtil(int connectionRequestTimeout, int connectionTimeout,
                           int socketTimeout, String defaultCharset) {
		super();
		init(connectionRequestTimeout, connectionTimeout, socketTimeout,
				defaultCharset, null);
	}

	public HttpRequestUtil(int connectionRequestTimeout, int connectionTimeout,
                           int socketTimeout, String defaultCharset, String proxyServer) {
		super();
		init(connectionRequestTimeout, connectionTimeout, socketTimeout,
				defaultCharset, proxyServer);
	}

	/**
	 * 构建
	 */
	private void init(int connectionRequestTimeout, int connectionTimeout,
                      int socketTimeout, String defaultCharset, String proxyServer) {
		httpClient = HttpClients.createDefault();

		Builder requestBuilder = RequestConfig.custom();
		requestBuilder.setConnectionRequestTimeout(connectionRequestTimeout);
		requestBuilder.setConnectTimeout(connectionTimeout);
		requestBuilder.setSocketTimeout(socketTimeout);
		if (StringUtils.isNotBlank(proxyServer)) {
			requestBuilder.setProxy(HttpHost.create(proxyServer));
		}
		requestBuilder.build();
	}

	private String execute(HttpUriRequest request) {
		HttpResponse response = null;
		String content = null;
		try {
			response = httpClient.execute(request);
			log.info("response================== {}", response);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				content = EntityUtils.toString(response.getEntity(),DEFAULT_ENCODING);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			HttpClientUtils.closeQuietly(response);
			response = null;
		}
		log.info(content);
		return content;
	}

	public String doGet(String url) {
		return execute(new HttpGet(url));
	}

	public JSONObject doGetToJsonObject(String url) {
		String responseStr = doGet(url);
		if(StringUtils.isEmpty(responseStr)){
			return null;
		}
		return JSONObject.parseObject(responseStr);
	}
	

	public String doPost(String url, Map<String, String> param) {
		HttpPost request = new HttpPost(url);
		if (param != null) {
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : param.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(),
						entry.getValue());
				list.add(pair);
			}
			try {
				request.setEntity(new UrlEncodedFormEntity(list,
						DEFAULT_ENCODING));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return execute(request);
	}

	public  String doPost(String url, String param) {
		if(StringUtils.isEmpty(url)){
			return null;
		}
		HttpPost request = new HttpPost(url.trim());
		if (param != null) {
			// 设置发送消息的参数
			StringEntity entity = new StringEntity(param, DEFAULT_ENCODING);
			entity.setContentEncoding(DEFAULT_ENCODING);
			entity.setContentType("application/json");
			request.setEntity(entity);
		}
		return execute(request);
	}

}
