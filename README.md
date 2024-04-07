Custom Web Application Server 구현
=== 
# 목표
HTTP 프로토콜과 HttpRequest/HttpResponse 구조를 이해하기 위해, Http Protocol (Http 통신 규약)에 따라 메시지를 주고 받을 수 있는 Tomcat과 같은 WAS (Web Application Server)를 Custom 형태로 구현

# 실습 단계
- Step1. 사용자 요청을 메인 Thread가 처리하도록 한다.
- Step2. 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리하도록 한다.
- Step3. Thread Pool을 적용해 안정적인 서비스가 가능하도록 한다.

# Http request 구조
Request line
Header
Blank line
Body

# Http response 구조
Status line
Header
Blank line
Body

# Step1 : 메인 쓰레드를 이용한 Http 요청 처리
## Step1의 구현 내용
- Socket을 이용한 Client <-> Server 통신 구조 정의
- Http Protocol에 따라 통신하기 위한 Http request/response 도메인 객체 생성
- Http request, Request line 데이터 구조에 따라, 수신한 InputStream 데이터를 Java 도메인 객체로 변환
- 변환된 Java 도메인 객체를 이용한 사칙 연산 로직 수행
- 수행 결과 반환을 위해 Http response 구조에 따라 OutputStream을 이용한 응답 반환

## Step1의 한계점
- 메인 쓰레드에서 클라이언트 요청을 처리하므로, 작업 수행 중 blocking이 발생하는 경우 메인 쓰레드가 요청 처리를 완료할 때 까지 다음 클라이언트 요청을 처리할 수 없어 동시에 여러 요청을 처리 할 수 없는 문제 발생

## Step1의 한계점 해결
- 클라이언트의 요청 시, 메인 쓰레드가 아닌 요청 처리를 위한 별도 쓰레드를 생성하여 처리하도록 수정함으로써, 단일 쓰레드가 요청을 처리하여 동시에 여러 요청을 처리 할 수 없는 문제점 해결

# Step 2 : 별도 쓰레드를 이용한 Http 요청 처리
## Step2의 구현 내용
- 클라이언트 요청을 처리할 별도 스레드 생성을 위한 ClientRequestHandler 구현 : Runnable

## Step2의 한계점
- 쓰레드 생성 시 독립적인 스택 메모리 공간을 할당 받는데 이는 매우 비용이 큰 작업인데, 사용자 요청이 몰리게 되는 경우 쓰레드를 생성하기 위해 메모리 공간을 할당 받는 과정으로 인해 서버 성능 저하 문제가 발생할 수 있음
- 또한 쓰레드가 많아지게 되면 CPU Context switching 횟수가 증가하게 될 것이며, CPU 혹은 메모리 사용량이 증가하는 문제가 발생할 수 있다.
- 이런 서버 과부하가 지속되는 경우 최악에 상황에서 서버가 다운될 수 있는 가능성 존재

## Step2의 한계점 해결
- 클라이언트 요청 시 마다 쓰레드를 생성하여 처리하는 것이 아닌, 요청을 처리할 쓰레드를 고정된 개수만큼 생성하고, 이를 재활용하는 Thread Pool을 적용하여 안정적인 서비스 제공

# Step 3 : Thread Pool을 이용한 Http 요청 처리
- 제한된 숫자의 쓰레드를 미리 생성하여 재활용 할 수 있는 Thread pool 적용하여 클라이언트 요청 시, Thread pool에 미리 생성된 쓰레드를 이용하여 클라이언트의 요청 처리
