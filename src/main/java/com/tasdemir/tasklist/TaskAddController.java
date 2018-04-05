package com.tasdemir.tasklist;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import utils.HibernateUtil;

@Controller
public class TaskAddController {

	SessionFactory sf = HibernateUtil.getSessionFactory();

	@RequestMapping(value = "/taskadd", method = RequestMethod.GET)
	public String taskAddPage() {
		return "taskadd";
	}
}
