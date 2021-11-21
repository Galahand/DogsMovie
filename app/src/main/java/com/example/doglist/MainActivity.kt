package com.example.doglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.doglist.presentation.screen.DogsOverviewScreen
import com.example.doglist.presentation.theme.DoglistTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoglistTheme {
                DogsOverviewScreen()
            }
        }
    }
}