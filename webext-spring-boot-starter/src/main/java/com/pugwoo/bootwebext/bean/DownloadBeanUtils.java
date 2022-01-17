package com.pugwoo.bootwebext.bean;

import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 设置下载http头部
 */
public class DownloadBeanUtils {

    public static HttpHeaders getHeaders(String filename) {
        return getHeaders(filename, null);
    }

    public static HttpHeaders getHeaders(String filename, Map<String, String> headers) {
        HttpHeaders h = new HttpHeaders();
        if (filename != null && (headers == null || headers.get("Content-Disposition") == null)) {
            try {
                filename = URLEncoder.encode(filename, "UTF-8");
            } catch (UnsupportedEncodingException e) { // ignore
            }
            h.add("Content-Disposition", "attachment;filename=" + filename);
        }

        if(headers != null) {
            for(Map.Entry<String, String> entry : headers.entrySet()) {
                h.add(entry.getKey(), entry.getValue());
            }
        }

        return h;
    }

}
