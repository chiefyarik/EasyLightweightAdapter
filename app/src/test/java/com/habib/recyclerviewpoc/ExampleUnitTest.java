package com.habib.recyclerviewpoc;

import com.habib.recyclerviewpoc.models.ThisIsPojo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @ThisIsPojo
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}