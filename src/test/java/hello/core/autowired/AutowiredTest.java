package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    @DisplayName("Autowired 테스트")
    void AutowiredOption() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

        //when


        //then

    }

    static class TestBean {
        @Autowired(required = false)/*의존 관계가 없으면 이 메서드 자체가 호출이 안된다.*/
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired/*null을 호출한다.*/
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired/*Optional.empty를 호출한다.*/
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }


}
