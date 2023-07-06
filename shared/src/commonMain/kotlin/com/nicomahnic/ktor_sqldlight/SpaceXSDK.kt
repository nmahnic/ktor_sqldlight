package com.nicomahnic.ktor_sqldlight

import com.nicomahnic.ktor_sqldlight.cache.Database
import com.nicomahnic.ktor_sqldlight.cache.DatabaseDriverFactory
import com.nicomahnic.ktor_sqldlight.entity.RocketLaunch
import com.nicomahnic.ktor_sqldlight.network.SpaceXApi

class SpaceXSDK (databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = SpaceXApi()

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }
}