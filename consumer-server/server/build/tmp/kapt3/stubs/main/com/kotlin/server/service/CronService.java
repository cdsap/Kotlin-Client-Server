package com.kotlin.server.service;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \r2\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000e"}, d2 = {"Lcom/kotlin/server/service/CronService;", "Ljavax/servlet/http/HttpServlet;", "url", "", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "doGet", "", "req", "Ljavax/servlet/http/HttpServletRequest;", "resp", "Ljavax/servlet/http/HttpServletResponse;", "Companion", "server"})
public final class CronService extends javax.servlet.http.HttpServlet {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String url = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String URL = "https://kotlin-client-server.appspot.com/droidcon/v1/sync/";
    public static final com.kotlin.server.service.CronService.Companion Companion = null;
    
    @java.lang.Override()
    protected void doGet(@org.jetbrains.annotations.Nullable()
    javax.servlet.http.HttpServletRequest req, @org.jetbrains.annotations.Nullable()
    javax.servlet.http.HttpServletResponse resp) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUrl() {
        return null;
    }
    
    public CronService(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/kotlin/server/service/CronService$Companion;", "", "()V", "URL", "", "server"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}