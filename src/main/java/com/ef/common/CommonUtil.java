package com.ef.common;

import com.ef.entity.Argument;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adarshbhattarai on 3/11/18.
 */
public class CommonUtil {

    //We can get the Format From application Properties as well to make it more generic. But for the scope of this task
    //we will keep it here

    private static String DATETIME_FORMAT ="yyyy-MM-dd.HH:mm:ss";

    public static Timestamp dateToTimeStamp(String date) throws Exception{

        Timestamp timestamp=null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
            Date parsedDate = dateFormat.parse(date);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch(Exception e) {

            throw new IllegalArgumentException("Correct Format Is "+DATETIME_FORMAT);
        }
        return timestamp;

    }

    public static String makeComment(Argument cmdArg) {
        return "Exceeded a threshold of " + cmdArg.getThreshold() +" requests, making request " + cmdArg.getDuration().toString().toLowerCase() + " starting from " +
                cmdArg.getStartDate();
    }
}
