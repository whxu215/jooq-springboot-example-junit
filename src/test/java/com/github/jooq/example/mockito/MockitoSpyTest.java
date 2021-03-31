package com.github.jooq.example.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by xupanpan on 2017/8/20.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoSpyTest {
    @Spy
    private LinkedList<String> spy;

    @Test
    public void testSpy() {
//        List<String> list = new LinkedList();
//        List<String> spy = spy(list);

        //optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);

        //You have to use doReturn() for stubbing
        doReturn("111").when(spy).get(100);

        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        //prints "one" - the first element of a list
        Assert.assertThat(spy.get(0), is("one"));

        Assert.assertThat(spy.get(100), is("111"));

        //size() method was stubbed - 100 is printed
        Assert.assertThat(spy.size(), is(100));

        //optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");

    }
}
