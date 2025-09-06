package murach.cart;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import murach.business.Cart;
import murach.business.LineItem;
import murach.business.Product;
import murach.data.ProductIO;

public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext sc = getServletContext();

        // get current action
        String action = request.getParameter("action");
        if (action == null) action = "cart";  // default action

        // perform action and set URL to appropriate page
        String url = "/index.jsp";

        switch (action) {
            case "shop":
                url = "/index.jsp";
                break;

            case "cart": {
                String productCode = request.getParameter("productCode");
                String quantityString = request.getParameter("quantity");

                HttpSession session = request.getSession();
                Cart cart = (Cart) session.getAttribute("cart");
                if (cart == null) {
                    cart = new Cart();
                }

                // if the user enters a negative or invalid quantity,
                // the quantity is automatically reset to 1.
                int quantity = 1;
                try {
                    if (quantityString != null) {
                        quantity = Integer.parseInt(quantityString);
                        if (quantity < 0) quantity = 1;
                    }
                } catch (NumberFormatException ignore) {
                    quantity = 1;
                }

                String path = sc.getRealPath("/WEB-INF/products.txt");
                Product product = ProductIO.getProduct(productCode, path);

                LineItem lineItem = new LineItem();
                lineItem.setProduct(product);
                lineItem.setQuantity(quantity);

                if (quantity > 0) {
                    cart.addItem(lineItem);
                } else { // quantity == 0 => remove
                    cart.removeItem(lineItem);
                }

                session.setAttribute("cart", cart);
                url = "/cart.jsp";
                break;
            }

            case "checkout":
                url = "/checkout.jsp";
                break;

            default:
                url = "/index.jsp";
        }

        sc.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
