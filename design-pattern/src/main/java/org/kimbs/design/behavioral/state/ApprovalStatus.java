package org.kimbs.design.behavioral.state;

public class ApprovalStatus implements TemplateStatus {

    private final Template template;

    public ApprovalStatus(Template template) {
        this.template = template;
    }
    @Override
    public Template requestTemplate(Template template) {
        throw new UnsupportedOperationException(String.format("%s 상태에서는 검수요청을 할 수 없습니다.", template.getStatus()));
    }

    @Override
    public Template cancelTemplate(Template template) {
        throw new UnsupportedOperationException(String.format("%s 상태에서는 검수취소 요청을 할 수 없습니다.", template.getStatus()));
    }

    @Override
    public Template cancelApprovalTemplate(Template template) {
        template.setTemplateStatus(new RegisteredStatus(this.template));
        template.changeStatus(Template.Status.REG);

        return template;
    }
}
