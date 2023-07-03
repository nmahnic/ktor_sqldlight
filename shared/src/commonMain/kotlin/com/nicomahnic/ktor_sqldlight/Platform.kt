package com.nicomahnic.ktor_sqldlight

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform