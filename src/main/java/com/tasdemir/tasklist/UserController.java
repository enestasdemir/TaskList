package com.tasdemir.tasklist;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.User;
import utils.HibernateUtil;
import utils.Utils;

@Controller
public class UserController {

	SessionFactory sf = HibernateUtil.getSessionFactory();

	// User sign in action
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user, HttpServletRequest req, Model model) {
		// Check the required fields
		if (user.getUserMail().equals("") || user.getUserPassword().equals("")) {
			req.getSession().setAttribute("error", "Email or password can not be empty!");
			return "redirect:/";
		}

		User usr = null; // Create a user object for the session operations
		Session sesi = sf.openSession();
		try {
			// Fill the user obj with attributes
			usr = (User) sesi.createQuery("from User where user_mail = '" + user.getUserMail()
					+ "' and user_password = '" + user.getUserPassword() + "'").list().get(0);
		} catch (Exception e) {
			System.err.println("Database error: " + e);
		}
		sesi.close();

		// Session creation
		if (usr != null) {
			req.getSession().setAttribute("id", usr.getUserId()); // Set the user ID
			req.getSession().setAttribute("user", usr); // Set the user attributes

			return "redirect:/tasklist";
		} else {
			req.getSession().setAttribute("error", "User email or password is not valid!");
			return "redirect:/";
		}
	}

	// User register page
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String userRegisterPage(HttpServletRequest req, Model model) {
		// Check the errors
		boolean error = req.getSession().getAttribute("error") != null;
		if (error) {
			String err = "" + req.getSession().getAttribute("error"); // Convert error object to string
			model.addAttribute("error", err); // Set error model
			req.getSession().removeAttribute("error"); // Clear error object
		}

		return Utils.loginControl(req, "register", "redirect:/tasklist");
	}

	// User sign up action
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String userRegister(User user, HttpServletRequest req, @RequestParam String conUserPassword) {
		// Check required fields
		if (user.getUserMail().equals("") || user.getUserPassword().equals("") || user.getUserName().equals("")
				|| user.getUserSurname().equals("")) {
			req.getSession().setAttribute("error", "All fields are required!");
			return "redirect:/register";
		}

		// Check password matching
		if (!user.getUserPassword().equals(conUserPassword)) {
			req.getSession().setAttribute("error", "The passwords do not match!");
			return "redirect:/register";
		}

		// Create a new user
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
