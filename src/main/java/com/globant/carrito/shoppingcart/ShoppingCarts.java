package com.globant.carrito.shoppingcart;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import com.globant.carrito.product.Product;

public class ShoppingCarts {

	@Id
	@GeneratedValue
	private int cartId;
	@ManyToOne(cascade = CascadeType.ALL)
	private User client;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Product> items;
	@Column
	private Date date;
	@Column
	private double cartcost;
	@Column
	private boolean status;
	
	
	
	public ShoppingCarts() {
	}

	public ShoppingCarts(User client) {
		this.client = client;
		items = new HashSet<Product>();
		status = true;
	}

	public ShoppingCarts(Product... initialItems) {
		items = new HashSet<Product>();
		for (Product item : initialItems) {
			addItem(item);
		}
	}

	public void addItem(Product item) {
		items.add(item);
		// Esto es necesario para que cargue la "clave foranea"
		item.setCart(this);
	}

	public Set<Product> getItems() {
		return items;
	}

	public void setItems(Set<Product> items) {
		this.items = items;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getCartPrice() {
		return cartcost;
	}

	public void setCartPrice(double cartcost) {
		this.cartcost = cartcost;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getCartId() {
		return cartId;
	}
	
}
