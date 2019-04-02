package com.zh.common.taskmanger.entity;

import java.io.Serializable;

public final class Foo implements Serializable {

    private static final long serialVersionUID = 2706842871078949451L;

    private final String id; //id

    private final String location; //本地

    private final String bak; //备用字段

    private Status status;

    public Foo(final String id, final String location, final String bak, final Status status) {
        this.id = id;
        this.location = location;
        this.bak = bak;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getBak() {
        return bak;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public String toString() {
        return String.format("id: %s, location: %s, status: %s", id, location, bak, status);
    }

    public enum Status {
        TODO,
        COMPLETED
    }
}