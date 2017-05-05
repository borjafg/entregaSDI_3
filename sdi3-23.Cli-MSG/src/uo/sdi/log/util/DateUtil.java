package uo.sdi.log.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String format(Date fecha) {
	try {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

	    return sdf.format(fecha);
	}

	catch (Exception ex) {
	    return null;
	}
    }
}