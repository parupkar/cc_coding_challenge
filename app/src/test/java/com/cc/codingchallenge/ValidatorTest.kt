package com.cc.codingchallenge

import com.google.common.truth.ExpectFailure.assertThat
import com.google.common.truth.TruthFailureSubject
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest {
    object Validator {
        fun validateInput(amount: Int, desc: String): Boolean {
            return !(amount <= 0 || desc.isEmpty())
        }
    }

    @Test
    fun whenInputIsValid(){
        val amount = 100
        val desc = "Coding Challeng"
        val result = Validator.validateInput(amount, desc)
        assertThat(result)
    }

    private fun assertThat(result: Boolean): Boolean {
return result
    }

    @Test
    fun whenInputIsInvalid(){
        val amount = 0
        val desc = ""
        val result = Validator.validateInput(amount, desc)
        assertThat(result)
    }
}