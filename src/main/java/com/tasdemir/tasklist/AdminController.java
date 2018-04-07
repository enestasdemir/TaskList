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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public String userList(Model model, HttpServletRequest req) {
		// Check the session
		boolean log = req.getSession().getAttribute("id") != null;
		if (log) {
			User user = (User) req.getSession().getAttribute("user");
			int role = user.getUserRole();
			// Check the user's role
			if (role == 1) {
				Session sesi = sf.openSession();
				List<User> userarr = new ArrayList<User>(); // Create a list for users
				try {
					userarr = sesi.createQuery("from User").list(); // Fill the user list
					model.addAttribute("uls", userarr); // Create a model for the user list
				} catch (Exception e) {
					System.err.println("Database error (User list filling): " + e);
				}
				return "admin/userlist";
			} else {
				req.getSession().setAttribute("error", "Your user permissions are insufficient!");
				return "redirect:/";
			}
		} else {
			req.getSession().setAttribute("error", "You must be login first!");
			return "redirect:/";
		}

	}

	int uid; // Selected user id

	// User's task list page
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/tasklist/{id}", method = RequestMethod.GET)
	public String usersTaskListPage(@PathVariable Integer id, Model model, HttpServletRequest req) {
		uid = id; // Set user id for filter operations
		model.addAttribute("uid", uid); // For filter page activate
		model.addAttribute("filter", 2); // Filter dropdown menu activate
		// Check the session
		boolean log = req.getSession().getAttribute("id") != null;
		if (log) {
			User user = (User) req.getSession().getAttribute("user");
			int role = user.getUserRole();
			// Check the user's role
			if (role == 1) {
				Session sesi = sf.openSession();
				List<Task> taskarr = new ArrayList<Task>(); // Create a list for tasks
				try {
					// Task list filling
					taskarr = sesi.createQuery("from Task where task_user_id = '" + uid + "'").list();
					model.addAttribute("ls", taskarr); // Create a model for the task list
				} catch (Exception e) {
					System.err.println("Database error (Task list filling): " + e);
				}

				return "admin/tasklist";
			} else {
				req.getSession().setAttribute("error", "Your user permissions are insufficient!");
				return "redirect:/";
			}
		} else {
			req.getSession().setAttribute("error", "You must be login first!");
			return "redirect:/";
		}
	}

	int tid; // Selected task id

	// Task edit page
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/taskedit/{id}", method = RequestMethod.GET)
	public String adminTaskEditPage(@PathVariable Integer id, Model model, HttpServletRequest req) {
		tid = id; // Set task id for update operations
		// Check the session
		boolean log = req.getSession().getAttribute("id") != null;
		if (log) {
			User user = (User) req.getSession().getAttribute("user");
			int role = user.getUserRole();
			// Check the user's role
			if (role == 1) {
				Session sesi = sf.openSession();
				// Fill the list with the task
				List<Task> ls = sesi.createQuery("from Task where taskId = '" + id + "' ").setMaxResults(1).list();
				model.addAttribute("ls", ls); // Create a model for the task
				sesi.close();
			} else {
				req.getSession().setAttribute("error", "Your user permissions are insufficient!");
				return "redirect:/";
			}
		} else {
			req.getSession().setAttribute("error", "You must be login first!");
			return "redirect:/";
		}
		return Utils.loginControl(req, "redirect:/", "admin/taskedit");
	}

	// Task edit action
	@RequestMapping(value = "/updatetask", method = RequestMethod.POST)
	public String updateTask(Task task, HttpServletRequest req, @RequestParam String startDate,
			@RequestParam String dueDate) {
		// Check required fields
		if (task.getTaskName().equals("") || task.getTaskDescription().equals("") || startDate.equals("")
				|| dueDate.equals("")) {
			req.getSession().setAttribute("error", "All fields are required!");
			return "redirect:/taskedit/" + task.getTaskId();
		}

		// Check the session
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
			req.getSession().setAttribute("error", "Your user permissions are insufficient!");
			return "redirect:/";
		}
	}

	// Task filtering
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/tasklist/{id}/{fid}", method = RequestMethod.GET)
	public String taskFilter(@PathVariable Integer id, @PathVariable Integer fid, HttpServletRequest req, Model model) {
		uid = id; // Set the user id for filter operations
		model.addAttribute("uid", uid); // For filter page activate
		model.addAttribute("filter", 2); // Filter dropdown menu activate
		// Check the session
		boolean log = req.getSession().getAttribute("id") != null;
		if (log) {
			User user = (User) req.getSession().getAttribute("user");
			int role = user.getUserRole();
			// Check the user's role
			if (role == 1) {
				Session sesi = sf.openSession();
				List<Task> ftaskarr = new ArrayList<Task>(); // Create a list for the filtered tasks
				try {
					// Fill the list with filtered tasks
					ftaskarr = sesi
							.createQuery("from Task where task_user_id = '" + uid + "' and task_status = '" + fid + "'")
							.list();
					model.addAttribute("ls", ftaskarr); // Create a model for the filtered task list
				} catch (Exception e) {
					System.err.println("Database error: " + e);
				}

				return "tasklist";
			} else {
				req.getSession().setAttribute("error", "Your user permissions are insufficient!");
				return "redirect:/";
			}
		} else {
			req.getSession().setAttribute("error", "You must be login first!");
			return "redirect:/";
		}
	}
}
