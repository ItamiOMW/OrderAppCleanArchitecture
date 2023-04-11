package com.example.orderappcleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.orderappcleanarchitecture.core.presentation.navigation.graph.RootNavGraph
import com.example.orderappcleanarchitecture.ui.theme.OrderAppCleanArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrderAppCleanArchitectureTheme {
                RootNavGraph()
            }
        }
    }
}

