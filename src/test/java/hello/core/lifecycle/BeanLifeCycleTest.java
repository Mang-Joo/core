package hello.core.lifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {


    @Test
    @DisplayName("라이프사이클 테스트")
    void lifeCycleTest() {
        //given
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClientTest clientTest = ac.getBean(NetworkClientTest.class);

        //when

        //then
        ac.close();

    }

    @Configuration
    static class LifeCycleConfig {

        @Bean
        public NetworkClientTest networkClientTest() {
            NetworkClientTest networkClientTest = new NetworkClientTest();
            networkClientTest.setUrl("http://hello-spring.dev");
            return networkClientTest;
        }

    }

}
