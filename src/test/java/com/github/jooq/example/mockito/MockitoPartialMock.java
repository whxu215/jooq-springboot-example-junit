package com.github.jooq.example.mockito;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by xupanpan on 2017/8/20.
 */
public class MockitoPartialMock {
    @Test
    public void testPartialMock() {
        //you can enable partial mock capabilities selectively on mocks:
        Foo mock = mock(Foo.class);
        //Be sure the real implementation is 'safe'.
        //If real implementation throws exceptions or depends on specific state of the object then you're in trouble.
        when(mock.test1()).thenCallRealMethod();

        Assert.assertThat(mock.test1(), is("111111"));
        Assert.assertNull(mock.test2());
    }

    private class Foo {
        public String test1() {
            return "111111";
        }
        public String test2() {
            return "222222";
        }
    }
}
