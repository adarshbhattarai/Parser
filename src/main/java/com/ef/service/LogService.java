package com.ef.service;

import com.ef.entity.Argument;
import com.ef.entity.LogData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by adarshbhattarai on 3/11/18.
 */
@Service
public interface LogService {
    public void loadDataToDB(String file);

    List findBlockedIP(Argument cmdArg);

    void persistBlockedIp(Argument cmdArg, List<Object> logs);
}
