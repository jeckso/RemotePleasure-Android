package com.jeckso.remotepleasure.domain.validation.base

import android.os.Build
import android.util.Patterns
import androidx.annotation.RequiresApi
import com.jeckso.remotepleasure.domain.validation.exceptions.EmptyFieldException
import java.util.function.Predicate
import java.util.regex.Pattern

private const val PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}"

@RequiresApi(Build.VERSION_CODES.N)
private val EMAIL_PREDICATE = Patterns.EMAIL_ADDRESS.asPredicate()

@RequiresApi(Build.VERSION_CODES.N)
private val PASSWORD_PREDICATE = Pattern.compile(PASSWORD_REGEX).asPredicate()

private val EMPTINESS_PREDICATE = Predicate<String> { it.isNotBlank() }

@RequiresApi(Build.VERSION_CODES.N)
private val PHONE_PREDICATE = Patterns.PHONE.asPredicate()

@RequiresApi(Build.VERSION_CODES.N)
val EMAIL_VALIDATOR = BaseValidator(EMAIL_PREDICATE)

@RequiresApi(Build.VERSION_CODES.N)
val PASSWORD_VALIDATOR = BaseValidator(PASSWORD_PREDICATE)

@RequiresApi(Build.VERSION_CODES.N)
val PHONE_VALIDATOR = BaseValidator(PHONE_PREDICATE)

val EMPTINESS_VALIDATOR = object : Validator<String> {

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun validate(input: String) {
        if (!EMPTINESS_PREDICATE.test(input)) {
            throw EmptyFieldException()
        }
    }
}