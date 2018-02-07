package com.payease.wallet.gateway.impl.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by TianQ on 2017/5/19.
 */
public class HttpClients {
    /**
     * 发起http请求，并返回结果
     */
    public static String InvokeHttpPost(String requestURL, Map<String,String> requestBody) throws Exception{

        StringBuffer bodybuffer = new StringBuffer();
        for (Map.Entry<String,String> entry : requestBody.entrySet()) {
            bodybuffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        if (bodybuffer.length()>0) bodybuffer.deleteCharAt(bodybuffer.length()-1);

        StringBuffer str = new StringBuffer();
        try {

            HttpURLConnection httpConn = (HttpURLConnection) new URL(requestURL).openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);

            OutputStream o = httpConn.getOutputStream();
            o.write(bodybuffer.toString().getBytes("UTF-8"));
            o.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));

            String s = "";
            while((s=reader.readLine())!=null){
                str.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("http请求发送失败:");
        }

        return str.toString();
    }
    /**
     * 解析多层xml,并放到单层map中
     */
    public static Map<String,String> xmlPutMap(String xmlStr) {

        Document doc = null;
        try {
            doc = new SAXReader().read(new InputSource(new StringReader(xmlStr)));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return elementsPutMap(doc.getRootElement().elements());
    }
    /**
     * 递归方法遍历xml,并放到单层的map中
     */
    private static Map<String,String> elementsPutMap(List<Element> elements){

        Map<String,String> map = new HashMap<String, String>();
        Iterator<Element> iterator = elements.iterator();
        Element element = null;
        List<Element> elementList = null;

        while (iterator.hasNext()){

            element = iterator.next();
            elementList = element.elements();

            if (elementList.size()>0){  //如果下边有子节点
                map.putAll(elementsPutMap(elementList));
            }else {
                map.put( element.getName(), element.getTextTrim());
            }
        }
        return map;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"GBK"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }

        return result;
    }
}
