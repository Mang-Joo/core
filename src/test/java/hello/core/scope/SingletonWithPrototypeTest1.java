package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonWithPrototypeTest1 {
    @Test
    @DisplayName("프로토타입")
    void prototypeFind() {
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        //when
        prototypeBean1.addCount();
        prototypeBean2.addCount();

        //then
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    @DisplayName("싱글톤 프로토타입 주입")
    void singletonClientUsePrototype() {
        //given
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);

        //when
        int count1 = clientBean1.logic();
        int count2 = clientBean2.logic();

        //then
        assertThat(count1).isEqualTo(1);
        assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean {
        private final Provider<PrototypeBean> prototypeBeanProvider;
        /*
        * ObjectFactory: getObject만 지원한다.
        * ObjectProvider: ObjectFactory를 상속받는다.
        * */

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get(); /*getObject를 실행하면 그 때 찾아준다.*/
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }


    @Scope(value="prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy ");
        }




    }

}
