package com.agrotech.task;

public class AgroTechTaskApplication {

    public static void main(String[] args) {
        new MailAnalyzer()
                .getUniqueUserMails()
                .forEach(System.out::println);
    }
}
