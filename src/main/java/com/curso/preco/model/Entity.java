package com.curso.preco.model;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Date;

import com.curso.preco.annotations.IgnoreOnParseable;

public interface Entity {

	public Long getId();

	public Long getVersion();

	public void setId(Long id);

	public void setVersion(Long systemCurrentTimeMillis);

	default String toJson() {
		StringBuilder json = new StringBuilder("{");
		if (this != null) {
			try {
				Object value = null;
				String name = null;
				boolean useQuote = Boolean.TRUE;

				for (Field field : getClass().getDeclaredFields()) {
					if (field.isAnnotationPresent(IgnoreOnParseable.class)) {
						continue;
					}

					field.setAccessible(Boolean.TRUE);
					value = field.get(this);
					if (value != null) {
						name = field.getName();
						json.append("\"").append(name).append("\"").append(":");

						useQuote = value instanceof java.lang.String || value instanceof Date
						        || value instanceof Timestamp;

						if (useQuote) {
							json.append("\"").append(value.toString()).append("\"").append(",");
						} else {
							json.append(value.toString()).append(",");
						}
					}
				}
				if (json.toString().length() > 1) {
					json.replace(json.length() - 1, json.length(), "");
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		json.append("}");
		return json.toString();
	}

}
