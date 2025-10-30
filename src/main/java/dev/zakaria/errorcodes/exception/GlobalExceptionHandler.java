package dev.zakaria.errorcodes.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


	@ExceptionHandler(BusinessException.class)
	public ProblemDetail handleBusinessException(BusinessException ex) {
		var errorCode = ex.getErrorCode();
		log.info("Error occurred {} [{}]: {}", errorCode.errorCode, errorCode.internalErrorCode, ex.getCaller());
		return ex.getBody();
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		log.info("Forces JVM to eger loading (warm start/ahead of time alternative) ErrorCodes enum via log number of ErrorCode: {}", ErrorCodes.values().length);
	}
}
