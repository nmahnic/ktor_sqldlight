package com.nicomahnic.ktor_sqldlight.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicomahnic.ktor_sqldlight.SpaceXSDK
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RocketLaunchState())
    val uiState: StateFlow<RocketLaunchState> = _uiState.asStateFlow()

    private lateinit var sdk : SpaceXSDK

    fun setupSdk(sdk: SpaceXSDK){
        this.sdk = sdk
    }

    fun displayLaunches(needReload: Boolean) {
        viewModelScope.launch {
            kotlin.runCatching {
                sdk.getLaunches(needReload)
            }.onSuccess {
                _uiState.value = RocketLaunchState(it)
            }.onFailure {
                Log.e("NM", "ERROR: $it")
            }
        }
    }
}