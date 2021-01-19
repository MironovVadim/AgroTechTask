package com.agrotech.task;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class MailAnalyzerTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private final MailAnalyzer mailAnalyzer = new MailAnalyzer();
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void whenFiveUsersInThenTwoOut() {
        String testData = "user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru\n" +
                "user2 -> foo@gmail.com, ups@pisem.net\n" +
                "user3 -> xyz@pisem.net, vasya@pupkin.com\n" +
                "user4 -> ups@pisem.net, aaa@bbb.ru\n" +
                "user5 -> xyz@pisem.net\n";
        System.setIn(new ByteArrayInputStream(testData.getBytes()));
        mailAnalyzer.getUniqueUserMails().forEach(System.out::println);

        String expected = "user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru, ups@pisem.net, aaa@bbb.ru\n" +
                "user3 -> xyz@pisem.net, vasya@pupkin.com\n";
        String actual = testOut.toString();

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void whenOneUsersInThenOneOut() {
        String testData = "user5 -> xyz@pisem.net\n";
        System.setIn(new ByteArrayInputStream(testData.getBytes()));
        mailAnalyzer.getUniqueUserMails().forEach(System.out::println);

        String expected = "user5 -> xyz@pisem.net\n";
        String actual = testOut.toString();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void whenTwoUsersInThenTwoOut() {
        String testData = "user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru\n" +
                "user3 -> xyz@pisem.net, vasya@pupkin.com\n";
        System.setIn(new ByteArrayInputStream(testData.getBytes()));
        mailAnalyzer.getUniqueUserMails().forEach(System.out::println);

        String expected = "user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru\n" +
                "user3 -> xyz@pisem.net, vasya@pupkin.com\n";
        String actual = testOut.toString();

        Assert.assertEquals(expected, actual);
    }
}
