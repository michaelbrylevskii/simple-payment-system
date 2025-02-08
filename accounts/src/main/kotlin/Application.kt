package com.michaelbrylevskii.sps.accounts

import com.michaelbrylevskii.sps.common.HelloUtil
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    HelloUtil.sayHello()
    configureSecurity()
    configureFrameworks()
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureDatabases()
    configureRouting()
}
