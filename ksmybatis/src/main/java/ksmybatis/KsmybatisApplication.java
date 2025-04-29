package ksmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class KsmybatisApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KsmybatisApplication.class, args);
		log.debug("개발 중에 테스트 값 확인");
	}

}
