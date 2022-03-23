package org.kimbs.design.behavioral.state;

public class Template {

    public enum Status {
        REG, REQ, APR, REJ
    }

    private String name;
    private String contents;
    private String code;
    private TemplateStatus templateStatus = new RegisteredStatus(this);
    private Status status = Status.REG;
    // etc..

    public String getName() {
        return name;
    }

    public String getContents() {
        return contents;
    }

    public String getCode() {
        return code;
    }

    public TemplateStatus getTemplateStatus() {
        return templateStatus;
    }

    public void setTemplateStatus(TemplateStatus templateStatus) {
        this.templateStatus = templateStatus;
    }

    public Status getStatus() {
        return status;
    }

    public void changeStatus(Status status) {
        this.status = status;
    }

    public Template(String name, String contents, String code) {
        this.name = name;
        this.contents = contents;
        this.code = code;
    }
}
