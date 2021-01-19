package com.agrotech.task;

import java.util.Set;

public class User {
    private final String name;
    private final Set<String> mails;

    public User(String name, Set<String> mails) {
        this.name = name;
        this.mails = mails;
    }

    public void addMails(Set<String> newMails) {
        mails.addAll(newMails);
    }

    @Override
    public String toString() {
        return name + " " + TextLineParser.ARROW + " " + String.join(", ", mails);
    }
}