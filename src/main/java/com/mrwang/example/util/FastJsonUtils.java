package com.mrwang.example.util;

import java.io.IOException;
import java.util.Collection;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FastJsonUtils {

	static final ObjectMapper jsonMapper = new ObjectMapper();

	static {
		jsonMapper.setSerializationInclusion(Include.NON_NULL);
	}

	public static String toJSONString(Object object) {
		try {
			return jsonMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T toBean(String text, Class<T> clazz) {
		try {
			return jsonMapper.readValue(text, clazz);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把对象转换为指定对象
	 */
	public static <T> T toObjectFromSource(Object source, Class<T> target) {
		return toBean(toJSONString(source), target);
	}

	public static ObjectMapper getJsonMapper() {
		return jsonMapper;
	}

	/**
	 *
	 * @param                 <L> the type parameter
	 * @param                 <E> the type parameter
	 * @param jsonString      the json string
	 * @param collectionClass the collection class
	 * @param elementClass    the element class
	 */
	public static <L extends Collection<E>, E> L fromJson(String jsonString, Class<L> collectionClass,
			Class<E> elementClass) {
		if (StringUtils.hasLength(jsonString)) {
			return null;
		}
		try {
			CollectionType type = jsonMapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
			return jsonMapper.readValue(jsonString, type);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
}
