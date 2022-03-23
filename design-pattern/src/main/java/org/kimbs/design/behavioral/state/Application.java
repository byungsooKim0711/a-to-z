package org.kimbs.design.behavioral.state;

public class Application {

    public static void main(String[] args) {
        TemplateService service = new TemplateService();

        Template regTemplate = service.getTemplate("template_code_reg");
        try {
            service.requestTemplate(regTemplate);
            service.cancelApprovalTemplate(regTemplate);
            service.cancelTemplate(regTemplate);
        } catch (Exception e) {
            System.out.println(regTemplate.getCode() + " / " + e.getMessage());
        }

        System.out.println("----------------------------------");

        Template reqTemplate = service.getTemplate("template_code_req");
        try {
            service.requestTemplate(reqTemplate);
            service.cancelApprovalTemplate(reqTemplate);
            service.cancelTemplate(reqTemplate);
        } catch (Exception e) {
            System.out.println(reqTemplate.getCode() + " / " + e.getMessage());
        }

        System.out.println("----------------------------------");

        Template aprTemplate = service.getTemplate("template_code_apr");
        try {
            service.requestTemplate(aprTemplate);
            service.cancelApprovalTemplate(aprTemplate);
            service.cancelTemplate(aprTemplate);
        } catch (Exception e) {
            System.out.println(aprTemplate.getCode() + " / " + e.getMessage());
        }

        System.out.println("----------------------------------");

        Template rejTemplate = service.getTemplate("template_code_rej");
        try {
            service.requestTemplate(rejTemplate);
            service.cancelApprovalTemplate(rejTemplate);
            service.cancelTemplate(rejTemplate);
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
