package com;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        tags = "@test",
        plugin = {"pretty", "html:target/cucumber-html-report"},
        features="src/test/resources",
        glue = {"com.support.hooks", "com.admin.stepdef", "com.agency.stepDef", "com.chat.stepDef"},
        dryRun = false
)
public class TestRunner {

}