package com.test.pos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class ProductCatalogTest {
	
	@Test
	public void firstProductRetrievedShouldBeTheFirstThatWasSetInCatalog(){
		ProductCatalog catalog = new ProductCatalog();
		
		catalog.set(new PstFreeProduct("barcode",new Amount(100),"product"));
		Product retrievedProduct = catalog.get("barcode");
		
		assertThat(retrievedProduct.barcode(),is("barcode"));
	}
	
	@Test
	public void allAddedProductsShouldBeAvailable(){
		ProductCatalog catalog = new ProductCatalog();
		catalog.set(new PstFreeProduct("barcode1",new Amount(100),"product1"));
		catalog.set(new PstFreeProduct("barcode2",new Amount(100),"product2"));
		
		List<Product> allProducts = catalog.items();
		
		assertThat(allProducts.size(),is(2));
	}
	
	@Test(expected = InvalidProductException.class)
	public void exceptionShouldBeRaisedWhenTheProvidedBarcodeDoeNotRepresentAValidProduct(){
		ProductCatalog catalog = new ProductCatalog();
		
		catalog.get("barcode1");		
	}
	
}
