package com.codegym.controller;

import com.codegym.dao.ProductDAO;
import com.codegym.model.Product;
import com.codegym.service.IProductService;
import com.codegym.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    private IProductService productService;

    public ProductServlet() {
        this.productService = new ProductService(new ProductDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
               RequestDispatcher dispatcher=request.getRequestDispatcher("/product/create.jsp");
               dispatcher.forward(request,response);
            }
            case "edit":{
                int id= Integer.parseInt(request.getParameter("id"));
                Product product=productService.findById(id);
                request.setAttribute("product",product);
                RequestDispatcher dispatcher= request.getRequestDispatcher("/product/edit.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "delete":{
                int id = Integer.parseInt(request.getParameter("id"));
                Product product = productService.findById(id);
                request.setAttribute("product", product);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/product/delete.jsp");
                dispatcher.forward(request, response);
            }
            case "view":{
                int id = Integer.parseInt(request.getParameter("id"));
                Product product = productService.findById(id);
                request.setAttribute("product", product);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/product/view.jsp");
                dispatcher.forward(request, response);
            }
            default:
                List<Product> products = productService.findAll();
                String q = request.getParameter("q");
                if (q != null) {
                    products = productService.findAllByName(q);
                }
                request.setAttribute("products", products);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/product/list.jsp");
                dispatcher.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                createProduct(request, response);
                break;
            }
            case "delete":{
                int id = Integer.parseInt(request.getParameter("id"));
                productService.delete(id);
                response.sendRedirect("/products");
                break;
            }
            case "edit":{
                int id= Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                double price = Double.parseDouble(request.getParameter("price"));
                double quantity = Double.parseDouble(request.getParameter("quantity"));
                String color = request.getParameter("color");
                String description = request.getParameter("description");
                String category = request.getParameter("category");
                Product product = new Product(name, price, quantity, color, description, category);
                boolean isUpdate= productService.update(id,product);
                String message = "";
                if (isUpdate) {
                    message="1";
                    request.setAttribute("message",message);
                    RequestDispatcher dispatcher=request.getRequestDispatcher("/product/edit.jsp");
                    dispatcher.forward(request,response);
                } else {
                    message="2";
                    request.setAttribute("message",message);
                    RequestDispatcher dispatcher=request.getRequestDispatcher("/product/edit.jsp");
                    dispatcher.forward(request,response);
                }
                break;
            }
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        double quantity = Double.parseDouble(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        Product product = new Product(name, price, quantity, color, description, category);
        productService.create(product);
        response.sendRedirect("/products");
    }
}
