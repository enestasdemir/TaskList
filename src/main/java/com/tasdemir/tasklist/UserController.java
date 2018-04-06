package com.tasdemir.tasklist;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.User;
import utils.HibernateUtil;
import utils.Utils;

@Controller
public class UserController {

	SessionFactory sf = HibernateUtil.getSessionFactory();

	// User sign in action
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

	// User register page
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String userRegisterPage(HttpServletRequest req) {
		return Utils.loginControl(req, "register", "redirect:/tasklist");
	}

	// User sign up action
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String userRegister(User user) {
		Date date = new Date();
		Session sesi = sf.openSession();
		Transaction tr = sesi.beginTransaction();
		user.setUserId(Integer.MAX_VALUE); // Set user id
		user.setUserRole(0); // Set user role (as Standart User)
		user.setUserRegisterDate(date); // Set register date (now)
		sesi.save(user); // Save user
		tr.commit();
		sesi.close();
		
		return "redirect:/";
	}
	
	// User log out action
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut(HttpServletRequest req) {
		// individual log out
		req.getSession().removeAttribute("id"); // Remove user session id
		req.getSession().invalidate(); // Remove all sessions
		
		return "redirect:/";
	}

}
