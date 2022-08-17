package net.seehope.foodie.common.properties;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dayday
 * @date 8/9/2022 10:23 PM
 **/
@Data
public class ImageValidateCodeProperties {
    private Integer width = 67;
    private Integer height = 23;
    private Integer length = 4;
    private Integer expireIn = 60 * 15;

    private String codeType = "image";

    private List<String> processingUrl = new ArrayList<>();
}