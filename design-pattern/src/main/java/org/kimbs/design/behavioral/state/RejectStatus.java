package org.kimbs.design.behavioral.state;

public class RejectStatus implements TemplateStatus {

    private final Template template;

    public RejectStatus(Template template) {
        this.template = template;
    }

    @Override
    public Template requestTemplate(Template template) {
        template.setTemplateStatus(new RequestStatus(this.template));
        template.changeStatus(Template.Status.REQ);

        return template;
    }

    @Override
    public Template cancelTemplate(Template template) {
        throw new UnsupportedOperationException(String.format("%s 상태에서는 검수취소를 할 수 없습니다.", template.getStatus()));
    }

    @Override
    public Template cancelApprovalTemplate(Template template) {
        throw new UnsupportedOperationException(String.format("%s 상태에서는 승인 취소 요청을 할 수 없습니다.", template.getStatus()));
    }
}
