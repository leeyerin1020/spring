package hello.hellospring.repository;
import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByID(Long id);
    Optional<Member> findByName(String name);
    //null을 처리하는 방식-> optional이용
    List<Member> findAll();



}
