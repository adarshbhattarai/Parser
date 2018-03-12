package com.ef.service.impl;

import com.ef.common.CommonUtil;
import com.ef.entity.Argument;
import com.ef.entity.BlockedIp;
import com.ef.entity.LogData;
import com.ef.repository.LoggingDAO;
import com.ef.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarshbhattarai on 3/11/18.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LoggingDAO loggingDAO;

    @Override
    public void loadDataToDB(String filename) {

        File file = new File(filename);
        BufferedReader br = null;
        List<LogData> logs = new ArrayList<>();
        try{
            br = new BufferedReader(new FileReader(file));
            String line="";
            while ((line = br.readLine()) !=null ) {

                String[] record = line.split("\\|");
                LogData logData = new LogData();
                logData.setStartDate(Timestamp.valueOf(record[0]));
                logData.setIp(record[1]);
                logData.setRequest(record[2]);
                logData.setStatus(Integer.parseInt(record[3]));
                logData.setUseragent(record[4]);
                loggingDAO.saveLogToDB(logData);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List findBlockedIP(Argument cmdArg) {

        return loggingDAO.findBlockedIP(cmdArg);
    }

    @Override
    public void persistBlockedIp(Argument cmdArg, List<Object> logs) {
        //findBlockedIp

        logs.forEach(ip->{
            BlockedIp blockedIp = new BlockedIp();
            blockedIp.setIp(ip.toString());
            blockedIp.setComment(CommonUtil.makeComment(cmdArg));
            blockedIp.setDataDate(new Timestamp(System.currentTimeMillis()));
            loggingDAO.persistBlockedIp(blockedIp);
        });
    }


}
