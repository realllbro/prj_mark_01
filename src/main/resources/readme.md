* 머스테치   
다양한 언어를 지원하는 심플한 템플릿 엔진
자바에서 사용될 때는 서버 템플릿 엔진, 자바스크립트에서
사용될 때는 클라이언트 템플릿 엔진으로 모두 사용할 수 있다.


* 머스테치 의존성 추가 build.gradle
implementation('org.springframework.boot:spring-boot-starter-mustache')


* 기본 파일 위치  
 src/main/resources/templates 하위.  
이 위치에 머스테치 파일을 두면 스프링 부트에서 자동으로 로딩한다.


* 화면은 오픈소스인 부트스트랩과 제이쿼리를 이용.


* 페이지 로딩속도를 높이기 위해 css는 header에, js는 footer에 둔다.
* HTML은 위에서부터 코드가 실행되기 때문에 head가 다 실행되고서야 body 가 실행된다. 
* 즉 head가 다 불러지지 않으면 사용자 쪽에선 백지 화면만 노출된다.
* 특히 js의 용량이 크면 클수록 body 부분의 실행이 늦어지기 때문에 js는 body 하단에 두어 화면이 다 그려진 뒤에 호출하는 것이 좋다.
* 반면 css는 화면을 그리는 역할이므로 head에서 불러오는 것이 좋다. 그렇지 않으면 css가 적용되지
* 않은 깨진 화면을 사용자가 볼수 있기 때문이다. 추가로 bootstrap.js의 경우 제이쿼리가 곡 있어야만 하기 때문에 
* 부트스트랩 보다 먼저 호출되도록 코드를 작성한다. (bootstrap.js 가 제이쿼리에 의존하기 때문)

스프링 부트는 기본적으로 src/main/resources/static에 위치한 자바스크립트, CSS, 이미지 등 정적 파일들은 URL 에서 /로 설정된다.
그래서 다음과 같이 파일이 위치하면 위치에 맞게 호출이 가능하다.

src/main/resources/static/js/...(http://도메인/js/...)
src/main/resources/static/css/...(http://도메인/css/...)
src/main/resources/static/image/...(http://도메인/image/...)



* REST 에서 CRUD 는 다음과 같이 HTTP Method에 매핑된다.  
생성(Create) - POST  
읽기(Read) - GET  
수정(Update) - PUT  
삭제(Delete) - DELETE  
