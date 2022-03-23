package org.kimbs.design.behavioral.state;

public class Template {

    private String name;
    private String contents;
    private String code;
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

    @Override
    public String toString() {
        return "Template{" +
                "name='" + name + '\'' +
                ", contents='" + contents + '\'' +
                ", code='" + code + '\'' +
                ", status=" + status +
                '}';
    }
}
