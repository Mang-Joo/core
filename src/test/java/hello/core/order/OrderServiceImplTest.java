package hello.core.order;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceImplTest {

    @Test
    @DisplayName("OrderServiceImpl Test")
    void createOrder() {
        //given
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new RateDiscountPolicy());
        /*
         * 수정자 메서드로 두면 의존관계가 무엇이 들어가는지 직관적으로 확인할 수 없다.
         * 하지만 생성자 주입으로 했을 땐 생성할 때 필수적으로 들어가야하기 때문에 문제가 바로 나타난다.
         * */


        //when
        Order itemA = orderService.createOrder(1L, "itemA", 10000);

        //then
        assertThat(itemA.getDiscountPrice()).isEqualTo(1000);

    }

}