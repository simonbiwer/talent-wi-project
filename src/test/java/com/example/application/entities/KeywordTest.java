package com.example.application.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KeywordTest {

    private Keyword keyword;

    @BeforeEach
    void setUp() {
        keyword = new Keyword();
        keyword.setKeywordid(1);
        keyword.setKeywordname("Java");
    }

    @Test
    void testGetKeywordid() {
        assertEquals(1, keyword.getKeywordid());
    }

    @Test
    void testSetKeywordid() {
        keyword.setKeywordid(2);
        assertEquals(2, keyword.getKeywordid());
    }

    @Test
    void testGetKeywordname() {
        assertEquals("Java", keyword.getKeywordname());
    }

    @Test
    void testSetKeywordname() {
        keyword.setKeywordname("Python");
        assertEquals("Python", keyword.getKeywordname());
    }

    @Test
    void testEquals() {
        Keyword keyword2 = new Keyword();
        keyword2.setKeywordid(1);
        keyword2.setKeywordname("Java");
        assertTrue(keyword.equals(keyword2));
    }

    @Test
    void testHashCode() {
        Keyword keyword2 = new Keyword();
        keyword2.setKeywordid(1);
        keyword2.setKeywordname("Java");
        assertEquals(keyword.hashCode(), keyword2.hashCode());
    }
}