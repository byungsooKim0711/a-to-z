package org.kimbs.design.behavioral.state;

import java.util.HashMap;
import java.util.Map;

public class TemplateService {

    private static final Map<String, Template> db = new HashMap<>();

    static {
        // 등록 상태 템플릿
        db.put("template_code_reg", new Template("템플릿1", "템플릿내용1", "template_code_reg"));

        // 검수요청 상태 템플릿
        Template reqTemplate = new Template("템플릿2", "템플릿내용2", "template_code_req");
        reqTemplate.setTemplateStatus(new RequestStatus(reqTemplate));
        reqTemplate.changeStatus(Template.Status.REQ);
        db.put("template_code_req", reqTemplate);

        // 승인 상태 템플릿
        Template aprTemplate = new Template("템플릿3", "템플릿내용3", "template_code_apr");
        aprTemplate.setTemplateStatus(new ApprovalStatus(aprTemplate));
        aprTemplate.changeStatus(Template.Status.APR);
        db.put("template_code_apr", aprTemplate);

        // 반려 상태 템플릿
        Template rejTemplate = new Template("템플릿4", "템플릿내용4", "template_code_rej");
        rejTemplate.setTemplateStatus(new RejectStatus(rejTemplate));
        rejTemplate.changeStatus(Template.Status.REJ);
        db.put("template_code_rej", aprTemplate);
    }

    public Template getTemplate(String templateCode) {
        Template selected = db.get(templateCode);
        if (selected == null) {
            throw new IllegalArgumentException(String.format("template not found. code: %s", templateCode));
        }

        return selected;
    }

    public Template requestTemplate(Template template) {
        Template response = template.getTemplateStatus().requestTemplate(template);

        // doSomething...
        System.out.println(String.format("code: %s 검수요청 완료.", template.getCode()));

        return response;
    }

    public Template cancelTemplate(Template template) {
        Template response = template.getTemplateStatus().cancelTemplate(template);

        // doSomething...
        System.out.println(String.format("code: %s 검수취소 완료.", template.getCode()));

        return response;
    }

    public Template cancelApprovalTemplate(Template template) {
        Template response = template.getTemplateStatus().cancelApprovalTemplate(template);

        // doSomething...
        System.out.println(String.format("code: %s 승인취소 완료.", template.getCode()));

        return response;
    }
}
