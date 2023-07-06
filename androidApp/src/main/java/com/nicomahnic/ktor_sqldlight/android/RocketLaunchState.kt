package com.nicomahnic.ktor_sqldlight.android

import com.nicomahnic.ktor_sqldlight.entity.RocketLaunch

data class RocketLaunchState(
    val rocketLaunch: List<RocketLaunch> = emptyList()
)
