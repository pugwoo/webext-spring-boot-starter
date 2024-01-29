package com.pugwoo.webextstarterdemoboot3.entity;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 演示用<br/>
 *  此处使用 lombok 插件，自动生成getter/setter等<br/>
 *  使用 @Data == @Setter + @Getter + @ToString + @RequiredArgsConstructor + @EqualsAndHashCode<br/>
 * @date 2018-07-12
 */
@Data
public class UserDO {
    
    @NotNull
    private String name;

    @NotBlank
    private String password;

    private Integer age;

}
