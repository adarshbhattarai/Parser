package com.ef;

import com.ef.tasks.TaskRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by adarshbhattarai on 3/11/18.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Parser implements ApplicationRunner{

    @Autowired
    TaskRunner taskRunner;

    public static void main(String[] args){

        SpringApplication application = new SpringApplication(Parser.class);

        application.setBannerMode(Banner.Mode.OFF);

        application.run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        boolean status ;
        try{
         taskRunner.run(args);
         status = taskRunner.getStatus();
        }catch (Exception e){
            status=false;
            e.printStackTrace();
        }

        if(!status)
            printusage();

        System.exit(status?0:1);
    }

    private void printusage() {
        System.out.println(" :: To load data, Usage : java -cp \"parser.jar\" com.ef.Parser --startDate=<yyyy-MM-dd.HH:mm:ss> --duration=<hourly/daily> --threshold=<Integer> --load=<FilePath> :: ");
        System.out.println(" :: Regular Usage: java -cp \"parser.jar\" com.ef.Parser --startDate=<yyyy-MM-dd.HH:mm:ss> --duration=<hourly/daily> --threshold=<Integer> :: " );

    }
}
