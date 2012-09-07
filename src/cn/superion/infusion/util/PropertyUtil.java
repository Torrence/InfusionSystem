package cn.superion.infusion.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	
	public static Properties getNetConfigProperties() {
		Properties props = new Properties();
		InputStream in = PropertyUtil.class.getResourceAsStream("/netconfig.properties");
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
}
