package com.toptunes

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.toptunes.presentation.navigation.NavigationRoot
import com.toptunes.presentation.theme.TopTunesTheme

@Composable
@Preview
fun App() {
    TopTunesTheme {
        NavigationRoot()
    }
}