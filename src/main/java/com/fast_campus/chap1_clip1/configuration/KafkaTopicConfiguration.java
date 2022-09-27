package com.fast_campus.chap1_clip1.configuration;


import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfiguration {

    //카프카 정보를 가지고 오는거
    //어드민 클라이언트 이용,설정 프로펄티스들이 필요함 .디폴트이용해서 만듬
    @Bean
    public AdminClient adminClient(KafkaAdmin kafkaAdmin)
    {
        return AdminClient.create(kafkaAdmin.getConfigurationProperties());
    }

    @Bean
    public NewTopic clip2()
    {
        //토픽 생성
        return TopicBuilder.name("clip2").build();
        //➜  kafka bin/kafka-topics.sh --list --zookeeper localhost:2181
    }

    //뉴토픽스 이용하면 여러개 한꺼번에 만듬
    @Bean
    public KafkaAdmin.NewTopics clips()
    {
        //카프카 어드민객체가 초기화되면서 만들어짐
        //카프카어드민은 실행할때 디폴트로 만들어지게 되어있음

        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("clip2-part1").build(),
                TopicBuilder.name("clip2-part2").partitions(3) //파티션 3개
                        .replicas(1) //브로커1개
                        .config(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(1000*60*60))
                        //1시간 리텐션 시간 가짐 (보관주기)
                        .build()
        );
    }

}
