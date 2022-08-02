package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//controller-> 외부 요청을 받고 , service-> 비즈니스 로직 만들고, repository-> 데이터 저장.. 정형화된 패턴이다!
@Controller
public class MemberController {//controller annotation-> 객체가 생성된다, 스프링 컨테이너가 관리하기 시작한다.

    private final MemberService memberService;

    @Autowired// 스프링 컨테이너에서 memberservice를 가져온다---> dependency injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member= new Member();
        member.setName(form.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members= memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
