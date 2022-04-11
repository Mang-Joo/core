package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        /*basePackages = "hello.core.member",*//*여기 패키지 안에 있는것만 ComponentScan한다.*//*
        basePackageClasses = AutoAppConfig.class,*/
        /*default: 클래스 패키지부터 다 찾아본다.*/
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) /*AppConfig 등록을 제외하기 위함*/
)/*@Annotation이 붙은 클래스를 찾아 스프링 빈으로 등록해준다.*/
public class AutoAppConfig {
/*
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    */

}
