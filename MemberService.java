package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {// service가 스프링 컨테이너에 등록되면서.. 아래 생성자를 호출하는데
    //ctrl+shift+t해주면 테스트 바로 만들어줄 수 있음!
    private final MemberRepository memberRepository;// memberservice는 memberrepository가 필요함!


    public MemberService(MemberRepository memberRepository){// autowired이기에 스프링 컨테이너에 repository를 주입해준다. 서비스에
        this.memberRepository= memberRepository;
    }//이게 di라고 한대(dependency injection?)
    //memberRepository를 만든후 집어넣어 주는 것으로 해줌! => 같은 repositoty 이용하도록.. test에서도


    public Long join(Member member){
        //같은 이름의 중복 회원은 제외!
        //optional이용해서,, ifPresent이용 가능
        validateDuplicateMember(member);//중복회원 검증 메서드로 추출해줌
        //findbyname의 결과가 optional member니깐, 바로 .ifPresent=> 즉 중복 회원 존재
        memberRepository.save(member);
        return member.getId();// 회원 가입 기능 , 그냥 id 리턴하게 해준거임
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    //전체 회원 조회
    //service는 비즈니스를 처리하는 것이기에 이름 자체가 딱 join, 보이도록
    public List<Member> findMembers(){
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findByID(memberId);
    }
}
