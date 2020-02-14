package com.javaguru.shoppinglist.service.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AbstractValidationRuleTest {
    private ValidationRule ruleVictim = Mockito.mock(AbstractValidationRule.class, Mockito.CALLS_REAL_METHODS);
    @Mock
    private ValidationRule ruleToAdd;

    @Test
    public void addShouldThrowException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            ruleVictim.add(ruleToAdd);
        });
    }
}