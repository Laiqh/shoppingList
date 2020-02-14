package com.javaguru.shoppinglist.service.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AbstractValidationRuleListTest {
    @Mock
    private ValidationRule firstRuleToAdd;
    @Mock
    private ValidationRule secondRuleToAdd;
    private ValidationRule ruleList;
    private Object objectToValidate;

    @BeforeEach
    public void init() {
        ruleList = new AbstractValidationRuleListImpl();
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
            verify(firstRuleToAdd).validate(objectToValidate);
        } catch (Exception e) {
            fail();
        }

        try {
            verify(secondRuleToAdd).validate(objectToValidate);
        } catch (Exception e) {
            fail();
        }
    }

    class AbstractValidationRuleListImpl extends AbstractValidationRuleList {

    }
}