package com.ef.entity;

import com.ef.durationType.DurationType;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by adarshbhattarai on 3/11/18.
 *
 * Arguments provided by User when running the application
 */
@Component
public class Argument {

    private Timestamp startDate;
    private DurationType duration;
    private int threshold;
    private String load;

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public DurationType getDuration() {
        return duration;
    }

    public void setDuration(DurationType duration) {
        this.duration = duration;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(String load) {
        this.load = load;
    }
}
