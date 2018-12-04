package com.laiba.wash.core.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;

public class JsonUtil {
	private final static Logger log = Logger.getLogger(JsonUtil.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
					JsonProcessingException {
				jgen.writeString("");
			}
		});

		mapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * obj转json
	 * 
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		if (object == null) {
			return "";
		}
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			log.warn(e.getMessage());
		} catch (JsonMappingException e) {
			log.warn(e.getMessage());
		} catch (IOException e) {
			log.warn(e.getMessage());
		}
		log.warn("toJson error...");
		return "";
	}

	/**
	 * json字符串转换成java对象 针对简单对象
	 * 
	 * @param json
	 * @param clzss
	 * @return
	 * @throws Exception
	 */
	public static <T> T jsonToObject(String json, Class<T> clzss) throws Exception {
		T obj = null;
		obj = mapper.readValue(json, clzss);
		return obj;
	}

	/**
	 * json字符串转换成java对象 针对复杂List
	 * 
	 * @param json
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 * @throws Exception
	 */
	public static <C, E> C jsonToListObject(String json, Class<C> collectionClass, Class<E> elementClasses)
			throws Exception {
		JavaType type = getCollectionType(collectionClass, elementClasses);
		C obj = mapper.readValue(json, type);
		return obj;
	}

	/**
	 * json字符串转换成java对象 针对复杂map
	 * 
	 * @param json
	 * @param m
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static <M, K, V> M jsonToMapObject(String json, Class<M> m, Class<K> key, Class<V> value) throws Exception {
		JavaType type = getCollectionType(m, key, value);
		M obj = mapper.readValue(json, type);
		return obj;
	}

	/**
	 * 获取泛型的Collection Type
	 * 
	 * @param collectionClass
	 *            泛型的Collection
	 * @param elementClasses
	 *            元素类
	 * @return JavaType Java类型
	 * @since 1.0
	 */
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
}
