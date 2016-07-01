package com.curso.preco.model;

import java.util.function.Consumer;

import com.curso.preco.exceptions.ParseableException;

public class EntityList implements Consumer<Entity> {

	StringBuilder list = new StringBuilder("[");

	@Override
	public void accept(Entity entity) {
		try {
			if (list.length() > 1) {
				list.append(",").append(entity.toJson());
			} else {
				list.append(entity.toJson());
			}
		} catch (ParseableException e) {
			list = new StringBuilder("[]");
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return list.append("]").toString();
	}

}
