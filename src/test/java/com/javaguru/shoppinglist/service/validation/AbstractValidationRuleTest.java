package com.javaguru.shoppinglist.service.validation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AbstractValidationRuleTest {
    private ValidationRule ruleVictim = Mockito.mock(AbstractValidationRule.class, Mockito.CALLS_REAL_METHODS);
    private ValidationRule ruleToAdd = Mockito.mock(ValidationRule.class);

    @Test
    public void addShouldThrowException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            ruleVictim.add(ruleToAdd);
        });
    }
}