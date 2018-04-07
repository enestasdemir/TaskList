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
		// Check the session
		boolean log = req.getSession().getAttribute("id") != null;
		if (log) {
			User user = (User) req.getSession().getAttribute("user"); // Get user's attributes
			List<User> userarr = new ArrayList<User>(); // Set the list for user
			userarr.add(user); // Add the user to list
			model.addAttribute("us", userarr); // Create a model for user list
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
