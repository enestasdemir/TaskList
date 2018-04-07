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
import org.springframework.web.bind.annotation.ResponseBody;

import model.Task;
import model.User;
import utils.HibernateUtil;
import utils.Utils;

@Controller
public class TaskController {

	SessionFactory sf = HibernateUtil.getSessionFactory();

	// Task list page
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/tasklist", method = RequestMethod.GET)
	public String taskList(Model model, HttpServletRequest req) {
		model.addAttribute("filter", 1); // Filter dropdown menu activate
		// Check the session
		boolean log = req.getSession().getAttribute("id") != null;
		if (log) {
			int id = (Integer) req.getSession().getAttribute("id"); // Set the user ID
			Session sesi = sf.openSession();
			List<Task> taskarr = new ArrayList<Task>(); // Create a list for tasks
			try {
				// Fill the task list with tasks
				taskarr = sesi.createQuery("from Task where task_user_id = '" + id + "'").list();
				model.addAttribute("ls", taskarr); // Create a model for the task list
			} catch (Exception e) {
				System.err.println("Databse error: " + e);
			}

			return "tasklist";
		} else {
			return "redirect:/";
		}
	}

	// Task add page
	@RequestMapping(value = "/taskadd", method = RequestMethod.GET)
	public String taskAddPage(HttpServletRequest req, Model model) {
		// Check errors
		boolean error = req.getSession().getAttribute("error") != null;
		if (error) {
			String err = "" + req.getSession().getAttribute("error"); // Convert error object to string
			model.addAttribute("error", err); // Set error model
			req.getSession().removeAttribute("error"); // Clear error object
		}

		return Utils.loginControl(req, "redirect:/", "taskadd");
	}

	// Task create action
	@RequestMapping(value = "/addtask", method = RequestMethod.POST)
	public String addTask(Task task, HttpServletRequest req, @RequestParam String startDate,
			@RequestParam String dueDate) {
		// Check required fields
		if (task.getTaskName().equals("") || task.getTaskDescription().equals("") || startDate.equals("")
				|| dueDate.equals("")) {
			req.getSession().setAttribute("error", "All fields are required!");
			return "redirect:/taskadd";
		}

		// Check the sessions
		boolean log = req.getSession().getAttribute("id") != null;
		if (log) {
			int id = (Integer) req.getSession().getAttribute("id"); // Standart user ID

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
			task.setTaskId(Integer.MAX_VALUE); // Set task id
			task.setTaskUserId(id); // Set task user id
			task.setTaskStartDate(sd); // Set task start date
			task.setTaskDueDate(dd); // Set task due date
			sesi.save(task); // Save task
			tr.commit();
			sesi.close();

			return "redirect:/tasklist";
		} else {
			return "redirect:/";
		}
	}

	int tid; // Selected task id

	// Task edit page
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/taskedit/{id}", method = RequestMethod.GET)
	public String taskEditPage(@PathVariable Integer id, Model model, HttpServletRequest req) {
		tid = id; // Set task ID for update operations
		User user = (User) req.getSession().getAttribute("user"); // Get the user's attributes

		Session sesi = sf.openSession();
		// Create a list and fill it with the task
		List<Task> ls = sesi.createQuery("from Task where taskId = '" + id + "' ").setMaxResults(1).list();
		// Check the user's ID for validation
		if (ls.get(0).getTaskUserId() != user.getUserId()) {
			model.addAttribute("error", "User is not validated!");
		} else {
			model.addAttribute("ls", ls); // Create a model for the task
			sesi.close();
		}
		return Utils.loginControl(req, "redirect:/", "taskedit");
	}

	// Task edit action
	@RequestMapping(value = "/updatetask", method = RequestMethod.POST)
	public String updateTask(Task task, HttpServletRequest req, @RequestParam String startDate,
			@RequestParam String dueDate) {
		// Check required fields
		if (task.getTaskName().equals("") || task.getTaskDescription().equals("") || startDate.equals("")
				|| dueDate.equals("")) {
			req.getSession().setAttribute("error", "All fields are required!");
			return "redirect:/taskedit/" + tid;
		}

		// Check the session
		boolean log = req.getSession().getAttribute("id") != null;
		if (log) {
			int id = (Integer) req.getSession().getAttribute("id"); // Standart user ID

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
			task.setTaskId(tid); // Set the task id
			task.setTaskUserId(id); // Set the task user ID
			task.setTaskStartDate(sd); // Set the task start date
			task.setTaskDueDate(dd); // Set the task due date
			sesi.update(task); // Update the task
			tr.commit();
			sesi.close();

			return "redirect:/tasklist";
		} else {
			return "redirect:/";
		}
	}

	// Task delete from list (ajax)
	@ResponseBody
	@RequestMapping(value = "/taskdelete", method = RequestMethod.POST)
	public String taskDelete(@RequestParam String id, @RequestParam String idName, @RequestParam String tableName) {
		try {
			Session sesi = sf.openSession();
			Transaction tr = sesi.beginTransaction();
			// Delete operation
			String q = "delete from " + tableName + " where " + idName + " = " + id;
			sesi.createNativeQuery(q).executeUpdate();
			tr.commit();
			sesi.close();
			return id;
		} catch (Exception e) {
			return "";
		}
	}

	// Task filtering
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/tasklist/{fid}", method = RequestMethod.GET)
	public String taskFilter(@PathVariable Integer fid, HttpServletRequest req, Model model) {
		model.addAttribute("filter", 1); // Filter dropdown menu activate
		// Check the session
		boolean log = req.getSession().getAttribute("id") != null;
		if (log) {
			int id = (Integer) req.getSession().getAttribute("id"); // Get the user's ID
			Session sesi = sf.openSession();
			List<Task> ftaskarr = new ArrayList<Task>(); // Create a list for the filtered tasks
			try {
				// Fill the list with the filtered tasks
				ftaskarr = sesi
						.createQuery("from Task where task_user_id = '" + id + "' and task_status = '" + fid + "'")
						.list();
				model.addAttribute("ls", ftaskarr); // Create a model for the list
			} catch (Exception e) {
				System.err.println("Databse error: " + e);
			}

			return "tasklist";
		} else {
			return "redirect:/";
		}
	}

}
