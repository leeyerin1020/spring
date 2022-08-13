package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();


    @AfterEach// 공용 데이터를 지워주기 위해 aftereach!,각 테스트가 끝날때마다 clear
    public void afterEach(){
        repository.clearStore();// 순서 상관없이 테스트 끝날때마다 -> reposityory 내용 지워준다!
    }
    @Test// test annocation을 통해 test해준다.
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result= repository.findByID(member.getId()).get();
        assertThat(member).isEqualTo(result);
        //member가 result와 똑같은지
    }

    @Test
    public void findByName(){
        Member member1= new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2= new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result=repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);


    }

    @Test
    public void findAll(){
        Member member1= new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2= new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result=repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
    //모든 테스트에서 메소드의 순서는 랜덤이기에,,
    //순서 의존적으로 설계하면 안됨. findAll-> findByName 이미 객체 선언되어있기에
    //-> 따라서 clear해줘야한다!


}
