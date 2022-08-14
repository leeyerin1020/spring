package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){// beforeEach=> 테스트 시작하기 전마다 실행되도록
        //테스트는 독립적으로 실행되어야 하기에
        memberRepository= new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    //repository가 마치 db처럼 사용되는 것 같고.. + 객체를 생성하는 것이 새로운 repository에 저장되도록
    //하는것같음-> 그래서 new MemoryMemberRepository를 test와 java 코드 모두에서 하지 않도록, 하나의 repositoy를 사용하도록 해준다.
    //그래서 java 코드에서 new 생성이 아닌,,

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    //test는 과감하게 한글로 해도된대-> 빌드시 코드에 포함 안되기에
    void 회원가입() {//test할 때, 이렇게 주석 깔고 시작하자!
        //given, 이 데이터를 검증한다
        Member member= new Member();
        member.setName("hello");

        //when, 이 상황에서
        Long saveId= memberService.join(member);
        //then
        Member findMember=memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test// test는 예외 상황 검증이 더욱 중요하다.
    public void 중복_회원_예외(){
        //given
        Member member1= new Member();
        member1.setName("spring");

        Member member2= new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        /*
        try{
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }// assertThat이용해서, 에러 메세지랑 같은지 .. fail하는 경우 에러 잡을 수 있도록
        */
        memberService.join(member2);
        //중복회원이기에 예외가 발생하지-> try, catch 문이 실행되게 해도된다.
        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}