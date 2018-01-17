package abnod.webcart.controller;

import abnod.webcart.dao.DBInterface;
import abnod.webcart.dao.MySqlDAO;
import abnod.webcart.model.Cart;
import abnod.webcart.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class MainController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DBInterface dbInterface;

    public void init() {
        dbInterface = new MySqlDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prefix = req.getServletPath();
        try {
            switch (prefix) {
                case "/add": {
                    addToCart(req, resp);
                    break;
                }
                case "/remove": {
                    removeFromCart(req, resp);
                    break;
                }
                case "/order": {
                    order(req, resp);
                    break;
                }
                case "/clear": {
                    clearCart(req, resp);
                    break;
                }
                case "/cart": {
                    getCart(req, resp);
                    break;
                }
                case "/fill": {
                    fillTestProducts(req, resp);
                    break;
                }
                default: {
                    productList(req, resp);
                }
            }
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void productList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void getCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = getCart(req, session);
        session.setAttribute("cart", cart);
        RequestDispatcher dispatcher = req.getRequestDispatcher("cart.jsp");
        dispatcher.forward(req, resp);
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = getCart(req, session);
        Product product = dbInterface.getProduct(Integer.parseInt(req.getParameter("id")));
        cart.addToCart(product);
        session.setAttribute("cart", cart);
        resp.sendRedirect("/");
    }

    private void removeFromCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = getCart(req, session);
        Product product = dbInterface.getProduct(Integer.parseInt(req.getParameter("id")));
        cart.removeFromCart(product);
        session.setAttribute("cart", cart);
        resp.sendRedirect("/cart");
    }

    private void order(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = getCart(req, session);
        int orderid = dbInterface.createOrder(cart, req.getParameter("name"), req.getParameter("surname"), req.getParameter("phone"));
        if (orderid > 0) {
            session.removeAttribute("cart");
            req.setAttribute("orderid", orderid);
            RequestDispatcher dispatcher = req.getRequestDispatcher("success.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/cart");
        }
    }

    private void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("cart");
        resp.sendRedirect("/cart");
    }

    private Cart getCart(HttpServletRequest request, HttpSession session) {
        Cart cart;
        Object cartBean = session.getAttribute("cart");
        if (cartBean == null) {
            cart = new Cart();
        } else {
            cart = (Cart) cartBean;
        }
        return cart;
    }
}
