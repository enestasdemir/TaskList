package com.tasdemir.tasklist;

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
public class HomeController {

	SessionFactory sf = HibernateUtil.getSessionFactory();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}

	// Login action
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user, HttpServletRequest req) {
		User usr = null;
		Session sesi = sf.openSession();

		try {
			usr = (User) sesi.createQuery("from User where user_mail = '" + user.getUserMail()
					+ "' and user_password = '" + user.getUserPassword() + "'").list().get(0);

		} catch (Exception e) {
			System.err.println("Database error: " + e);
		}
		sesi.close();

		// Login check
		if (usr != null) {
			req.getSession().setAttribute("id", usr.getUserId());
			req.getSession().setAttribute("user", usr);

			return "redirect:/tasklist";
		} else {
			req.getSession().setAttribute("error", "User mail or password is not valid!");
			return "redirect:/";
		}
	}
}
