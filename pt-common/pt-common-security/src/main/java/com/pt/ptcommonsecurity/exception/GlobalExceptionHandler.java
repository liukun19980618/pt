package com.pt.ptcommonsecurity.exception;


import com.pt.ptcommoncore.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author ruoyi
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public R businessException(CustomException e) {
        if (StringUtils.isEmpty(e.getCode())) {
            log.error("全局异常信息 ex={}", e.getMessage(), e);
            return R.failed(e.getMessage());

        }
        return R.failed(e.getCode(), e.getMessage());
    }

}
