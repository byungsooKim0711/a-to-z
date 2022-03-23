package org.kimbs.design.behavioral.state;

public class RequestStatus implements TemplateStatus {

    @Override
    public Template cancelTemplate(Template template) {
        template.changeStatus(Status.REG);
        return template;
    }
}
