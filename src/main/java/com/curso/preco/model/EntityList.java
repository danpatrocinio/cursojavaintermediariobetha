package com.curso.preco.model;

import java.util.function.Consumer;

public class EntityList implements Consumer<Entity> {

	StringBuilder list = new StringBuilder("[");

	@Override
	public void accept(Entity e) {
		if (list.length() > 1) {
			list.append(",").append(e.toJson());
		} else {
			list.append(e.toJson());
		}
	}

	@Override
	public String toString() {
		return list.append("]").toString();
	}

}
