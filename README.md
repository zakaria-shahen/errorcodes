### ErrorCode Dome*

- In this demo project, I share a new approach to handling error codes in your applications, along with a new method for logging the ErrorCodes and the class and line number where the error occurred.
- This handles everything in a centralized way, so you can set it up once and forget about it.
- This approach supports **Spring WebFlux** and **Spring WebMVC**, and can be adapted to other frameworks or languages as well.
- The approach is expandable, which means you can add more features like i18n, push metrics via Prometheus, etc.

[*] Credit for the `ErrorCodes` Enums goes to my teammates at one of my previous workplaces; other ideas are my own.

#### How to use it (as business backend developer)

- check if your error code is already defined in `ErrorCode` enum.
- if not, add your error code there with proper description in the `ErrorCodes` enum class.
- whenever you want to throw an error, just throw `new BusinessException(ErrorCode.YOUR_ERROR_CODE)`
- and the core code/developer will take care of the rest. 🎉


#### The result

When you throw the `BusinessException`, you will get a proper error response with error code and description aligned with [RFC 9457](https://www.rfc-editor.org/rfc/rfc9457.html) specification, like below:

```json
{
  "detail": "not found, try again later",
  "errorCode": "SERVICE_01_001",
  "instance": "/api/errorcodes/hello",
  "status": 500,
  "title": "Error",
  "type": "about:blank"
}
``` 

and logs like below:

```log
2025-10-30T23:00:29.226+03:00  INFO 00000 --- [errorcodes] [ctor-http-nio-2] d.z.e.exception.GlobalExceptionHandler   : Error occurred SERVICE_01_001 [SERVICE_GOOGLE_001]: dev.zakaria.errorcodes.HelloController.lambda$sayHello$0(HelloController.java:15)
```

#### Why HTTP Status 500?

- short answer: **Bike-Shedding** phenomenon
- long answer: In my experience, business error codes often do not align well with HTTP status codes. and every developer has their own opinion on which HTTP status code to use for which business error code, leading to endless debates and delays in development.
- So, The error codes defined in the `ErrorCode` enum are business error codes, not HTTP status codes.
- then, you define a error code format that algents with your business needs, for example `SERVICE_{MICROSERVICE_ID}_{ERROR_ID}`, etc.
- As I know, there is no concept of HTTP status codes in **GraphQL**, **gRPC**, so this approach is more flexible and adaptable to different cases.


#### How to run demo project

- install java 25, for example using [sdkman](https://sdkman.io/) or winget via `winget install EclipseAdoptium.Temurin.25.JDK`
- run `./gradlew bootRun` in the project root folder
- access [http://localhost:8080/api/errorcodes/hello](http://localhost:8080/api/errorcodes/hello) to see the error response and logs in action


That's it! Enjoy handling error codes in a better way! 🚀