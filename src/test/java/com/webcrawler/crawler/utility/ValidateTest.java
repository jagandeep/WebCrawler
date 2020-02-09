package com.webcrawler.crawler.utility;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidateTest {

    @Test
    public void testValidateUrl() {
        Validate validate = new Validate();
        assertFalse(validate.validateUrl("mailto:eakn1@york.ac.uk"));
    }
}