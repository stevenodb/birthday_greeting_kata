package com.cegeka.birthday.domain;

public class BirthdayMessage {
    private final String heading;
    private final String body;

    public BirthdayMessage(String heading, String body) {
        this.heading = heading;
        this.body = body;
    }

    public String getHeading() {
        return heading;
    }

    public String getBody() {
        return body;
    }
}
