package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {


   //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
   //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //얘들은 DIP 위반. 추상인 인터페이스에만 의존하는게 아니라 구체 클래스 (Fix, Rate DiscountPolicy)에 의존
    private final DiscountPolicy discountPolicy;
    //MemberRepository에서 회원 찾아야되니까
    private final MemberRepository memberRepository;


    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //멤버찾기
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
