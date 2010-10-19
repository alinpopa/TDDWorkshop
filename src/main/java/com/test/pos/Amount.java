package com.test.pos;

public class Amount {
	private final int value;

	public Amount(final int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}

	public Amount plus(Amount price) {
		return new Amount(price.value() + value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Amount other = (Amount) obj;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return value + "";
	}
}
