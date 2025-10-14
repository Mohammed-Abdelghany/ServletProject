package com.project.controller;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;

import com.project.model.Item;
import com.project.service.ItemServiceImp;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
	@WebServlet("/itemservlet")	
public class ItemServlet extends HttpServlet  {
	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/connection")
	private DataSource dataSource;
	private ItemServiceImp itemService;
	@Override
	    public void init() throws ServletException {
	        itemService = new ItemServiceImp(dataSource);
	    }
	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException   {
//		PrintWriter print =response.getWriter();
		String action = request.getParameter("action");
		if (Objects.isNull(action)) {
			action = "show-items";
		}
		switch (action) {
		case "show-items":
			showItems(request, response);
			break;
		case "update-item":
			updateItem(request, response);
			break;
		case "delete-item":
			deleteItem(request, response);
			break;
		case "show-item":
			showItem(request, response);
			break;
		case "add-item":
			addItem(request, response);
			break;
		case "edit-item":
			editItem(request, response);
			break;

		default:
			showItems(request, response);
			break;
		}
	}

	private void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String price_parm=request.getParameter("price");
		String total_number_parm=request.getParameter("total_number");
	
				try {
				    Double price=Double.parseDouble(price_parm);
					 int total_number=Integer.parseInt(total_number_parm);
						Item item=new Item(total_number,price,name);
				    if(itemService.addItem(item)) {
				    request.setAttribute("item", item);
				    request.setAttribute("message", "✅ Created successfully");
					response.sendRedirect(request.getContextPath() + "/itemservlet");
				    }
				    } catch (NumberFormatException e) {
				    request.setAttribute("errors", "error in inputs field");
			        request.getRequestDispatcher("/CrudItemsProject/add-item.jsp").forward(request, response);

				}
	}

	private void showItem(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
	String parm= request.getParameter("id");

	try {
	    Long id = Long.parseLong(parm);
	    Item item=itemService.showItem(id);
	    if(item!=null)
	    request.setAttribute("item", item);
        request.getRequestDispatcher("/CrudItemsProject/show-item.jsp").forward(request, response);

	} catch (NumberFormatException e) {
		e.printStackTrace(); 
	}

	}

	private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_parm=request.getParameter("id");
		try {
		    Long id = Long.parseLong(id_parm);
		    if(itemService.deleteItem(id)) {
		response.sendRedirect(request.getContextPath() + "/itemservlet");
		    }
		    } catch (NumberFormatException e) {
				e.printStackTrace(); 
		}

	}

	private void updateItem(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			String id_parm=request.getParameter("id");
			String name=request.getParameter("name");
			String price_parm=request.getParameter("price");
			String total_number_parm=request.getParameter("total_number");
			
					try {
					    Long id = Long.parseLong(id_parm);
					    Double price=Double.parseDouble(price_parm);
						 int total_number=Integer.parseInt(total_number_parm);
							Item item=new Item(id,total_number,price,name);
					    if(itemService.updateItem(item)) {
					    	request.setAttribute("message", "✅ Updated successfully");
					    	request.setAttribute("item", item);
				        request.getRequestDispatcher("/CrudItemsProject/show-item.jsp").forward(request, response);
					    }
					    } catch (NumberFormatException e) {
					    	 request.setAttribute("errors", "error in inputs field");
						        request.getRequestDispatcher("/CrudItemsProject/update-item.jsp").forward(request, response);
						
					}

		 
	}
	private void showItems(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		List<Item>items=itemService.showItems();
	request.setAttribute("items", items);
	  try {
	        request.getRequestDispatcher("/CrudItemsProject/index.jsp").forward(request, response);
	    } catch (ServletException e) {
	       e.printStackTrace();
	       
	    }
	
	}
	private void editItem(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String parm= request.getParameter("id");

		try {
		    Long id = Long.parseLong(parm);
		    Item item=itemService.showItem(id);
		    if(item!=null)
		    request.setAttribute("item", item);
	        request.getRequestDispatcher("/CrudItemsProject/update-item.jsp").forward(request, response);

		} catch (NumberFormatException e) {
			e.getMessage();
		}

		}

	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	



}
