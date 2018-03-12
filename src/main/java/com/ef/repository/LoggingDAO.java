package com.ef.repository;

import com.ef.entity.Argument;
import com.ef.entity.BlockedIp;
import com.ef.entity.LogData;

import java.util.List;

/**
 * Created by adarshbhattarai on 3/11/18.
 */
public interface LoggingDAO {
    public void saveLogToDB(LogData logs);

    List findBlockedIP(Argument cmdArg);

    public void persistBlockedIp(BlockedIp blockedIp);
}
