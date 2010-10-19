package com.test.pos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCatalog implements Catalog<Product>{

	private final Map<String, Product> catalogProducts = new HashMap<String, Product>();
	
	@Override
	public Product get(final String barcode) {
		if(!catalogProducts.containsKey(barcode)){
			throw new InvalidProductException();
		}
		return catalogProducts.get(barcode);
	}

	@Override
	public void set(final Product product) {
		catalogProducts.put(product.barcode(), product);
	}

	@Override
	public List<Product> items() {
		return Collections.unmodifiableList(new ArrayList<Product>(catalogProducts.values()));
	}

}
