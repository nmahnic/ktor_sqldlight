package com.nicomahnic.ktor_sqldlight.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nicomahnic.ktor_sqldlight.SpaceXSDK
import com.nicomahnic.ktor_sqldlight.cache.DatabaseDriverFactory
import com.nicomahnic.ktor_sqldlight.entity.RocketLaunch

class MainActivity : ComponentActivity() {

    private val sdk = SpaceXSDK(DatabaseDriverFactory(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GreetingView(sdk)
                }
            }
        }
    }

}

@Composable
fun RocketLaunchList(rocketLaunchList: List<RocketLaunch>) {
    LazyColumn {
        items(rocketLaunchList) { rocketLaunch ->
            Text("Launch name: ${rocketLaunch.missionName}")
            Text("Launch year: ${rocketLaunch.launchYear}")
            Text("Launch details: ${rocketLaunch.details}")
        }
    }
}

@Composable
fun GreetingView(sdk: SpaceXSDK, viewModel: MainViewModel = viewModel()) {
    viewModel.setupSdk(sdk)

    Column(){
        val rocketLaunchState by viewModel.uiState.collectAsState()
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            onClick = {
                viewModel.displayLaunches(true)
            }
        ) {
            Text("Reload")
        }
        RocketLaunchList(rocketLaunchList = rocketLaunchState.rocketLaunch)
    }
}
