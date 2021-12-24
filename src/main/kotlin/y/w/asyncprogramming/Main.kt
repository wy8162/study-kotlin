package y.w.asyncprogramming

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val format = "%-10s%-20s%-10s"
    println(String.format(format, "Code", "Temperature", "Delay"))

    val time = measureTimeMillis {
        val airportCodes = listOf("LAX", "SFO", "PDX", "SEA", "EWR", "IAD", "PEK-")

        val airportData: List<Deferred<Airport?>> =
            airportCodes.map { anAirportCode ->
                async(Dispatchers.IO + SupervisorJob()) {
                    Airport.getAirportData(anAirportCode)
                }
            }

//        airportData
//            .mapNotNull { it -> it.await() }
//            .forEach {
//                println(String.format(format, it.code, it.weather.temperature.get(0), it.delay))
//            }
        for (anAirportData in airportData) {
            try {
                val airport = anAirportData.await()
                println(String.format(format, airport?.code, airport?.weather?.temperature?.get(0), airport?.delay))
            } catch (e: Exception) {
                println("Error: ${e.message?.substring(0..28)}")
            }
        }
    }

    println("Time taken: $time ms")
}
