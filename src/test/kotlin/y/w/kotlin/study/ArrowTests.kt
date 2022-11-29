package y.w.kotlin.study

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.getOrElse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ArrowTests {
    @Test
    fun test1() {
        val value = getOne("1")
            .flatMap { assertValue(it) }

        assertThat(value.orNull()).isEqualTo("1, is great")
    }

    @Test
    fun test2() {
        val value = getOne("2")
            .flatMap { assertValue(it) }

        assertThat(value.getOrElse { "It is left" }).isEqualTo("It is left")
    }

    private fun getOne(one: String): Either<String, String> {
        return if (one == "1") Either.Right(one) else Either.Left("Error")
    }

    private fun assertValue(one: String): Either<String, String> {
        return if (one == "1") Either.Right("$one, is great") else Either.Left("Error")
    }
}
