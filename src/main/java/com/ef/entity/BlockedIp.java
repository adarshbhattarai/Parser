package com.ef.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by adarshbhattarai on 3/11/18.
 */
@Entity
@Table(name = "BLOCKED_IP")
public class BlockedIp {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data_dt")
    public Timestamp dataDate;
    @Column(name = "ip")
    public String ip;
    @Column(name = "comment")
    public String comment;

    public Timestamp getDataDate() {
        return dataDate;
    }

    public void setDataDate(Timestamp dataDate) {
        this.dataDate = dataDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
