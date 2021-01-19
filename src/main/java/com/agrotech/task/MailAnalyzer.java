package com.agrotech.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class MailAnalyzer {

    public List<User> getUniqueUserMails() {
        Map<String, User> map = new HashMap<>();
        List<User> uniqueUsers = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.lines()
                .takeWhile(textLine -> !textLine.isEmpty())
                .forEach(textLine -> {
                            Set<String> currentUserMails = TextLineParser.parseMailsFromTextLine(textLine);
                            findFirstMailOfExistedUser(currentUserMails, map)
                                    .ifPresentOrElse(mailOfExistedUser -> {
                                                User existedUser = map.get(mailOfExistedUser);
                                                existedUser.addMails(currentUserMails);
                                                currentUserMails.forEach(mail -> map.putIfAbsent(mail, existedUser));
                                            },
                                            () -> {
                                                User currentUser = new User(
                                                        TextLineParser.parseUserNameFromTextLine(textLine), currentUserMails);
                                                currentUserMails.forEach(mail -> map.put(mail, currentUser));
                                                uniqueUsers.add(currentUser);
                                            });
                        }
                );
        return uniqueUsers;
    }

    private Optional<String> findFirstMailOfExistedUser(Set<String> mails, Map<String, User> map) {
        return mails.stream()
                .filter(map::containsKey)
                .findFirst();
    }
}
