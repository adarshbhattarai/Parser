package com.ef.repository.impl;

import com.ef.durationType.DurationType;
import com.ef.entity.Argument;
import com.ef.entity.BlockedIp;
import com.ef.entity.LogData;
import com.ef.repository.LoggingDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by adarshbhattarai on 3/11/18.
 */
@Transactional
@Repository
public class LoggingDAOImpl implements LoggingDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveLogToDB(LogData logs){
        entityManager.persist(logs);
    }

    @Override
    public List findBlockedIP(Argument cmdArg) {
        //select ip,count(*)  c from access_logs where startdate between '2017-01-01 00:00:12' and  '2017-01-01 23:00:12' group by ip having c > 100 ;

        return entityManager.createNativeQuery("SELECT ip threshold FROM access_logs " +
                "WHERE startdate BETWEEN :sdate AND :tdate GROUP BY ip HAVING  count(ip) > :thld")
                .setParameter("sdate", cmdArg.getStartDate())
                .setParameter("tdate", new Date(cmdArg.getStartDate().getTime() + TimeUnit.HOURS.toMillis(cmdArg.getDuration()==DurationType.DAILY?24:1)))
                .setParameter("thld",cmdArg.getThreshold())
                .getResultList();

    }

    @Override
    public void persistBlockedIp(BlockedIp blockedIp) {
        entityManager.persist(blockedIp);
    }
}
