package abnod.webcart.controller;

import abnod.webcart.dao.DBInterface;
import abnod.webcart.dao.MySqlDAO;
import abnod.webcart.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DBInterface dbInterface;

    public void init(){
        dbInterface = new MySqlDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prefix = req.getServletPath();
        try{
            switch (prefix){
                case "/cart":{

                }
                case "/fill":{
                    fillTestProducts(req, resp);
                    break;
                }
                default:{
                    productlist(req, resp);
                    break;
                }
            }
        } catch (IOException e){
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void productlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> pr = dbInterface.getProducts();
        req.setAttribute("products", pr);
        RequestDispatcher dispatcher = req.getRequestDispatcher("productlist.jsp");
        dispatcher.forward(req, resp);
    }

    private void fillTestProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dbInterface.fillTestTable();
        req.setAttribute("products", dbInterface.getProducts());
        resp.sendRedirect("/");
    }

    private void getCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("cart.jsp");
        dispatcher.forward(req, resp);
    }
}
