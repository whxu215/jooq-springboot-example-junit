package com.github.jooq.example.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.util.LinkedList;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by xupanpan on 2017/8/20.
 */
public class MockitoArgumentMatchersTest {
    @Test
    public void testArgumentMatchers() {
        LinkedList<String> mockedList = mock(LinkedList.class);

        //stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");

        //stubbing using custom matcher (let's say isValid() returns your own matcher implementation):
        when(mockedList.contains(argThat(validStringMatcher()))).thenReturn(true);

        Assert.assertThat(mockedList.get(999), is("element"));

        //you can also verify using an argument matcher
        verify(mockedList).get(999);

        Assert.assertThat(mockedList.contains("test123"), is(true));
        Assert.assertThat(mockedList.contains("123456"), is(false));
    }

    private ArgumentMatcher<String> validStringMatcher() {
        return new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object argument) {
                return ((String) argument).startsWith("test");
            }
        };
    }
}
