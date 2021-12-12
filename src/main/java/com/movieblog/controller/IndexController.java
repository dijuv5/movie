package com.movieblog.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.movieblog.utils.DateUtility;
import com.movieblog.utils.GlobalVariable;

@Controller
public class IndexController {
	
	@Value(value = "${best.movie.url}")
	private String url;
	
	@Value(value="${current.movie.theatre}")
	private String currentMovieUrl;
	
	@RequestMapping("/index")
	public String  firstPage(Model model) {
		model.addAttribute("currYear", Calendar.getInstance().get(Calendar.YEAR));
		List<Integer>year=new ArrayList<>();
		
		for (int i = Calendar.getInstance().get(Calendar.YEAR); i >=2000; i--) {
			year.add(i);
		}
		model.addAttribute("year", year);
		return "index";

	}
	
	@GetMapping(value = "/bestmovie/{year}")
	@ResponseBody
	private String getBestMovie(@PathVariable("year")String year) {
		String uri=url;
		uri=uri.replace("{1}", year);
		RestTemplate restTemplate=new RestTemplate();
		String result=restTemplate.getForObject(uri, String.class);
		return result;
		
	}
	
	@GetMapping(value = "/moviesintheatre")
	@ResponseBody
	private String getMoviesInTheatre(String year) {
		Date today=new Date();
		String url=currentMovieUrl;
		url=url.replace("{2}", DateUtility.datetoString(today, GlobalVariable.SQL_FORMAT_STRING));
		today.setDate(today.getDate()-15);
		url=url.replace("{1}", DateUtility.datetoString(today, GlobalVariable.SQL_FORMAT_STRING));
		RestTemplate restTemplate=new RestTemplate();
		String result=restTemplate.getForObject(url, String.class);
		return result;
		
	}
	
	
}
