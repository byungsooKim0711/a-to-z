package org.kimbs.design.behavioral.state;

public interface TemplateStatus {

    default Template requestTemplate(Template template) {
        throw new UnsupportedOperationException(String.format("%s 상태에서는 검수요청을 할 수 없습니다.", template.getStatus()));
    }

    default Template cancelTemplate(Template template) {
        throw new UnsupportedOperationException(String.format("%s 상태에서는 검수취소 요청을 할 수 없습니다.", template.getStatus()));
    }

    default Template cancelApprovalTemplate(Template template) {
        throw new UnsupportedOperationException(String.format("%s 상태에서는 승인 취소 요청을 할 수 없습니다.", template.getStatus()));
    }
}
