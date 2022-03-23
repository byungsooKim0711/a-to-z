package org.kimbs.design.behavioral.state;

/*
1. 최초 상태는 REG 상태이다.
2. REG 상태에서는 REQ 상태로만 변경 가능하다.
3. REQ 상태에서는 REJ, APR, REG 상태로 변경 가능하다. (APR, REG 는 외부에서 변경함)
4. APR 상태에서는 REG 상태로 변경 가능하다.
5. REJ 상태에서는 REQ 상태로만 가능하다.
*/
public class Application {

    public static void main(String[] args) {
        TemplateService service = new TemplateService();

        Template regTemplate = service.getTemplate("template_code_reg");
        try {
            // 등록 -> 검수요청
            service.requestTemplate(regTemplate);
            // 검수요청 -> 승인취소 (실패)
            service.cancelApprovalTemplate(regTemplate);
        } catch (Exception e) {
            System.out.println(regTemplate.getCode() + " / " + e.getMessage());
        }

        System.out.println("----------------------------------");

        Template reqTemplate = service.getTemplate("template_code_req");
        try {
            // 검수요청 -> 검수요청 (실패)
            service.requestTemplate(reqTemplate);
        } catch (Exception e) {
            System.out.println(reqTemplate.getCode() + " / " + e.getMessage());
        }

        System.out.println("----------------------------------");

        Template aprTemplate = service.getTemplate("template_code_apr");
        try {
            // 승인 -> 검수취소 (실패)
            service.cancelTemplate(aprTemplate);
        } catch (Exception e) {
            System.out.println(aprTemplate.getCode() + " / " + e.getMessage());
        }

        System.out.println("----------------------------------");

        Template rejTemplate = service.getTemplate("template_code_rej");
        try {
            // 반려 -> 검수요청
            service.requestTemplate(rejTemplate);
            // 검수요청 -> 승인 취소(실패)
            service.cancelApprovalTemplate(rejTemplate);
        } catch (Exception e) {
            System.out.println(rejTemplate.getCode() + " / " + e.getMessage());
        }

        System.out.println("---------- 정상프로세스 1 ----------");

        // 정상 프로세스1
        Template aprTemplate1 = service.getTemplate("template_code_apr");
        try {

            // 승인 -> 등록 (검수취소)
            service.cancelApprovalTemplate(aprTemplate1);
            // 등록 -> 검수요청
            service.requestTemplate(aprTemplate1);
            // 검수요청 -> 등록
            service.cancelTemplate(aprTemplate1);
        } catch (Exception e) {
            System.out.println(aprTemplate1.getCode() + " / " + e.getMessage());
        }

        System.out.println("---------- 정상프로세스 2 ----------");
        // 정상 프로세스2
        Template regTemplate1 = service.getTemplate("template_code_reg");
        try {

            // 등록 -> 검수요청
            service.requestTemplate(aprTemplate1);
            // 검수요청 -> 등록
            service.cancelTemplate(aprTemplate1);
            // 등록 -> 검수요청
            service.requestTemplate(aprTemplate1);
            // 검수요청 -> 등록
            service.cancelTemplate(aprTemplate1);
        } catch (Exception e) {
            System.out.println(aprTemplate1.getCode() + " / " + e.getMessage());
        }
    }
}
