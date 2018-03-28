package com.bootdo.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.constant.CodeConstant;
import com.bootdo.common.exception.BusinessException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * HttpClient4.3工具类
 * 
 * @author hang.luo
 */
public class HttpClientUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class); // 日志记录

	private static RequestConfig requestConfig = null;

	static {
		// 设置请求和传输超时时间
		requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
	}

	/**
	 * post请求传输json参数
	 * 
	 * @param url
	 *            url地址
	 * @param json
	 *            参数
	 * @return
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam) {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		// 设置请求和传输超时时间
		httpPost.setConfig(requestConfig);
		try {
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse result = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					str = EntityUtils.toString(result.getEntity(), "utf-8");
					// 把json字符串转换成json对象
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
					throw new BusinessException(e.getMessage(), CodeConstant.OPEN_PLATFORM_ERROR);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
			throw new BusinessException(e.getMessage(), CodeConstant.OPEN_PLATFORM_ERROR);
		} finally {
			httpPost.releaseConnection();
		}
		return chainReturn(jsonResult);
	}

	/**
	 * post请求传输String参数 例如：name=Jack&sex=1&type=2
	 * Content-type:application/x-www-form-urlencoded
	 * 
	 * @param url
	 *            url地址
	 * @param strParam
	 *            参数
	 * @return
	 */
	public static JSONObject httpPost(String url, String strParam) {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		try {
			if (null != strParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(strParam, "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/x-www-form-urlencoded");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse result = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					str = EntityUtils.toString(result.getEntity(), "utf-8");
					// 把json字符串转换成json对象
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
					throw new BusinessException(e.getMessage(), CodeConstant.OPEN_PLATFORM_ERROR);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
			throw new BusinessException(e.getMessage(), CodeConstant.OPEN_PLATFORM_ERROR);
		} finally {
			httpPost.releaseConnection();
		}
		return chainReturn(jsonResult);
	}


	/**
	 * post请求传输String参数 例如：
	 * {
	 *     "name":"11",
	 *     "sex":"22"
	 * }
	 * Content-type:application/json
	 *
	 * @param url
	 *            url地址
	 * @param json
	 *            参数
	 * @return
	 */
	public static JSONObject httpPostJsonStr(String url, String json) throws  Exception {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		try {
			if (null != json) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(json, "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse result = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				//try {
				// 读取服务器返回过来的json字符串数据
				str = EntityUtils.toString(result.getEntity(), "utf-8");
				// 把json字符串转换成json对象
				jsonResult = JSONObject.parseObject(str);
//				} catch (Exception e) {
//					e .printStackTrace();
//					logger.error("post请求提交失败:" + url, e);
//					throw new BusinessException(e.getMessage(), CodeConstant.CHAIN_ERROR);
//				}
			}else{
				logger.info("请求发送成功，并得到失败响应："+ JSONObject.toJSONString(result));
			}
			//	} catch (IOException e) {
			//e .printStackTrace();
			//logger.error("post请求提交失败:" + url, e);
			//throw new BusinessException(e.getMessage(), CodeConstant.CHAIN_ERROR);
		} finally {
			httpPost.releaseConnection();
		}
		return chainReturn(jsonResult);
	}


	/**
	 * 发送get请求
	 * 
	 * @param url
	 *            路径
	 * @return
	 */
	public static JSONObject httpGet(String url) {
		// get请求返回结果
		JSONObject jsonResult = null;
		CloseableHttpClient client = HttpClients.createDefault();
		// 发送get请求
		HttpGet request = new HttpGet(url);
		request.setConfig(requestConfig);
		try {
			CloseableHttpResponse response = client.execute(request);

			// 请求发送成功，并得到响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 读取服务器返回过来的json字符串数据
				HttpEntity entity = response.getEntity();
				String strResult = EntityUtils.toString(entity, "utf-8");
				// 把json字符串转换成json对象
				jsonResult = JSONObject.parseObject(strResult);
			} else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
			throw new BusinessException(e.getMessage(), CodeConstant.OPEN_PLATFORM_ERROR);
		} finally {
			request.releaseConnection();
		}
		return chainReturn(jsonResult);
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 *            路径
	 * @return
	 */
	public static JSONArray httpGetList(String url) {
		// get请求返回结果
		JSONArray jsonResult = null;
		CloseableHttpClient client = HttpClients.createDefault();
		// 发送get请求
		HttpGet request = new HttpGet(url);
		request.setConfig(requestConfig);
		try {
			CloseableHttpResponse response = client.execute(request);

			// 请求发送成功，并得到响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 读取服务器返回过来的json字符串数据
				HttpEntity entity = response.getEntity();
				String strResult = EntityUtils.toString(entity, "utf-8");
				// 把json字符串转换成json对象
				jsonResult = JSONArray.parseArray(strResult);
			} else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
			throw new BusinessException(e.getMessage(), CodeConstant.OPEN_PLATFORM_ERROR);
		} finally {
			request.releaseConnection();
		}
		return chainReturn(jsonResult);
	}

	// 链返回空实体
	public static JSONObject chainReturn(JSONObject jo) {
		if (jo != null) {
			return jo;
		} else {
			throw new BusinessException("No ChainData", CodeConstant.OPEN_PLATFORM_ERROR);
		}
	}

	// 链返回空集合
	public static JSONArray chainReturn(JSONArray jo) {
		if (jo != null) {
			return jo;
		} else {
			throw new BusinessException("No ChainData", CodeConstant.OPEN_PLATFORM_ERROR);
		}
	}

	/**
	 * post请求传输json参数
	 * 
	 * @param url
	 *            url地址
	 * @param json
	 *            参数
	 * @return
	 */
	public static JSONObject testPost(String url, JSONObject jsonParam) {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		// 设置请求和传输超时时间
		httpPost.setConfig(requestConfig);
		try {
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			CloseableHttpResponse result = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					str = EntityUtils.toString(result.getEntity(), "utf-8");
					// 把json字符串转换成json对象
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
					throw new BusinessException(e.getMessage(), CodeConstant.OPEN_PLATFORM_ERROR);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
			throw new BusinessException(e.getMessage(), CodeConstant.OPEN_PLATFORM_ERROR);
		} finally {
			httpPost.releaseConnection();
		}
		return chainReturn(jsonResult);
	}

}