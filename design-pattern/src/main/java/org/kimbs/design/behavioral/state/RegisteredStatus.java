package org.kimbs.design.behavioral.state;

public class RegisteredStatus implements TemplateStatus {

    @Override
    public Template requestTemplate(Template template) {
        template.changeStatus(Status.REQ);
        return template;
    }
}
