package com.test.pos;

public interface EventHandler<T> {
	void handle(T event);
}
