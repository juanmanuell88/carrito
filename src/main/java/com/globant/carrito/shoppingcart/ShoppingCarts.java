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



public class ShoppingCarts { //es un carrito solo asi que iría en singular habria que cambiarlo 


	@Id
	@GeneratedValue
	private int cartId;
	@ManyToOne(cascade = CascadeType.ALL)
	private User client;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Product> items;
	@Column
	private Date date; // el campo date es la fecha del carrito
	@Column
	private double cartcost;
	@Column
	private boolean status; /*el campo status, es un boolean que indica si el carrito está abierto o no. 
	                         * Cuando el usuario inicia sesión, en caso de tener algún carrito abierto, le carga lo que tiene en el carrito y sigue utilizando el mismo hasta 
	                         * que se cierre, caso que esté cerrado, inicia uno nuevo */
	
	
	
	public ShoppingCarts() {
	}

	public ShoppingCarts(User client) {
		this.client = client;
		items = new HashSet<Product>();
		status = true;
	}

	public ShoppingCarts(Product... initialItems) {
		items = new HashSet<Product>();
		for (Product item : initialItems) {    //Va colocando los items (product) en el hash
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
	
	/* esto puede ser un cuestión de performance, si bien es un dato que puede calcularse, pero supongamos el caso de tener                                         
	 * carritos con varios productos, cada vez que consultes el total tiene que recorrerlo una y otra vez... en cambio con sólo 
	* agregarle un atributo más (cosa que no baja mucho la performance) ya no es necesario recorrerlo cada vez que necesito saber                                      
	* * el total de cada carrito */

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



