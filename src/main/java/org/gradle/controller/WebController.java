package org.gradle.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.servlet.http.HttpServletResponse;

import org.gradle.service.TranslateMerchantData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
@RequestMapping("/controller")
public class WebController {
	private static Logger logger=LoggerFactory.getLogger(WebController.class);
	private static Queue<String> queue= new LinkedList<String>();
	@Autowired
	TranslateMerchantData translateService;
	
	@RequestMapping("/number")
	@ResponseBody
	public Map<String,Object> numberColl(){
		return translateService.translateDate(true);
	}
	
	@RequestMapping("send")
	public void test(HttpServletResponse response){
		try {
			response.sendRedirect("http://www.baidu.com");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	@RequestMapping("/queueList/{id}")
	@ResponseBody
	public List<String> testQueue(@PathVariable("id") int id){
		logger.info("id="+id);
		queue.add("hello:"+id);
		System.err.println(queue.poll());
		return null;
	}
}
