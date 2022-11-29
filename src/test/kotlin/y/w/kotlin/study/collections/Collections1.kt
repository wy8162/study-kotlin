package y.w.kotlin.study.collections

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Collections1 {
    @Test
    fun collectionConstruction() {
        val m = buildMap {
            put(1, "a")
            put(2, "b")
        }
        val s = buildSet<String> {
            add("a")
            add("b")
        }
        val l = buildList<Int> {
            add(1)
            add(2)
        }

        val v: Map<String, Int> = l.associateBy({ it.toString() }, { it })
        assertThat(v["1"]).isEqualTo(1)
        l.associateWith { it.toString() }

        val numbers = listOf("one", "two", "three", "four")
        val listIterator = numbers.listIterator()
        while (listIterator.hasNext()) listIterator.next()
        println("Iterating backwards:")
        while (listIterator.hasPrevious()) {
            print("Index: ${listIterator.previousIndex()}")
            println(", value: ${listIterator.previous()}")
        }
    }

    @Test
    fun testIterator() {
        val l = mutableListOf<Int>(1, 3, 5, 7, 9)
        val iter = l.listIterator()
        while (iter.hasNext()) {
            val v = iter.next()
            if (v == 9) {
                iter.remove()
            } else {
                iter.add(v + 1)
            }
        }

        assertThat(l).containsExactly(1, 2, 3, 4, 5, 6, 7, 8)
    }

    @Test
    fun testSeq1() {
        val oddNumbers = sequence {
            yield(1)
            yieldAll(listOf(3, 5))
            yieldAll(generateSequence(7) { it + 2 })
        }
        println(oddNumbers.take(5).toList())
    }

    @Test
    fun testSeq2() {
        val words = "The quick brown fox jumps over the lazy dog".split(" ")
        val wordsSequence = words.asSequence()

        val lengthsSequence = wordsSequence.filter { println("filter: $it"); it.length > 3 }
            .map { println("length: ${it.length}"); it.length }
            .take(4)

        println("Lengths of first 4 words longer than 3 chars")
        println(lengthsSequence.toList())
    }
}
