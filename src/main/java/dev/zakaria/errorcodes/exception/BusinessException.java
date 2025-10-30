package dev.zakaria.errorcodes.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

@Getter
@Slf4j
public class BusinessException extends ErrorResponseException {

	private final StackTraceElement caller;
	private final ErrorCodes errorCode;

	public BusinessException(ErrorCodes errorCodes) {
		// If you're using Java version earlier than 25, you can extract this into a static method.
		// TODO: You can apply i18n here and make the title and detail values configurable properties.
		var problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		problemDetail.setTitle(errorCodes.title);
		problemDetail.setDetail(errorCodes.detail);
		problemDetail.setProperty("errorCode", errorCodes.errorCode);

		super(HttpStatus.INTERNAL_SERVER_ERROR, problemDetail, null);
		this.caller = fetchCaller();
		this.errorCode = errorCodes;
	}

	private static StackTraceElement fetchCaller() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		// stackTrace[0] is getStackTrace()
		// stackTrace[1] is fetchCaller()
		// stackTrace[2] is BusinessException() constructor
		// stackTrace[3] is the actual caller
		return stackTrace[3];
	}
}
