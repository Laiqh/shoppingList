package com.javaguru.shoppinglist.service.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.fail;

class AbstractValidationRuleListTest {
    private ValidationRule ruleList;
    private ValidationRule firstRuleToAdd;
    private ValidationRule secondRuleToAdd;
    private Object objectToValidate;

    @BeforeEach
    public void init() {
        ruleList = new AbstractValidationRuleListImpl();
        firstRuleToAdd = Mockito.mock(ValidationRule.class);
        secondRuleToAdd = Mockito.mock(ValidationRule.class);
        objectToValidate = new Object();
        ruleList.add(firstRuleToAdd);
        ruleList.add(secondRuleToAdd);
    }

    @Test
    public void addTwoRules() {
        try {
            ruleList.validate(objectToValidate);
        } catch (Exception e) {
            fail();
        }

        try {
            Mockito.verify(firstRuleToAdd).validate(objectToValidate);
        } catch (Exception e) {
            fail();
        }

        try {
            Mockito.verify(secondRuleToAdd).validate(objectToValidate);
        } catch (Exception e) {
            fail();
        }
    }

    class AbstractValidationRuleListImpl extends AbstractValidationRuleList {

    }
}