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