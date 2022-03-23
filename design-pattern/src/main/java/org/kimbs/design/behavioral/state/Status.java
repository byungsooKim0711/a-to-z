package org.kimbs.design.behavioral.state;

public enum Status {
    REG(new RegisteredStatus()),
    REQ(new RequestStatus()),
    APR(new ApprovalStatus()),
    REJ(new RejectStatus()),
    ;

    private final TemplateStatus templateStatus;

    Status(TemplateStatus templateStatus) {
        this.templateStatus = templateStatus;
    }

    public TemplateStatus getTemplateStatus() {
        return templateStatus;
    }
}