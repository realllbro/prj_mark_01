package com.brodio.brown.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/* @MappedSuperclass
 * JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우
 * 필드들(createdDate, modifiedDate)도 컬럼으로 인식하도록 한다.
 *
 * @EntityListeners(AuditingEntityListener.class)
 * BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다.
 *
 * @CreatedDate
 * Entity가 생성되어 저장될 때 시간이 자동 저장된다.
 *
 * @LastModifiedDate
 * 조회한 Entity의 값을 변경할 때 시간이 자동 저장된다.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    show databases;

    create database brodio;

    alter schema brodio default character set utf8; -- 둘중에 하나를 입력하면 DB 생성됨

    alter database brodio default character set utf8;

    GRANT ALL privileges ON brodio.* TO 'docker_mysql'@'%';


}
