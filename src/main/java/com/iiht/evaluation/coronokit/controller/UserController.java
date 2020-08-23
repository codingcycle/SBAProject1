package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.OrderSummary;
import com.iiht.evaluation.coronokit.model.ProductMaster;
import com.iiht.evaluation.coronokit.model.User;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KitDao kitDAO;
	private ProductMasterDao productMasterDao;

	public void setKitDAO(KitDao kitDAO) {
		this.kitDAO = kitDAO;
	}

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");

		this.kitDAO = new KitDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		String viewName = "";
		try {
			switch (action) {
			case "newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "showproducts":
				viewName = showAllProducts(request, response);
				break;
			case "addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "showkit":
				viewName = showKitDetails(request, response);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "saveorder":
				viewName = saveOrderForDelivery(request, response);
				break;
			case "ordersummary":
				viewName = showOrderSummary(request, response);
				break;
			default:
				viewName = "notfound.jsp";
				break;
			}
		} catch (Exception ex) {

			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);

	}

	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) {
		String view = "";
		OrderSummary orderSummary = new OrderSummary();
		HttpSession session = request.getSession();
		orderSummary = (OrderSummary) session.getAttribute("kitdetails");
		orderSummary.getKitDetails();
		view = "ordersummary.jsp";
		return view;
	}

	private String saveOrderForDelivery(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String view = "";
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		ProductMaster existingProduct = productMasterDao.getProduct(id);
		List<Integer> selectedProductIds = (List<Integer>) session.getAttribute("selectedproductids");
		if (selectedProductIds == null) {
			selectedProductIds = new ArrayList<Integer>();
		}
		selectedProductIds.add(existingProduct.getId());
		view = "showproductstoadd.jsp";
		return view;
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) {
		String view = "";
		int id = (int) request.getAttribute("id");
		
		return "";
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) {

		return "";
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String view = "";
		List<ProductMaster> listAllProducts = productMasterDao.listAllProducts();
		request.setAttribute("listProducts", listAllProducts);
		view = "listproducts.jsp";
		return view;
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String view = "";
		String userName = request.getParameter("userName");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("useremail");

		User user = new User(userName, mobile, email);
		kitDAO.insertUser(user);
		request.setAttribute("Message", "user added succcessfully");
		view = "listproducts.jsp";
		return view;
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String view = "newuser.jsp";
		return view;
	}
}