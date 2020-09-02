package com.github.jooq.example.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.LinkedList;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by xupanpan on 2017/8/20.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoStubTest {
    @Mock
    private LinkedList<String> mockedList;

    @Test
    public void testStub() {
        //You can mock concrete classes, not just interfaces
//        LinkedList mockedList = mock(LinkedList.class);

        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //following prints "first"
        Assert.assertThat(mockedList.get(0), is("first"));

        //following throws runtime exception
//        System.out.println(mockedList.get(1));

        //following prints "null" because get(999) was not stubbed
        Assert.assertNull(mockedList.get(999));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        //If your code doesn't care what get(0) returns, then it should not be stubbed. Not convinced? See here.
        verify(mockedList, times(1)).get(0);
    }

    @Test(expected = RuntimeException.class)
    public void testStubConsecutiveCalls() {
        LinkedList<String> mockedList = mock(LinkedList.class);
        when(mockedList.get(0))
                .thenReturn("foo")
                .thenThrow(new RuntimeException());

        Assert.assertThat(mockedList.get(0), is("foo"));

        mockedList.get(0);
    }

    @Test
    public void testStubWithCallbacks() {
        LinkedList<String> mockedList = mock(LinkedList.class);
        when(mockedList.get(anyInt())).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            //Object mock = invocation.getMock();
            return "called with arguments: " + Arrays.toString(args);
        });

        //the following prints "called with arguments: foo"
        Assert.assertThat(mockedList.get(999), is("called with arguments: [999]"));
    }
}
