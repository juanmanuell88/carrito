package com.globant.carrito.user;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserService {

	protected EntityManagerFactory emf;

	public UserService() {
		this.emf = Persistence.createEntityManagerFactory("dba");
	}

	@RequestMapping(value = "/service/getUser", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(String usr_Name) {
		EntityManager em = emf.createEntityManager();
		User c = em.find(User.class, usr_Name);
		em.close();
		return c;
	}

	@RequestMapping(value = "/service/newUser", method = RequestMethod.GET)
	@ResponseBody
	public User newUser(String usr_name, String usr_pass, String usr_mail) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			User emc = new User( usr_name, usr_pass, usr_mail);
			em.persist(emc);
			tx.commit();
			return emc;
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}

	@RequestMapping(value = "/service/removeUser", method = RequestMethod.GET)
	@ResponseBody
	public void removeUser(User client) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(client);
			em.persist(client);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}
}
