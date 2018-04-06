package com.tasdemir.tasklist;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import utils.HibernateUtil;
import utils.Utils;

@Controller
public class HomeController {

	SessionFactory sf = HibernateUtil.getSessionFactory();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req) {
		return Utils.loginControl(req, "home", "redirect:/tasklist");
	}
}