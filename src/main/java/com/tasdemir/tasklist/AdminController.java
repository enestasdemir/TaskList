package com.tasdemir.tasklist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.Task;
import model.User;
import utils.HibernateUtil;
import utils.Utils;

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
	
	int uid;	// Selected user id

	// User's task list page
	@RequestMapping(value = "/tasklist/{id}", method = RequestMethod.GET)
	public String usersTaskListPage(@PathVariable Integer id, Model model, HttpServletRequest req) {
		uid = id;
		User user = (User) req.getSession().getAttribute("user");
		
		boolean log = req.getSession().getAttribute("id") != null;
		if (log) {
			Session sesi = sf.openSession();
			List<Task> taskarr = new ArrayList<Task>();
			try {
				taskarr = sesi.createQuery("from Task where task_user_id = '" + uid + "'").list(); // List filling
				model.addAttribute("ls", taskarr);
			} catch (Exception e) {
				System.err.println("Databse error: " + e);
			}

			return "admin/tasklist";
		} else {
			return "redirect:/";
		}
	}
	
	int tid;	// Selected task id
	
	// Task edit page
	@RequestMapping(value = "/taskedit/{id}", method = RequestMethod.GET)
	public String adminTaskEditPage(@PathVariable Integer id, Model model, HttpServletRequest req) {
		tid = id;
		User user = (User) req.getSession().getAttribute("user");

		Session sesi = sf.openSession();
		List<Task> ls = sesi.createQuery("from Task where taskId = '" + id + "' ").setMaxResults(1).list();
		if (ls.get(0).getTaskUserId() != user.getUserId()) {
			model.addAttribute("error", "User is not validated!");
		} else {
			model.addAttribute("ls", ls);
			sesi.close();
		}
		return Utils.loginControl(req, "redirect:/", "admin/taskedit");
	}
	
	// Task edit action
	@RequestMapping(value = "/updatetask", method = RequestMethod.POST)
	public String updateTask(Task task, HttpServletRequest req, @RequestParam String startDate,
			@RequestParam String dueDate) {
		boolean log = req.getSession().getAttribute("id") != null;
		if (log) {

			// Date formatter (String -> Date)
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date sd = new Date();
			Date dd = new Date();
			try {
				sd = formatter.parse(startDate);
				dd = formatter.parse(dueDate);
			} catch (ParseException e) {
				System.err.println("Date parse error: " + e);
			}

			// New task creating
			Session sesi = sf.openSession();
			Transaction tr = sesi.beginTransaction();
			task.setTaskId(tid); // Set task id
			task.setTaskUserId(uid); // Set task user id
			task.setTaskStartDate(sd); // Set task start date
			task.setTaskDueDate(dd); // Set task due date
			sesi.update(task); // Update task
			tr.commit();
			sesi.close();

			return "redirect:/admin/tasklist/" + uid;
		} else {
			return "redirect:/";
		}
	}
}
