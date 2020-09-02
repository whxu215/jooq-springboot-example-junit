package com.github.jooq.example.mockito;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 * Created by xupanpan on 2017/8/20.
 */
public class MockitoStubVoidMethods {

    @Test(expected = RuntimeException.class)
    public void testStubVoidMethodsWithDoThrow() {
        List mockedList = mock(List.class);
        doThrow(new RuntimeException()).when(mockedList).clear();

        //following throws RuntimeException:
        mockedList.clear();
    }

    @Test
    public void testStubVoidMethodsWithDoAnswer() {
        List mockedList = mock(List.class);
        doAnswer(invocation -> {
            System.out.println("do something in do answer");
            return null;
        }).when(mockedList).clear();

        mockedList.clear();
    }
}
