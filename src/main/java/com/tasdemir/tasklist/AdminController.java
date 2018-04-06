package com.tasdemir.tasklist;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.User;
import utils.HibernateUtil;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	SessionFactory sf = HibernateUtil.getSessionFactory();

	// User list page
	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public String userList(Model model, HttpServletRequest req) {
		boolean log = req.getSession().getAttribute("id") != null;
		User user = (User) req.getSession().getAttribute("user");
		int role = user.getUserRole();
		if (log && role == 1) {
			// int id = (Integer) req.getSession().getAttribute("id"); // Standart user ID
			Session sesi = sf.openSession();
			List<User> userarr = new ArrayList<User>();
			try {
				userarr = sesi.createQuery("from User").list(); // List filling
				model.addAttribute("uls", userarr);
			} catch (Exception e) {
				System.err.println("Databse error: " + e);
			}

			return "admin/userlist";
		} else {
			return "redirect:/";
		}
	}
}
