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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Task;
import utils.HibernateUtil;
import utils.Utils;

@Controller
public class TaskController {

	SessionFactory sf = HibernateUtil.getSessionFactory();

	// Task list page
	@RequestMapping(value = "/tasklist", method = RequestMethod.GET)
	public String tasklist(Model model, HttpServletRequest req) {
		boolean log = req.getSession().getAttribute("id") != null;
		if (log) {
			int id = (Integer) req.getSession().getAttribute("id"); // Standart user ID
			Session sesi = sf.openSession();
			List<Task> taskarr = new ArrayList<Task>();
			try {
				taskarr = sesi.createQuery("from Task where task_user_id = '" + id + "'").list(); // List filling
				model.addAttribute("ls", taskarr);
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
	public String taskAddPage(HttpServletRequest req) {
		return Utils.loginControl(req, "redirect:/", "taskadd");
	}

	// Task create action
	@RequestMapping(value = "/addtask", method = RequestMethod.POST)
	public String addTask(Task task, HttpServletRequest req, @RequestParam String startDate,
			@RequestParam String dueDate) {
		boolean log = req.getSession().getAttribute("id") != null;
		if (log) {
			int id = (Integer) req.getSession().getAttribute("id"); // Standart user ID

			// Date formatter (String -> Date)
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
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

	// Task delete from list (ajax)
	@ResponseBody
	@RequestMapping(value = "/taskdelete", method = RequestMethod.POST)
	public String taskdelete(@RequestParam String id, @RequestParam String idName, @RequestParam String tableName) {
		try {
			Session sesi = sf.openSession();
			Transaction tr = sesi.beginTransaction();
			String q = "delete from " + tableName + " where " + idName + " = " + id;
			sesi.createNativeQuery(q).executeUpdate();
			tr.commit();
			sesi.close();
			return id;
		} catch (Exception e) {
			return "";
		}
	}

}
