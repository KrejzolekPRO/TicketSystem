package pl.krejzolekpro.system.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static String parseTime(long time){
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return format.format(date);
    }
}
