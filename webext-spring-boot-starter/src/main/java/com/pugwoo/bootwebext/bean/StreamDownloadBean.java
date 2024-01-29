package com.pugwoo.bootwebext.bean;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.InputStream;
import java.util.Map;

public class StreamDownloadBean extends ResponseEntity<InputStreamResource> {
	
	/**
	 * 构造一个下载Bean
	 * @param filename 下载文件的保存名称
	 * @param in 输入流
	 */
	public StreamDownloadBean(String filename, InputStream in) {
		super(new InputStreamResource(in), DownloadBeanUtils.getHeaders(filename), 200);
	}
	
	/**
	 * 构造一个下载Bean
	 * @param filename 下载文件的保存名称
	 * @param in 输入流
	 * @param headers 自定义头部
	 */
	public StreamDownloadBean(String filename, InputStream in, Map<String, String> headers) {
		super(new InputStreamResource(in), DownloadBeanUtils.getHeaders(filename, headers), 200);
	}

	/**
	 * 构造一个下载Bean
	 * @param filename 下载文件的保存名称
	 * @param in 输入流
	 * @param headers 自定义头部
	 * @param status 自定义响应状态
	 */
	public StreamDownloadBean(String filename, InputStream in, Map<String, String> headers, HttpStatus status) {
		super(new InputStreamResource(in), DownloadBeanUtils.getHeaders(filename, headers), status);
	}

}
