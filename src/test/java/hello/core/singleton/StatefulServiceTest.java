package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자 10,000원 주문
        int userA = statefulService1.order("userA", 10000);
        //ThreadB: B사용자 20,000원 주문
        int userB = statefulService2.order("userB", 20000);

        /*
         * ThreadA: 사용자A 주문 금액 조회
         * */

        System.out.println("userA = " + userA);
        System.out.println("userB = " + userB);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}