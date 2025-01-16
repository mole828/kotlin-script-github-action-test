@file:DependsOn("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
@file:DependsOn("com.github.kittinunf.fuel:fuel-moshi-jvm:3.0.0-alpha03")


import kotlinx.coroutines.delay
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
import fuel.Fuel
import fuel.get
import kotlin.system.exitProcess

val a = 1

println(a)

val idMaker = AtomicInteger()

suspend fun task() {
    val id = idMaker.incrementAndGet()
    println("task $id started")
    delay(1000)
    println("task $id finished")
}


runBlocking {
    val time = measureTime {
        (1..20).map {
            async { task() }
        }.awaitAll()
    }
    println("time: $time")
}

runBlocking {
    val resp = Fuel.get("https://www.moles.top/ping")
    println(resp.statusCode)
}

exitProcess(0)
