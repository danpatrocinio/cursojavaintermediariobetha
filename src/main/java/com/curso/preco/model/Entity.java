package com.curso.preco.model;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.curso.preco.annotations.IgnoreOnParseable;
import com.curso.preco.exceptions.ParseableException;

public interface Entity {

	public Long getId();

	public Long getVersion();

	public void setId(Long id);

	public void setVersion(Long systemCurrentTimeMillis);

	default Entity fromJson(String json) throws ParseableException {

		if (json == null || json.length() < 5) {
			return null;
		}

		json = json.replace("{", "").replace("}", "");
		String[] fields = json.split(",");
		String fieldName = null;

		Map<String, Object> data = new HashMap<String, Object>();
		for (int i = 0; i < fields.length; i++) {
			Object[] field = fields[i].split(":");
			fieldName = ((String) field[0]).trim();
			fieldName = fieldName.replace("\"", "").replace("\"", "");
			fieldName = fieldName.replace("'", "").replace("'", "");
			data.put(fieldName, field[1]);
		}

		return fromMap(data);
	}

	default Entity fromMap(Map<String, Object> data) throws ParseableException {
		if (data == null || data.isEmpty()) {
			return null;
		}

		try {
			Object value = null;
			for (Field field : getClass().getDeclaredFields()) {
				if (field.isAnnotationPresent(IgnoreOnParseable.class)) {
					continue;
				}
				field.setAccessible(Boolean.TRUE);
				value = data.get(field.getName());

				if (value != null) {
					if (value.toString().trim().equals("null")) {
						value = null;
					} else {
						if (field.getType().equals(String.class)) {
							String aux = ((String) value).trim();
							if (aux.startsWith("\"") || aux.startsWith("'")) {
								value = aux.subSequence(1, aux.length() - 1);
							}
						} else if (field.getType().equals(Integer.class)) {
							value = Integer.parseInt(value.toString().trim());
						} else if (field.getType().equals(Long.class)) {
							value = Long.parseLong(value.toString().trim());
						}
					}
				}

				field.set(this, value);
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ParseableException(e.getMessage(), e);
		}

		return this;
	}

	default Entity fromResultSet(ResultSet rs) throws ParseableException {
		if (rs == null) {
			return null;
		}

		Object value = null;
		for (Field field : getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(IgnoreOnParseable.class)) {
				continue;
			}
			field.setAccessible(Boolean.TRUE);
			try {
				value = rs.getObject(field.getName());
				if (value == null) {
					continue;
				}
				if (field.getType().equals(Integer.class)) {
					value = Integer.parseInt(value.toString().trim());
				} else if (field.getType().equals(Long.class)) {
					value = Long.parseLong(value.toString().trim());
				}

				field.set(this, value);

			} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
				throw new ParseableException(e.getMessage(), e);
			}
		}

		return this;
	}

	default Entity getTableName() {
		return getTableName();
	}

	default String toJson() throws ParseableException {
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
				throw new ParseableException(e.getMessage(), e);
			}
		}
		json.append("}");
		return json.toString();
	}

}
