package com.js.graphicdisplay;

import com.js.graphicdisplay.util.FileUtil;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void readFile() throws Exception {
//        String file = "./newTable.txt";
        String file = "/home/laf/Documents/gitP/android-graphicdisplay/GraphicDisplay/app/src/test/java/com/js/graphicdisplay/newTable.txt";
        String json = FileUtil.readToString(file);
        System.out.println(json);
    }
}

