package com.wz.TakeOut;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@MapperScan("com.wz.TakeOut.mapper")
@ServletComponentScan
public class TakeOutApplication {
    public static void main(String[] args) {
        log.info("项目启动成功");
        SpringApplication.run(TakeOutApplication.class,args);
    }
}
