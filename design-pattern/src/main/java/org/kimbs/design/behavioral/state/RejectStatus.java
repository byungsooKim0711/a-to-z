package org.kimbs.design.behavioral.state;

public class RejectStatus implements TemplateStatus {

    @Override
    public Template requestTemplate(Template template) {
        template.changeStatus(Status.REQ);
        return template;
    }
}
