package com.nicomahnic.ktor_sqldlight

import com.nicomahnic.ktor_sqldlight.cache.DatabaseDriverFactory

class SpaceXSDK (databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = SpaceXApi()
}