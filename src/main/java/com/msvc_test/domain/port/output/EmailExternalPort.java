package com.msvc_test.domain.port.output;

import java.util.concurrent.CompletableFuture;

public interface EmailExternalPort {
    CompletableFuture<Void> sendEmail(String to, String from, String subject, String body, String htmlText);
}
