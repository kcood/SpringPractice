package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
       AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
       StatefulService statefulService1 =  ac.getBean(StatefulService.class);
       StatefulService statefulService2 =  ac.getBean(StatefulService.class);

       //ThreadA: A사용자가 1만원 주문
      //statefulService1.order("userA", 10000);
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB: B사용자가 2만원 주문
      //statefulService1.order("userA", 20000);
        int userBPrice = statefulService1.order("userB", 20000);

      //ThreadA: 사용자A가 주문 금액을 조회
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);
        System.out.println("Aprice = " + userAPrice);
        System.out.println("Bprice = " + userBPrice);

        //Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public  StatefulService statefulService() {
            return new StatefulService();
        }
    }

}