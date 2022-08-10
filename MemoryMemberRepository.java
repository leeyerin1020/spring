package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{


    private static Map<Long, Member> store= new HashMap<>();
    private static long sequence =0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);// sequence 값을 먼저 하나올려주고
        //sequence 변수 이용해서 id set해준다.
        //회원접오-> id, 회원이름인데 이름은 입력받아서 넣어준다. (Member member로 인자 넘어온다.)
        store.put(member.getId(), member);
        return member;

    }

    @Override
    public Optional<Member> findByID(Long id) {
        //return store.get(id);-> 이렇게 할경우 null이 반환될 수도 있다.
        return Optional.ofNullable(store.get(id));//따라서 optional로 감싼다.

    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member-> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {//loop 돌리기 쉽게, list
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
