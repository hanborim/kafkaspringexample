package com.fast_campus.chap1_clip1;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
public class Chap1Clip1Application {

	public static void main(String[] args) {
		SpringApplication.run(Chap1Clip1Application.class, args);
	}


//	@Bean
//	public ApplicationRunner runner(KafkaTemplate<String,String> kafkaTemplate)
//	{	//키-벨류 타
//		//러너는 어플리케이션 뜨고 나서 , 특정 액션을 정의할수 있는 기능입
//		return args -> {
//			kafkaTemplate.send("quickstart-events","hello-world"); //토픽설정
//			//프로듀서가 만들어짐
//		};
//	}


		@Bean
	public ApplicationRunner runner(AdminClient adminClient)
	{	//키-벨류 타
		//러너는 어플리케이션 뜨고 나서 , 특정 액션을 정의할수 있는 기능입
		return args -> {
			//어드민클라이언트객체안에는 ,listTopics()메소드안에 namesToListings는
			//토픽의 이름키, 벨류는..TopicListing
			Map<String, TopicListing> topics = adminClient.listTopics().namesToListings().get();
			for(String topicName : topics.keySet())
			{
				TopicListing topicListing = topics.get(topicName);
				System.out.println(topicListing);
				//만들어진 토픽 정보 볼수 있음
//				(name=quickstart-events, internal=false)
//				(name=clip2, internal=false)
//				(name=clip2-part1, internal=false)
//				(name=fastcampus, internal=false)
//				(name=clip2-part2, internal=false)
				Map<String, TopicDescription> description = adminClient.describeTopics(Collections.singleton(topicName)).all().get();
				System.out.println(description);

				//토픽삭제
				//adminClient.deleteTopics(Collections.singleton(topicName));
			}


		};
	}



}
