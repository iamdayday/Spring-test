package net.seehope.foodie.common.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author dayday
 * @date 8/9/2022 6:25 PM
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateCode {
    private String code;
    private LocalDateTime expireTime;

    public boolean isExpired(){
       return LocalDateTime.now().isAfter(expireTime);
    }

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
}
