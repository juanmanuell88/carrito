package com.globant.carrito.product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.globant.carrito.StatusDto;
import com.globant.carrito.shoppingcart.ShoppingCarts;

@RestController
public class ProductService {

	protected EntityManagerFactory emf;

	public ProductService() {
		this.emf = Persistence.createEntityManagerFactory("dba");
	}

	@RequestMapping(value = "/service/newProduct", method = RequestMethod.GET)
	@ResponseBody
	public StatusDto nnewProduct(@PathVariable("prodId") Integer prodId,
			HttpSession session) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			ShoppingCarts cart = getCart(session, em);
			boolean found = false;
			for (Product i : cart.getItems()) {
				if (i.getProduct().getProduct_id() == (prodId)) {
					i.setProduct_quantity(i.getProduct_quantity() + 1);
					found = true;
					break;
				}
			}
			if (!found) {
				Product product = em.find(Product.class, prodId);
				cart.addItem(new Product(product.getProduct_price() , 1, product));
			}
			em.persist(cart);
			tx.commit();
			return new StatusDto(true);
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			return new StatusDto(false);
		} finally {
			em.close();
		}
	}

	@RequestMapping(value = "/service/findProduct", method = RequestMethod.GET)
	@ResponseBody
	public Product findProduct(String name) {
		EntityManager em = emf.createEntityManager();
		Product p = em.find(Product.class, name);
		em.close();
		return p;
	}

	@RequestMapping(value = "/service/removeProduct", method = RequestMethod.GET)
	@ResponseBody
	public StatusDto removeProduct(@PathVariable("prodId") Integer prodId,
			HttpSession session) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			ShoppingCarts cart = getCart(session, em);
			for (Product i : cart.getItems()) {
				if (i.getProduct().getProduct_id() == (prodId) && i.getProduct_quantity() > 0) {
					i.setProduct_quantity(i.getProduct_quantity() - 1);
					break;
				}
			}
			em.persist(cart);
			tx.commit();
			return new StatusDto(true);
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			return new StatusDto(false);
		} finally {
			em.close();
		}
	}
	
	
	@RequestMapping(value = "/service/checkout", method = RequestMethod.GET)
	@ResponseBody
	public StatusDto checkOut(HttpSession session) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			ShoppingCarts cart = getCart(session, em);
			cart.setStatus(false);
			em.persist(cart);
			tx.commit();
			return new StatusDto(true);
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			return new StatusDto(false);
		} finally {
			em.close();
		}
	}
	
	
	private ShoppingCarts getCart(HttpSession session, EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

}
