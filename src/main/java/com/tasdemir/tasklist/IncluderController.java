package com.tasdemir.tasklist;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.User;

@Controller
public class IncluderController {
	// Navbar includer
	@RequestMapping(value = "/navbar", method = RequestMethod.GET)
	public String navbar(HttpServletRequest req, Model model) {
		boolean log = req.getSession().getAttribute("id") != null;
		if (log) {
			User user = (User) req.getSession().getAttribute("user");
			List<User> userarr = new ArrayList<User>();
			userarr.add(user);
			model.addAttribute("us", userarr);
		}
		return "inc/navbar";
	}

	// CSS includer
	@RequestMapping(value = "/css", method = RequestMethod.GET)
	public String css() {
		return "inc/css";
	}
	
	// JS includer
	@RequestMapping(value = "/js", method = RequestMethod.GET)
	public String js() {
		return "inc/js";
	}
}
