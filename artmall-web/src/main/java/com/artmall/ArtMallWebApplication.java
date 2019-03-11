package com.artmall;

import com.artmall.storage.StorageProperties;
import com.artmall.service.StorageService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(scanBasePackages = "com.artmall")

@MapperScan(basePackages = "com.artmall.mapper")
@EnableConfigurationProperties(StorageProperties.class)

@EnableCaching
@EnableScheduling
public class ArtMallWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtMallWebApplication.class, args);
    }

    /**
     * 上传单个图片
     * @param storageService
     * @return
     */
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
//            storageService.deleteAll();
            storageService.init();
        };
    }


}

