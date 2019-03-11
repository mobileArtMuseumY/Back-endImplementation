/*

package com.artmall.quartz;

import org.quartz.JobDetail;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


*/
/**
 * @author mllove
 * @create 2018-11-30 15:52
 **//*


@Configuration
public class BeanConfig {

    @Autowired
    private JobFactory jobFactory;

    @Bean
    public SchedulerFactoryBean createSchedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(jobFactory);
        return schedulerFactoryBean;
    }

    @Bean
    public JobDetailImpl createJobDetailImpl(){
        return new JobDetailImpl();
    }
}

*/
