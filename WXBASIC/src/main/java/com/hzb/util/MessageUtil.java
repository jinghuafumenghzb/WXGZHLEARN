package com.hzb.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
 
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
 
/**
 * 
 * 类名称: MessageUtil
 * 类描述: 消息处理工具
 * @author hzb
 * 创建时间:2017年12月8日下午3:20:48
 */
public class MessageUtil {
	/**
	 * 将微信的请求中参数转成map
	 */
	public static Map<String,String> xmlToMap(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		SAXReader reader = new SAXReader();
		InputStream in = null;
		try {
			in = request.getInputStream();
			Document doc = reader.read(in);
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> list = root.elements();
			for (Element element : list) {
				map.put(element.getName(), element.getText());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
