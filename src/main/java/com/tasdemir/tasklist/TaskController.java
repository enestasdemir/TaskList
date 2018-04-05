package com.tasdemir.tasklist;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Task;
import utils.HibernateUtil;

@Controller
public class TaskController {

	SessionFactory sf = HibernateUtil.getSessionFactory();

	// Task list page
	@RequestMapping(value = "/tasklist", method = RequestMethod.GET)
	public String tasklist(Model model, HttpServletRequest req) {
		int id = (Integer) req.getSession().getAttribute("id");		// Standart user ID
		Session sesi = sf.openSession();
		List<Task> taskarr = new ArrayList<Task>();
		try {
			taskarr = sesi.createQuery("from Task where task_user_id = '" + id + "'").list(); // List filling
			model.addAttribute("ls", taskarr);
		} catch (Exception e) {
			System.err.println("Data getirme hatasi : " + e);
		}

		return "tasklist";
	}

	@RequestMapping(value = "/addtask", method = RequestMethod.POST)
	public String addTask(HttpServletRequest req, Task task) {
		Session sesi = sf.openSession();
		Transaction tr = sesi.beginTransaction();
		task.setTaskId(Integer.MAX_VALUE);
		sesi.save(task);
		tr.commit();
		sesi.close();

		return "redirect:/tasklist";
	}

}
