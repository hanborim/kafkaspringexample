# kafkaspringexample
kafka_sprong example
[스프링 카프카]
파트1
1.스프링을 이용해 사장 간단한 방법으로 메세지 발행/구독
2.카프카 토픽 생성/삭제
-자바 애플리케이션을 통해 토픽 생성과 삭제
3.publish messages(발행)
4.consume message(구독)
-스프링이 제공하는 여러 인터페이스를 이용해 메시지를 발행/구독

파트2
1.토픽관리하기
-토픽 수정.목록조회,오프셋관리
2.간단한 성능 모니터링
-카프카 template성능을 측정
3.error handling
-스프링이 제공하는 강력한 에러 처리 방버
4.kafka Stream with spring
-stream과 consumer의 차이등 stream에 대해서 알아보기




1.주키퍼 실행
Users/borimhan/Documents/kafka/kafka 여기에서 실행
➜  kafka  bin/zookeeper-server-start.sh config/zookeeper.properties

2.브로커 실행
➜  kafka bin/kafka-server-start.sh config/server.properties

3.토픽 생성
➜  kafka bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092
➜  kafka bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092
Created topic quickstart-events.
➜  kafka bin/kafka-topics.sh --list --zookeeper localhost:2181
➜  kafka bin/kafka-topics.sh --list --zookeeper localhost:2181
__consumer_offsets
fastcampus
quickstart-events


4.인텔리제이
뉴-프로젝트생성-그레이들
Name:chap1_clip1
Group:com.fastcampus
Pacakage name : com.fastcampus.chap1_clip1
자바:11 , 패키지:jar
디펜던시 : spring for apache Kafka



[카프카]
다운로드
https://kafka.apache.org/downloads
* Scala 2.13  - kafka_2.13-2.8.0.tgz (asc, sha512)

설치
➜  kafka_2.13-2.8.0 tar -xvf kafka_2.13-2.8.0.tgz
➜  kafka ln -s kafka_2.13-2.8.0 kafka (링크)
/Users/borimhan/Documents/kafka/kafka_2.13-2.8.0


‘-주키퍼 실행
/Users/borimhan/Documents/kafka/kafka 여기에서 실행
➜  kafka  bin/zookeeper-server-start.sh config/zookeeper.properties
로그확인
[2022-09-26 23:11:15,451] INFO binding to port 0.0.0.0/0.0.0.0:2181 (org.apache.zookeeper.server.NIOServerCnxnFactory)

‘-서버 커넥트 확인
➜  ~ telnet 0 2181
Trying 0.0.0.0...
Connected to 0.
Escape character is '^]'.

‘-주키퍼 접속
➜  bin ./zookeeper-shell.sh localhost:2181

‘-브로커
/Users/borimhan/Documents/kafka/kafka/config/server.properties
주석해제:
 31 #listeners=PLAINTEXT://:9092
listeners = PLAINTEXT://127.0.0.1:9092

“꼼꼼히 들여다 보기
/Users/borimhan/Documents/kafka/kafka/config/server.properties
#     listeners = PLAINTEXT://your.host.name:9092
-브로커가 참조하는 엔드포인트
#advertised.listeners=PLAINTEXT://your.host.name:9092
-프로듀서나 컨슈머가 참조 하는 엔드포인트
2가지로 나눈 이유는?
외부와 내부 트래픽을 나누기 위해서
num.network.threads=3
-서버가 요청을 받거나,응답을 내보내는 스레드
num.io.threads=8
-서버가 클라이언트 요청을 처리할때, 디스크IO까지 포함한 작업을 처리할때 사용하는 스레드
log.dirs=/tmp/kafka-logs
-브로커가 데이터를 저장하는 dir를 설정하는곳
#log.flush.interval.messages=10000
-실제로 데이터를 디스크에 쓰기전에 몇개까지 메세지를 가지고 있을것이냐
#log.flush.interval.ms=1000
-1초마다 디스크에 플러스 하도록 설정

‘-추가적인 설정
1.auto.create.topics.enable
-토픽의 오토크리에이션기능을 킬껀지 , 기본값 트루

2.compression.type
-카프카는 메세지 압축을 지원함 ex(gzip,..)
프로듀서가메세지를 압축해서 전송하면, 브로커가 압축된체로 디스크에 저장
컨슈머에서 디스컴프레션되는 구조

3.delete.topic.enable
-토픽을 삭제하는것을 활성화 한다
기본값은 트루
4.message.max.byte
-메세지를 브로커에서 허용할 가장큰바이트를 지정할수 있음




‘-브로커 실행
/Users/borimhan/Documents/kafka/kafka 여기에서
➜  kafka bin/kafka-server-start.sh config/server.properties


‘-토픽생성
➜  bin ./kafka-topics.sh 
➜  bin ./kafka-topics.sh --create --topic fastcampus(토픽 이름)
➜  bin ./kafka-topics.sh --create --topic fastcampus --bootstrap-server localhost:9092

‘-토픽 리스트 확인
➜  bin ./kafka-topics.sh --list --bootstrap-server localhost:9092

‘-메세지 발행
➜  bin ./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic fastcampus
➜  bin ./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic fastcampus
>hello
>fastcapus!
>

‘-구독
➜  bin ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic fastcampus
첫번째부터 구독
➜  bin ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic fastcampus --from-beginning
