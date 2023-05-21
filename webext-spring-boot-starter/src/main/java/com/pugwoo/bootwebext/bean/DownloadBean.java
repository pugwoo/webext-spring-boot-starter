package com.pugwoo.bootwebext.bean;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class DownloadBean extends ResponseEntity<byte[]> {

	/**
	 * 构造一个下载Bean
	 * @param filename 下载文件的保存名称
	 * @param bytes 下载的二进制内容
	 */
	public DownloadBean(String filename, byte[] bytes) {
		super(bytes, DownloadBeanUtils.getHeaders(filename), HttpStatus.OK);
	}
	
	/**
	 * 构造一个下载bean
	 * @param filename 下载文件的保存名称
	 * @param content 按照系统默认编码(utf-8)转成byte[]
	 */
	public DownloadBean(String filename, String content) {
		super(content == null ? new byte[0] : content.getBytes(),
				DownloadBeanUtils.getHeaders(filename), HttpStatus.OK);
	}
	
	/**
	 * 构造一个下载Bean
	 * @param filename 下载文件的保存名称
	 * @param bytes 下载的二进制内容
	 */
	public DownloadBean(String filename, byte[] bytes, Map<String, String> headers) {
		super(bytes, DownloadBeanUtils.getHeaders(filename, headers), HttpStatus.OK);
	}
	
	/**
	 * 构造一个下载bean
	 * @param filename 下载文件的保存名称
	 * @param content 按照系统默认编码(utf-8)转成byte[]
	 */
	public DownloadBean(String filename, String content, Map<String, String> headers) {
		super(content == null ? new byte[0] : content.getBytes(),
				DownloadBeanUtils.getHeaders(filename, headers), HttpStatus.OK);
	}


}
