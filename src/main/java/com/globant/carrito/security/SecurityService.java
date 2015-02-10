package com.globant.carrito.security;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.globant.carrito.StatusDto;

@RestController
public class SecurityService {
	public static final String USR_NAME = "usr_name";

	@RequestMapping(value = "/service/login", method = RequestMethod.POST)
	@ResponseBody
	public StatusDto login(@RequestBody LoginDto dto, HttpSession session) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("dba");

		EntityManager em = emf.createEntityManager();

		String usr_name = dto.getUsername();

		try {
			TypedQuery<User> query = em.createQuery("from User c where c.usr_Name = :usr_Name",User.class);
			query.setParameter("usr_Name", usr_name);
			User user = (User) query.getSingleResult();
			boolean isValid = dto.getPassword().equals(user.getPassword());
			if (isValid) {
				session.setAttribute(USR_NAME, usr_name);
			}
			return new StatusDto(isValid);
		} catch (NoResultException e) {
			return new StatusDto(false);
		} finally {		
			em.close();
		}
	}

	@RequestMapping(value = "/service/logout", method = RequestMethod.POST)
	public void logout(HttpSession session) {
		session.invalidate();
	}
}
