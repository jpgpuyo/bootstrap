package com.jpuyo.bootstrap.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.jpuyo.bootstrap.navigation.Navigator
import com.jpuyo.bootstrap.ui.theme.AppTheme
import org.koin.android.ext.android.inject

abstract class RootActivity : ComponentActivity() {

    protected val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                BuildUI()
            }
        }
    }

    @Composable
    abstract fun BuildUI()
}