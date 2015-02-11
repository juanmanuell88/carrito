package com.globant.carrito.product;

import javax.persistence.*;

import com.globant.carrito.shoppingcart.ShoppingCarts;

public class Product {

	/*
	 * private int id; private String name; private double price; private int
	 * quantity;
	 */

	@GeneratedValue
	@Id
	private int product_id;

	@Column
	private double product_price;

	@Column
	private int product_quantity;

	public int getProduct_quantity() {
		return product_quantity;
	}

	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	private ShoppingCarts cart;
	@ManyToOne(cascade = CascadeType.ALL)
	
	private ProductInStock productinstock;

	
	public ProductInStock getProductInStock() {
		return productinstock;
	}

	
	public Product() {
	}
	
	/*me falto subir otras clases al repo, estaba mal ya que como me comentabas un producto recibe como argumento otro producto, la idea o por lo menos lo que quiero hacer
	 *es sacar los articulos de una gondola (clase ProductInStock) e ir metiendolos en el carrito */
	public Product(double product_price, int product_quantity, ProductInStock productinstock) { 
		this.product_price = product_price;
		this.product_quantity = product_quantity;
		this.productinstock = productinstock;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	/*
	 * public ShoppingCarts getCart() { return cart; }
	 */// no iría

	public void setCart(ShoppingCarts cart) {
		this.cart = cart;
	}

}
