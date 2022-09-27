package com.fast_campus.chap1_clip1;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Chap1Clip1Application {

	public static void main(String[] args) {
		SpringApplication.run(Chap1Clip1Application.class, args);
	}


	@Bean
	public ApplicationRunner runner(KafkaTemplate<String,String> kafkaTemplate)
	{	//키-벨류 타
		//러너는 어플리케이션 뜨고 나서 , 특정 액션을 정의할수 있는 기능입
		return args -> {
			kafkaTemplate.send("quickstart-events","hello-world"); //토픽설정
			//프로듀서가 만들어짐
		};
	}


}
