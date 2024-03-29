package com.brodio.brown.config.auth.dto;

import com.brodio.brown.domain.user.User;
import lombok.Getter;
import java.io.Serializable;

@Getter
public class SessionUser implements Serializable{

    /* SessionUser 에는 인증된 사용자 정보만 필요하다.
     * 그 외에 필요한 정보들은 없으니 name, email, picture만 필드로 선언한다. */
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.name = user.getEmail();
        this.name = user.getPicture();
    }
}
