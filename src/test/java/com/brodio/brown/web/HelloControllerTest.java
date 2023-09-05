package com.brodio.brown.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/* @RunWith(SpringRunner.class)
 * 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행 시킨다.
 * 여기서는 SpringRunner라는 스프링 실행자를 사용한다.
 * 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
 *
 * @WebMvcTest
 * 여러 스프링 어노테이션 중 Web(Spring MVC)에 집중할 수 있는 어노테이션 이다.
 * 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있다.
 * 단, @Service, @Component, @Repository 등은 사용할 수 없다.
 * 여기서는 컨트롤러만 사용하기 때문에 선언한다.
 *
 * 'jpaAuditingHandler': Cannot resolve reference to bean 오류 처리 메모사항
 * 원본 : https://velog.io/@suujeen/Error-creating-bean-with-name-jpaAuditingHandler
 * Spring 컨테이너를 요구하는 test는 가장 기본이 되는 Application 클래스가 항상 로드된다.
 * Application 클래스에 @EnableJPaAuditing 어노테이션을 달아놓아서 모든 test들이 항상 Jpa관련 bean들을 필요로 하는 상태가 되었다.
 * 통합 test는 전체 context를 로드하고 Jpa를 포함한 모든 Bean들을 주입받기 때문에 에러가 발생하지 않는다.
 * 하지만 @WebMvcTest는 Jpa 관련 bean을 전혀 로드하지 않는 단위테스트이기 때문에 에러가 발생하는 것이다.
 *
 * 방법 1. MockBean 추가하기
 * @MockBean(JpaMetamodelMappingContext.class)
 * test 클래스에 이 어노테이션을 달아주면 일단 그 test는 오류 없이 실행시킬 수 있으나,
 * 위에서 언급했듯이 모든 test에서 bean을 필요로 하는 상태가 되었기 때문에 모든 test 클래스에 이 어노테이션을 달아주어야 한다.
 * 방법 2. @Configuration 분리하기
 * @Configuration
 * @EnableJpaAuditing
 * public class JpaAuditingConfiguration {} */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class HelloControllerTest {

    /* @Autowired
     * 스프링이 관리하는 빈(Bean)을 주입 받는다.
     *
     * private MockMvc mvc
     * 웹 API를 테스트할 때 사용한다.
     * 스프링 MVC 테스트이 시작점이다.
     * 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있다.*/
    @Autowired
    private MockMvc mvc;


    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        /* mvc.perform(get("/hello"))
         * MockMvc를 통해 /hello 주소로 HTTP GET 요청을 한다.
         *
         * .andExpect(status().isOk())
         * mvc.perform의 결과를 검증한다.
         * HTTP Header의 Status를 검증한다.
         * 우리가 흔히 알고 있는 200, 404, 500 등의 상태를 검증한다.
         * 여기선 OK 즉, 200 인지 아닌지를 검증한다.
         *
         * .andExpect(content().string(hello))
         * mvc.perform의 결과를 검증한다.
         * 응답 본문의 내용을 검증한다.
         * Controller 에서 "hello*를 리턴하기 때문에 이 값이 맞는지 검증한다. */
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        /*
         * param
         * API 테스트할 때 사용될 요청 파라미터를 설정한다.
         * 단, 값은 String만 허용된다.
         * 그래서 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경해야만 가능하다.
         *
         * jsonPath
         * JSON 응답값을 필드별로 검증할 수 있는 메소드 이다.
         * $를 기준으로 필드명을 명시한다.
         * 여기서는 name과 amount를 검증하니 $.name, $.amount로 검증한다.
         */
        mvc.perform(get("/hello/dto")
                        .param("name",name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}
