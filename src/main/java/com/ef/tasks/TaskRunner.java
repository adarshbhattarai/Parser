package com.ef.tasks;

import com.ef.common.CommonUtil;
import com.ef.durationType.DurationType;
import com.ef.entity.Argument;
import com.ef.entity.BlockedIp;
import com.ef.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by adarshbhattarai on 3/11/18.
 */
@Component
public class TaskRunner {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    LogService logService;

    ApplicationArguments arguments;

    boolean success = true;

    public void run(ApplicationArguments arguments)  {

        this.arguments = arguments;

        //If load is an option. It will only try to load the file data to DB.
        if(arguments.containsOption("load"))
        {
            String file =  arguments.getOptionValues("load").get(0);
            System.out.println(" :: Loading the log into DB :: ");
            logService.loadDataToDB(file);

        }
        else
        {
           Argument cmdArg = initializeArguments();

           //Proceed only if the 3 parameters with correct values are provided.
           if(cmdArg.getDuration()!=null && cmdArg.getThreshold()>0 && cmdArg.getStartDate()!=null)
           {
               List<Object> logs = logService.findBlockedIP(cmdArg);
               System.out.println(" :: List of blocked IP's making requests more than given threshold " +cmdArg.getThreshold() +" ::");
               logs.forEach(ip->{System.out.println(ip);});
               System.out.println(" :: Saving the Blocked IP into BLOCKED_IP table :: ");
               logService.persistBlockedIp(cmdArg,logs);


           }
           else
           {
               this.success=false;
           }
        }


    }

    private Argument initializeArguments() {

        Argument argsEntity = applicationContext.getBean(Argument.class);

        if(arguments.containsOption("startDate"))
        {
            String date = arguments.getOptionValues("startDate").get(0);
            try {
                Timestamp startDate = CommonUtil.dateToTimeStamp(date);
                argsEntity.setStartDate(startDate);
            }catch (Exception e){
                this.success=false;
                throw new RuntimeException("TimeStamp format is not correct. see usage");
            }

        }
        if(arguments.containsOption("duration"))
        {
            String duration = arguments.getOptionValues("duration").get(0);
            if(duration.equalsIgnoreCase("hourly"))
            {
                argsEntity.setDuration(DurationType.HOURLY);
            }
            if(duration.equalsIgnoreCase("daily"))
            {
                argsEntity.setDuration(DurationType.DAILY);
            }
        }
        if(arguments.containsOption("threshold"))
        {
            String threshold = arguments.getOptionValues("threshold").get(0);
            try {
                argsEntity.setThreshold(Integer.parseInt(threshold));
            }catch (Exception e){
                this.success=false;
                throw new RuntimeException("Threshold should be an Integer");
            }

        }
        return argsEntity;
    }

    public boolean getStatus(){
        return  success;
    }
}
