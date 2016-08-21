package com.orbit.configs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SystemConfig {

	private static Logger logger = LogManager.getLogger(SystemConfig.class);

	private static Properties properties;

	public static String getProperty(String key) {
		if (properties == null) {
			properties = new Properties();
			try {
				InputStream in = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("system.properties");
				properties.load(in);
			} catch (IOException e) {
				logger.debug(e.getMessage(), e);
			}
		}
		return properties.getProperty(key);
	}

	public static String getDatabaseDriver(){
		return getProperty("db.connect.driver");
	}

	public static String getDatabaseUrl(){
		return getProperty("db.connect.url");
	}

	public static String getDatabaseUsername(){
		return getProperty("db.connect.username");
	}

	public static String getDatabasePassword(){
		return getProperty("db.connect.password");
	}

	public static Integer getSystemCommonListPageSize(){
		return Integer.valueOf(getProperty("stage.common.list.pageSize"));
	}

	public static String getUploadDir(){
		return getProperty("upload.dir");
	}
}
