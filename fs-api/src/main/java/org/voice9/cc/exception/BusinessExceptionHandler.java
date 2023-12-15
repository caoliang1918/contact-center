package org.voice9.cc.exception;

import com.voice9.core.enums.ErrorCode;
import com.voice9.core.po.CommonResponse;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class BusinessExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(BusinessExceptionHandler.class);

    /**
     * 全局捕获异常
     *
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public CommonResponse runtimeException(Exception e) {
        logger.error(e.getMessage(), e);
        return new CommonResponse(ErrorCode.RUNTIME_EXCEPTION);
    }

    /**
     * 全局捕获异常
     *
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {BusinessException.class})
    public CommonResponse businessException(BusinessException e) {
        logger.error("errorMessage:{}  errorCode:{}", e.getMessage(), e.getErrorCode());
        return new CommonResponse(e.getErrorCode(), e.getMessage(), null);
    }

    /**
     * 空指针异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public CommonResponse nullPointHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("空指针异常 :{} ", e.getMessage(), e);
        return new CommonResponse(ErrorCode.NULL_POINT_EXCEPTION);
    }

    /**
     * 数据库异常
     *
     * @return
     */
    @ExceptionHandler(value = MyBatisSystemException.class)
    @ResponseBody
    public CommonResponse mybatisException(MyBatisSystemException e) {
        logger.error("数据库异常:{}", e.getMessage());
        return new CommonResponse(ErrorCode.DB_EXCEPTION);
    }


    /**
     * http 405异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public CommonResponse http405(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        logger.error("httpMethodNotSupportHandler :{} ", e.getMessage());
        return new CommonResponse(ErrorCode.METHOD_NOT_SUPPORT_EXCEPTION.getCode(), e.getMessage(), null);
    }

    /**
     * http 400异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public CommonResponse http400(HttpServletRequest request, HttpMessageNotReadableException e) {
        logger.error(e.getMessage());
        return new CommonResponse(ErrorCode.PARAMETER_ERROR.getCode(), e.getMessage().split(":")[0], null);
    }


    /**
     * 数据库异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = TransactionException.class)
    @ResponseBody
    public CommonResponse transactionExceptionHandler(TransactionException e) {
        logger.error("数据库异常:{}", e.getMessage());
        return new CommonResponse(ErrorCode.DB_EXCEPTION);
    }

    /**
     * 唯一约束异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public CommonResponse duplicateExceptionHandler(DuplicateKeyException e) {
        logger.error("唯一约束异常:{}", e.getMessage());
        return new CommonResponse(ErrorCode.DUPLICATE_EXCEPTION);
    }


    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public CommonResponse requestParamsHandler(MissingServletRequestParameterException e) {
        logger.error("请求参数异常:{}", e.getMessage());
        return new CommonResponse(ErrorCode.PARAMETER_ERROR.getCode(), e.getMessage(), null);
    }

    /**
     * 参数类型错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public CommonResponse httpMediaTypeNotSupport(HttpMediaTypeNotSupportedException e) {
        logger.error("请求参数异常:{}", e.getMessage());
        return new CommonResponse(ErrorCode.PARAMETER_ERROR.getCode(), e.getMessage(), null);
    }

    /**
     * 参数绑定错误
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public CommonResponse bindExceptionHandler(HttpServletRequest request, Throwable ex) {
        if (ex instanceof BindException) {
            BindException bindException = BindException.class.cast(ex);
            List<ObjectError> errors = bindException.getBindingResult().getAllErrors();
            return new CommonResponse(ErrorCode.PARAMETER_ERROR.getCode(), "参数校验错误：" + errors.get(0).getDefaultMessage(), null);
        }
        return new CommonResponse();
    }

    /**
     * 参数校验错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResponse validExceptionHandler(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        return new CommonResponse(ErrorCode.PARAMETER_ERROR.getCode(), "参数校验错误：" + errors.get(0).getDefaultMessage(), null);
    }


}
