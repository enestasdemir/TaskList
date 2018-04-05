package com.tasdemir.tasklist;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.User;
import utils.HibernateUtil;

@Controller
public class UserController {

	SessionFactory sf = HibernateUtil.getSessionFactory();

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerUserPage() {
		return "register";
	}

	// Sign up action
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(User user) {
		Date date = new Date();
		Session sesi = sf.openSession();
		Transaction tr = sesi.beginTransaction();
		user.setUserId(Integer.MAX_VALUE);
		user.setUserRole(0);
		user.setUserRegisterDate(date);
		sesi.save(user);
		tr.commit();
		sesi.close();

		return "redirect:/";
	}

}
