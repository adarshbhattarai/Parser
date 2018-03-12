package com.ef.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by adarshbhattarai on 3/11/18.
 */
@Entity
@Table(name = "access_logs")
public class LogData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "ip")
    private String ip;
    @Column(name = "startdate")
    private Timestamp startDate;
    @Column(name = "request")
    private String request;
    @Column(name = "status")
    private int status;
    @Column(name = "useragent")
    private String useragent;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }
}


/*
*
*
* create table `access_logs`(

`id` bigint unsigned AUTO_INCREMENT,
`startdate` timestamp not null,
`ip` varchar(20) not null,
`request` varchar(50) not null,
`status` int not null,
`useragent` varchar(100) not null,
primary key (`id`)
)

*/