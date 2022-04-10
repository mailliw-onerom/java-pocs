package com.mailliwonerom.javapocs.multimodule.validator.domain.services;

import org.springframework.stereotype.Service;

@Service
public class ItinValidatorService {

    private int[] itinNumbers           = new int[11];
    private int[] verificationDigits    = new int[2];

    public boolean dataReceiver(String input) {
        parseToIntArray(input);
        calculateSteps(this.itinNumbers, 10);
        calculateSteps(this.itinNumbers, 11);
        return isMatchingDigits();
    }

    private void calculateSteps(int[] input, int step) {
        int sumOfProduct = 0;

        for(int inverseMult = step, arrPos = 0; inverseMult > 1; inverseMult--) {
            if(arrPos == 9) {
                sumOfProduct += inverseMult * this.verificationDigits[0];
                break;
            }
            sumOfProduct += inverseMult * input[arrPos++];
        }

        setVerificationDigits((sumOfProduct - (sumOfProduct / 11 * 11)), step);
    }

    private void setVerificationDigits(int verificationDigit, int actualStep) {
        if(!(verificationDigit == 0 || verificationDigit == 1)) {
            if(actualStep == 11) {
                this.verificationDigits[1] = 11 - verificationDigit;
            } else {
                this.verificationDigits[0] = 11 - verificationDigit;
            }
        } else {
            if(actualStep == 11) {
                this.verificationDigits[1] = 11 - verificationDigit;
            } else {
                this.verificationDigits[0] = 0;
            }
        }
    }

    private boolean isMatchingDigits() {
        return itinNumbers[9]   ==  verificationDigits[0] &
               itinNumbers[10]  ==  verificationDigits[1];
    }

    private void parseToIntArray(String input) {
        try {
            for(int index = 0; index < input.length(); index++) {
                this.itinNumbers[index] = Character.getNumericValue(
                    input.charAt(index));
            }
        } catch(IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
