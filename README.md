# xml Config
  1) 암시적(implicity)
      -auto scanning, Annotaion Scannig
      - 설정파일(xml) <context:annotaion-config />
        + Bean Class : MVC 모델(@Component, @Service, @Controller, @Repository) , @Autowired(주입하기 DI)
  2) 명시적(explicity)
      - 설정파일 <bean ../>
      
# Java Config
  1) 암시적(implicity)
      -auto scanning, Annotation Scanning
      -설정파일(@Configuration, @ComponentScan)
        + Bean Class(@Component, @Service, @Controller, @Repository)
  2) 명시적(explicity)
      - 설정파일(@Configuration, @ComponentScan)
        @Bean, @Qualifier...
       
# Annotaion 정리

@Component : class Bean으로 바꿀 때 필요.

@ComponentScan : @Component를 Scan하는 것.

WebMvcConfigurerAdapter 상속 받는 이유???
  Spring MVC 환경을 구성하는 클래스 들을 등록!(예외처리, Valid 등..)
   - Interceptors, Handler, @EnableWebMvc
