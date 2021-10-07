package com.singleentity.app.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.singleentity.app.entity.CandyDetails;
import com.singleentity.app.util.EntityManagerUtil;

/**
 * arugji 
 * CIS175 fall 2021
 * Oct 5
 */
@WebServlet("/ViewAllNameServlet")
public class ViewAllNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewAllNameServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		entityManager.getTransaction().begin();

		@SuppressWarnings("unchecked")
		List<CandyDetails> list = (List<CandyDetails>) entityManager.createQuery("SELECT u FROM CandyDetails u")
				.getResultList();

		entityManager.getTransaction().commit();

		request.setAttribute("candyList", list);

		String path = "/list.jsp";

		if (list.isEmpty()) {
			path = "/index.jsp";
		}

		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
