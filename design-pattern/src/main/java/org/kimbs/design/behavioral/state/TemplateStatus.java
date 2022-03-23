package org.kimbs.design.behavioral.state;

public interface TemplateStatus {

    public Template requestTemplate(Template template);

    public Template cancelTemplate(Template template);

    public Template cancelApprovalTemplate(Template template);
}
