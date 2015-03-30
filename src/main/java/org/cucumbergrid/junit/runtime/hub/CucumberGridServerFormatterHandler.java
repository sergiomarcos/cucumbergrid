package org.cucumbergrid.junit.runtime.hub;

import cucumber.runtime.junit.JUnitReporter;
import gherkin.formatter.model.*;
import org.cucumbergrid.junit.runtime.common.FormatMessage;
import org.cucumbergrid.junit.runtime.common.FormatMessageID;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class CucumberGridServerFormatterHandler {

    private JUnitReporter jUnitReporter;

    public CucumberGridServerFormatterHandler(JUnitReporter jUnitReporter) {
        this.jUnitReporter = jUnitReporter;
    }

    public void onFormatMessage(FormatMessage message) {
        switch (message.getID()) {
            case SYNTAX_ERROR:
                onSyntaxError(message);
                break;
            case URI:
                onUri(message);
                break;
            case FEATURE:
                onFeature(message);
                break;
            case SCENARIO_OUTLINE:
                onScenarioOutline(message);
                break;
            case EXAMPLES:
                onExamples(message);
                break;
            case START_SCENARIO_LIFE_CYCLE:
                onStartOfScenarioLifeCycle(message);
                break;
            case BACKGROUND:
                onBackground(message);
                break;
            case SCENARIO:
                onScenario(message);
                break;
            case STEP:
                onStep(message);
                break;
            case END_SCENARIO_LIFE_CYCLE:
                onEndOfScenarioLifeCycle(message);
                break;
            case EOF:
                onEOF(message);
                break;
        }
    }

    private void onSyntaxError(FormatMessage message) {
        String state = message.getData(0);
        String event = message.getData(1);
        String[] legalEvents = message.getData(2);
        String uri = message.getData(3);
        Integer line = message.getData(4);
        jUnitReporter.syntaxError(state, event, Arrays.asList(legalEvents), uri, line);
    }

    private void onUri(FormatMessage message) {
        String uri = message.getData(0);
        jUnitReporter.uri(uri);
    }

    private void onFeature(FormatMessage message) {
        Feature feature = message.getData(0);
        jUnitReporter.feature(feature);
    }

    private void onScenarioOutline(FormatMessage message) {
        ScenarioOutline scenarioOutline = message.getData(0);
        jUnitReporter.scenarioOutline(scenarioOutline);
    }

    private void onExamples(FormatMessage message) {
        Examples examples = message.getData(0);
        jUnitReporter.examples(examples);
    }

    private void onStartOfScenarioLifeCycle(FormatMessage message) {
        Scenario scenario = message.getData(0);
        jUnitReporter.startOfScenarioLifeCycle(scenario);
    }

    private void onBackground(FormatMessage message) {
        Background background = message.getData(0);
        jUnitReporter.background(background);
    }

    private void onScenario(FormatMessage message) {
        Scenario scenario = message.getData(0);
        jUnitReporter.scenario(scenario);
    }

    private void onStep(FormatMessage message) {
        Step step = message.getData(0);
        jUnitReporter.step(step);
    }

    private void onEndOfScenarioLifeCycle(FormatMessage message) {
        Scenario scenario = message.getData(0);
        jUnitReporter.endOfScenarioLifeCycle(scenario);
    }

    private void onEOF(FormatMessage message) {
        jUnitReporter.eof();
    }

}