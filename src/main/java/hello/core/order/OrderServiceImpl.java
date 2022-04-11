package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor /* final이 붙은것으로 생성자를 만들어준다. */
public class OrderServiceImpl implements OrderService {

    /*철저하게 인터페이스만 지키고 있다.*/
    private final MemberRepository memberRepository; /*초기값을 넣어주거나 생성자를 통해서만 값을 넣을 수 있다.*/
    private final DiscountPolicy discountPolicy;

    /*
     * 1. 생성자가 한개만 있을 경우
     * 2. 스프링 빈에 등록될 경우
     * @Autowired 생략 할 수 있다.*/
    /*
     * 빈을 등록하면서 의존관계 주입도 같이 일어난다.
     * new OrderServiceImpl(memberRepository, discountPolicy)
     * */
    /*public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/
    /*@Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    /*
    * 수정자는 생성이 완료된 후에 일어난다.
    * */
    /*@Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }*/



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
