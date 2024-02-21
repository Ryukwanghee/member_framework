package com.codingrecipe.member.service;

import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
// final이 붙은 필드만 가지고 생성자를 만드는 어노테이션 RequiredArgsConstructor, 생성자 주입으로 의존성
public class MemberService {
    private final MemberRepository memberRepository;

    public int save(MemberDTO memberDTO) {
        return memberRepository.save(memberDTO);
    }


    public boolean login(MemberDTO memberDTO) {
        MemberDTO loginMember = memberRepository.login(memberDTO);
        if (loginMember != null) {
            return true;
        } else {
            return false;
        }
    }

    public List<MemberDTO> findAll() {
        return memberRepository.findAll();
    }

    public MemberDTO findById(Long id) {
        return memberRepository.findById(id);
    }

    public void delete(Long id) {
        memberRepository.delete(id);
    }

    public MemberDTO findByMemberEmail(String loginEmail) {
        return memberRepository.findByMemberEmail(loginEmail);
    }

    public boolean update(MemberDTO memberDTO) {
        int result = memberRepository.update(memberDTO);
        if (result > 0 ){
            return true;
        } else {
            return false;
        }
    }

    public String emailCheck(String memberEmail) {
        MemberDTO memberDTO = memberRepository.findByMemberEmail(memberEmail);
        if (memberDTO == null){
            return "ok";
        } else {
            return "no";
        } /*여기서 이제 controller쪽에 return 으로 ok인지 no인지 주게 됨, 그럼 save.jsp 쪽 res에 값이 넘어가게 되면서 출력 */
    }
}
