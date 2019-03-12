package com.fse.pm.common.presentation.controller;

import org.junit.Test;
import static org.junit.Assert.*;

public class IndexControllerTest {
    @Test
    public void index() {
        assertEquals("index.html", new IndexController().index());
    }
}