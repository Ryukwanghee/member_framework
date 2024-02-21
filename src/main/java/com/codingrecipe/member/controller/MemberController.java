package com.codingrecipe.member.controller;

import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService; //RequiredArgsConstructor 사용하여 의존성 주입받고 서비스 클래스 활용
    // RequestMapping으로 경로를 지정하고 그 뒤에 해당하는 경로를 GetMapping에 넣어줌
    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    /*
    *   @RequestParam은 1개의 파라미터를 받을 때 사용
    *   @ModelAttribute는 여러개의 파라미터를 받을 때 사용, 즉 form형태의 HTTP Body와 요청 파라미터들을 생성자나 Setter로 바인딩하기 위해 사용

    * */

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO){ //requestparam보다 더 편리한 기능인 modelattribute제공
        int saveResult = memberService.save(memberDTO);
        if (saveResult > 0){
            return "login";
        } else {
            return "save";
        }

    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        boolean loginResult = memberService.login(memberDTO);
        if (loginResult) {
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            return "main";
        } else {
            return "login";
        }
    }

    @GetMapping("/")
    public String findALl(Model model) {
        //data를 가지고 가는 객체이기 때문에 Model 사용
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }

    // /member?id=1 로 오기때문에 /member뒤엔 없는 것, 그래서 GetMapping어노테이션 디에 안붙는거임
    // 데이터를 담아가야하니까 model객체, /member?id=1의 주소형태기 때문에 RequestParam을 이용, url에 있는 정보를 RequestParam으로 저장
    // 링크박식 GetMapping
    @GetMapping
    public String findById(@RequestParam("id") Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        memberService.delete(id);
        return "redirect:/member/";
    }

    //수정화면 요청
    @GetMapping("/update")
    public String updateForm(HttpSession session, Model model) { //주소만 찾아서 왔는데 컨트롤러 입장에서는 누구의 정보를 가져와서 수정할 것 인가? 를 알아야함, 수정은 대부분 내가 내것을 수정하니까
        //세션에 담아둔 내 로그인 정보를 가져와서 수정
        String loginEmail = (String) session.getAttribute("loginEmail"); //object 타입이기 때문에 String으로 형변환
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member", memberDTO);
        // member라는 키값으로 update.jsp로 넘어가가지고 update.jsp에서 value를 통해 가져올 수 있음
        return "update";
    }

    //수정처리
    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO){
        boolean result = memberService.update(memberDTO);
        if (result){
            return "redirect:/member?id=" + memberDTO.getId();
        } else {
            return "index";
        }
    }

    @PostMapping("/email-check") /* ajax로 요청 및 응답이 필요할땐 @ResponseBody 가 있어줘야 제대로 응답이 나감 */
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail){
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult; /* String값 return save.jsp의 ajax에 res쪽으로 들어감 */
    }
}
