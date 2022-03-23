package org.kimbs.design.behavioral.state;

public class RequestStatus implements TemplateStatus {

    private final Template template;

    public RequestStatus(Template template) {
        this.template = template;
    }

    @Override
    public Template requestTemplate(Template template) {
        throw new UnsupportedOperationException(String.format("%s 상태에서는 검수요청을 할 수 없습니다.", template.getStatus()));
    }

    @Override
    public Template cancelTemplate(Template template) {
        template.setTemplateStatus(new RejectStatus(this.template));
        template.changeStatus(Template.Status.REJ);

        return template;
    }

    @Override
    public Template cancelApprovalTemplate(Template template) {
        throw new UnsupportedOperationException(String.format("%s 상태에서는 승인 취소 요청을 할 수 없습니다.", template.getStatus()));
    }
}
