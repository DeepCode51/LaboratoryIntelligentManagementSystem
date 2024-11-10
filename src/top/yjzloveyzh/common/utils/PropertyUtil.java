package top.yjzloveyzh.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import top.yjzloveyzh.common.Constants;

public class PropertyUtil {

    private static Properties properties = null;

    static {
        InputStream in = null;

        try {
            in = PropertyUtil.class.getClassLoader().getResourceAsStream(Constants.General.PROPERTY_APP_FILE_NAME);
            properties = new Properties();
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getRequestBuyRecordPerPageCount() {
        String count = properties.getProperty(Constants.Properties.PROPERTIES_KEY_REQUEST_BUY_RECORD_PER_PAGE_COUNT);

        return count;
    }
}
