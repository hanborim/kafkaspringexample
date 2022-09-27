package com.fast_campus.chap1_clip1;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class Consumer {

    //아이디 정의하면 , 그룹아이디로 지정됨.그룹아이디는 클라이언트 아이디의 모체
    @KafkaListener(id ="fast-campus-id",topics = "quickstart-events") //이메소드가 컨슈머가 되게 하는 어노테이션 ,
    public void listen(String messeage){
        System.out.println("============");
        System.out.println(messeage);
        System.out.println("============");

    }
}
