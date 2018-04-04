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

import model.Task;
import utils.HibernateUtil;

@Controller
public class TaskController {

	SessionFactory sf = HibernateUtil.getSessionFactory();

	@RequestMapping(value = "/tasklist", method = RequestMethod.GET)
	public String tasklist(Model model, HttpServletRequest req) {
		int id = (Integer) req.getSession().getAttribute("id"); // Standart user id
		System.out.println("id : " + id);
		Session sesi = sf.openSession();
		List<Task> taskarr = new ArrayList<Task>();
		try {
			taskarr = sesi.createQuery("from Task where task_user_id = '" + id + "'").list();
			model.addAttribute("ls", taskarr);
		} catch (Exception e) {
			System.err.println("Data getirme hatasi : " + e);
		}

		return "tasklist";
	}

	/*
	 * @RequestMapping(value = "/tasklist", method = RequestMethod.POST) public
	 * String tasklist(Model model, HttpServletRequest req) { int id= (Integer)
	 * req.getSession().getAttribute("id"); //Standart user id
	 * System.out.println("id : "+id); Session sesi = sf.openSession(); List<Task>
	 * taskarr = new ArrayList<Task>(); try { taskarr =
	 * sesi.createQuery("from Task where task_user_id = '" + id +"'").list();
	 * model.addAttribute("ls", taskarr); } catch (Exception e) {
	 * System.err.println("Data getirme hatasi : "+e); }
	 * 
	 * 
	 * return "tasklist"; }
	 */

}
