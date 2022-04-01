package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class MemberApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService(); /* AppConfig에서 결정헌다. */
//        MemberService memberService = new MemberServiceImpl(); 직접 생성.

        Member member = new Member(1L, "memberA", Grade.VIP);

        Member findMember = memberService.findMember(1L);

        System.out.println("findMember = " + findMember.getName());
        System.out.println("memberA = " + member.getName());

    }
}
