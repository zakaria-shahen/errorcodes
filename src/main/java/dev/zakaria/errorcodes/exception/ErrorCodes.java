package dev.zakaria.errorcodes.exception;

/// ErrorFormat:
///
/// ErrorCode (for response): `[service-name]_[random_number_for_each_system]_[random-number-for-each-code]`
///
/// Internal Error Code (for logs): `[service-name]_[system-name]_[random-number-for-each-code]`
public enum ErrorCodes {


	SELF_UNKNOWN("SERVICE_00_000", "SERVICE_SELF_001", "Error", "Something went wrong"),
	GOOGLE_NOT_FOUND("SERVICE_01_001", "SERVICE_GOOGLE_001", "Error", "not found, try again later")
	// ..etc
	;

	final String errorCode;
	final String internalErrorCode;
	final String title;
	final String detail;

	ErrorCodes(String errorCode, String internalErrorCode, String title, String detail) {
		this.errorCode = errorCode;
		this.internalErrorCode = internalErrorCode;
		this.title = title;
		this.detail = detail;
	}
}
