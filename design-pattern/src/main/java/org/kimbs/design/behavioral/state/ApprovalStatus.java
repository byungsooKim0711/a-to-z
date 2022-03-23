package org.kimbs.design.behavioral.state;

public class ApprovalStatus implements TemplateStatus {

    @Override
    public Template cancelApprovalTemplate(Template template) {
        template.changeStatus(Status.REG);
        return template;
    }
}
