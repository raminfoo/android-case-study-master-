package com.target.targetcasestudy.data

import java.util.regex.Pattern

/**
 * For an explanation of how to validate credit card numbers read:
 *
 * https://www.freeformatter.com/credit-card-number-generator-validator.html#fakeNumbers
 *
 * This contains a breakdown of how this algorithm should work as
 * well as a way to generate fake credit card numbers for testing
 *
 * The structure and signature of this is open to modification, however
 * it *must* include a method, field, etc that returns a [Boolean]
 * indicating if the input is valid or not
 *
 * Additional notes:
 *  * This method does not need to validate the credit card issuer
 *  * This method must validate card number length (13 - 19 digits), but does not
 *    need to validate the length based on the issuer.
 *
 * @param creditCardNumber - credit card number of (13, 19) digits
 * @return true if a credit card number is believed to be valid,
 * otherwise false
 */
fun validateCreditCard(creditCardNumber: String): Boolean {
    val regVisa = Regex("^4[0-9]{12}(?:[0-9]{3})?$")
    val regMaster = Regex("^5[1-5][0-9]{14}$")
    val regExpress = Regex("^3[47][0-9]{13}$")
    val regDiners = Regex("^3(?:0[0-5]|[68][0-9])[0-9]{11}$")
    val regDiscover = Regex("^6(?:011|5[0-9]{2})[0-9]{12}$")
    val regJCB = Regex("^(?:2131|1800|35\\d{3})\\d{11}$")

    return when {
        regVisa.matches(creditCardNumber) || //"VISA"
                regMaster.matches(creditCardNumber) || // -> "MASTER"
                regExpress.matches(creditCardNumber) || //-> "AEXPRESS"
                regDiners.matches(creditCardNumber) || // -> "DINERS"
                regDiscover.matches(creditCardNumber) || //-> "DISCOVERS"
                regJCB.matches(creditCardNumber) -> true //"JCB"
        else -> false //"INVALID"
    }
}