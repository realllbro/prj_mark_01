package com.brodio.brown.domain.posts;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/* @RunWith(SpringRunner.class)
 * 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행 시킨다.
 * 여기서는 SpringRunner라는 스프링 실행자를 사용한다.
 * 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
 *
 * @SpringBootTest
 * 별다른 설정 없이 @SpringBootTest를 사용할 경우 데이터베이스를 자동으로 실행해 준다.
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;


    /* @After
     * Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
     * 보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용한다.
     * 여러 테스트가 동시에 수행되면 테스트용 데이터베이스에 데이터가 그대로 남아 있어
     * 다음 테스트 실행 시 테스트가 실패할 수 있다. */
    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title   = "테스트 게시글";
        String content = "테스트본문";

        /* postsRepository.save
         * 테이블 posts에 insert/update 쿼리를 실행.
         * id 값이 있다면 update가, 없다면 insert 쿼리가 실행. */
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("brother@gmail.com")
                .build());

        /* postsRepository.findAll
         * 테이블 posts에 있는 모든 데이터를 조회해오는 메소드 이다.*/
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){

        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>> createDate = "+posts.getCreatedDate()
                          +", modifiedDate = "+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
