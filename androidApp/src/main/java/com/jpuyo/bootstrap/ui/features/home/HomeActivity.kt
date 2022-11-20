package com.jpuyo.bootstrap.ui.features.home

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.jpuyo.bootstrap.core.ui.RootActivity

class HomeActivity : RootActivity() {

    companion object {
        fun intent(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }

    @ExperimentalComposeUiApi
    @Composable
    override fun BuildUI() {
        navigator.HomeNavigation()
    }
}
