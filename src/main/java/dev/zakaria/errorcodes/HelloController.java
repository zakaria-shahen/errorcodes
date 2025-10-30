package dev.zakaria.errorcodes;

import dev.zakaria.errorcodes.exception.BusinessException;
import dev.zakaria.errorcodes.exception.ErrorCodes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

	@GetMapping("/hello")
	 public Mono<String> sayHello() {
		return Mono.fromCallable(() -> {
			throw new BusinessException(ErrorCodes.GOOGLE_NOT_FOUND);
		});
	 }
}
