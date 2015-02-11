package com.globant.carrito.product;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductInStockService {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");

	@RequestMapping(value = "/service/getProducts", method = RequestMethod.GET)
	@ResponseBody
	public ProductHistoryFeedback getProducts() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Product> query = em.createQuery("select p from Product p",
				Product.class);
		ProductHistoryFeedback response = new ProductHistoryFeedback();
		for (Product p : query.getResultList()) {
			response.getResults().add(p);
		}
		// response.getResults().add(new Product(123, "Coca cola", 15.5));
		// response.getResults().add(new Product(456, "Fernet", 87.30));
		return response;
	}
}

