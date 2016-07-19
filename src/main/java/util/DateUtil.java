package util;

import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

    public static Date subtractFromCurrentTime(int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -minutes);
        return calendar.getTime();
    }

    private DateUtil() {
    }
}
