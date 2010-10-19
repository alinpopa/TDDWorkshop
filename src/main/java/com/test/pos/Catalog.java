package com.test.pos;

import java.util.List;

public interface Catalog<T> {

	T get(String itemIdentifier);

	void set(T item);

	List<T> items();
}
