package com.zq.core.validate.code.image;

import com.zq.core.validate.code.common.ValidateCode;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/8 下午1:15
 * @Package com.zq.core.validate.code.image
 **/


/**
 * 图片验证码
 */
@Getter
@Setter
public class ImageCode extends ValidateCode {

    private BufferedImage bufferedImage;

    public ImageCode(BufferedImage bufferedImage, String code, int expireIn) {
        super(code, expireIn);
        this.bufferedImage = bufferedImage;
    }

    public ImageCode(String code, LocalDateTime expireTime, BufferedImage bufferedImage) {
        super(code, expireTime);
        this.bufferedImage = bufferedImage;
    }
}
