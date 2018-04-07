package com.tasdemir.tasklist;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import utils.Utils;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req, Model model) {
		// Check errors
		boolean error = req.getSession().getAttribute("error") != null;
		if(error) {
			String err =""+req.getSession().getAttribute("error");	// Convert error object to string
			model.addAttribute("error", err);						// Set error model
			req.getSession().removeAttribute("error");				// Clear error object
		}
		return Utils.loginControl(req, "home", "redirect:/tasklist");
	}
}