package com.shopping.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.domain.*;
import com.shopping.service.*;


public class ShoppingController extends HttpServlet {

	private static final long serialVersionUID = 5511500590069298630L;

	private ItemService itemService;
	private LoginService login;
	private HttpSession session;
	private CartService cartService;
	private SummaryService summaryService;
	private CheckoutService checkoutService;
	private Map<Item, Integer> cartItemPrice;
	
	private List<Item> cart = new ArrayList<>();
	
	@Override
	public void init() throws ServletException {
		login = new LoginService();
		itemService = new ItemService();
		cartService = new CartService();
		summaryService = new SummaryService();
		checkoutService = new CheckoutService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String page = request.getParameter("page");
		String action = request.getParameter("action");
		String nextPage = "/login.jsp";

// Login
		
		if(page.equalsIgnoreCase("login")) 
		{
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			if(!(username.equals("") || password.equals(""))) 
			{
				boolean isValid = false;
				try {
					isValid = login.authenticate(username, password);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				System.out.println(isValid);
				if (isValid) 
				{
					nextPage = "/item.jsp";
					request.setAttribute("addtocartmessage", "Add to cart");
					List<Item> itemList = cartService.fetchAllItems();
					checkoutService.setItemList(itemList);
					request.setAttribute("itemlist", itemList);
					session=request.getSession(true);
					cartItemPrice = new HashMap<Item, Integer>();
				}
				else 
				{
					request.setAttribute("invalidUser", "Invalid username or password.");
				}
			}
			else 
			{
				nextPage = "/login.jsp";
				request.setAttribute("invalidUser", "Username or Password cannot be blank.");
			}
		}
		
// Done
		
		else if(page.equalsIgnoreCase("item")) 
		{
				if(action.equalsIgnoreCase("Add to cart")) 
				{
					session.removeAttribute("cartitemprice");
					session.removeAttribute("carttotalprice");
//					session.removeAttribute("item");
					String [] selectedItems = request.getParameterValues("chkItem");
					Map<Integer, Integer> mapIdQuantity = new HashMap<Integer, Integer>();
					
					if(selectedItems!=null && selectedItems.length>0) {
					for(String selectedItem:selectedItems){
						
						int quantity=Integer.parseInt(request.getParameter("qty"+selectedItem));
						mapIdQuantity.put(Integer.parseInt(selectedItem), quantity);
					}
					checkoutService.setMapIdQuantity(mapIdQuantity);
					cartItemPrice = checkoutService.getMapItemPrice();
					int carttotalPrice = checkoutService.getTotalPrice();
					
					request.setAttribute("itemlist", cartService.getItemList());
					request.setAttribute("itemsaddedmessage", selectedItems.length + " product added in cart");
					
					session.setAttribute("cartitemprice", cartItemPrice);
					session.setAttribute("carttotalprice", carttotalPrice);
					
					nextPage="/item.jsp";
					}
					else {
						request.setAttribute("itemlist", checkoutService.getItemList());
						request.setAttribute("itemsaddedmessage", "The cart is empty");
						nextPage = "/item.jsp";
					}
				}
				
					else if(action.equalsIgnoreCase("Checkout")) 
					{
						String[] selectedItemIDs=request.getParameterValues("chkItem");
						if(session.getAttribute("carttotalprice")!=null) {
							nextPage = "/summary.jsp";
						}
						else {
							try {
								int i = selectedItemIDs.length + 1;
								nextPage = "/summary.jsp";
								
							}
							catch (NullPointerException e) {
								request.setAttribute("itemlist", checkoutService.getItemList());
								request.setAttribute("itemsaddedmessage", "The cart is empty");
								nextPage = "/item.jsp";
							}
						}
					}
		}
		
		else if (page.equalsIgnoreCase("summary")) {
			if(action.equalsIgnoreCase("Checkout")) {
				session.removeAttribute("cartitemprice");
				session.removeAttribute("carttotalprice");
				nextPage = "/thankyou.jsp";
			}
			else if (action.equalsIgnoreCase("Back To Cart")) {
				request.setAttribute("itemlist", checkoutService.getItemList());
				request.setAttribute("itemsaddedmessage", "The cart is empty");
				nextPage = "/item.jsp";
			}
		}
		
		else if (page.equalsIgnoreCase("menu")) {
			if(action.equalsIgnoreCase("Help")) {
				nextPage = "/help.jsp";
			}
			else if (action.equalsIgnoreCase("Logout")) {
				session.invalidate();
				nextPage = "/login.jsp";
			}
		}
		
		else if (page.equalsIgnoreCase("help")) {
			if(action.equalsIgnoreCase("Back To Cart")) {
				request.setAttribute("itemlist", checkoutService.getItemList());
				nextPage = "/item.jsp";
			}
		}
		
		else if(page.equalsIgnoreCase("thankyou")) {
			if(action.equalsIgnoreCase("Logout")) {
			session.invalidate();
			nextPage = "/login.jsp";
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		
	}

	@Override
	public void destroy() {

	}

}
