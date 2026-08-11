package com.toptunes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.toptunes.presentation.album_detail.AlbumDetailScreen
import com.toptunes.presentation.album_list.AlbumListRoot
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(AlbumRoute.AlbumListRoute::class, AlbumRoute.AlbumListRoute.serializer())
                    subclass(AlbumRoute.AlbumDetailRoute::class, AlbumRoute.AlbumDetailRoute.serializer())
                }
            }
        },
        AlbumRoute.AlbumListRoute
    )
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = { key ->
            when(key) {
                is AlbumRoute.AlbumListRoute -> {
                    NavEntry(key) {
                        AlbumListRoot(
                            onAlbumSelect = {
                                backStack.add(AlbumRoute.AlbumDetailRoute(it))
                            }
                        )
                    }
                }
                is AlbumRoute.AlbumDetailRoute -> {
                    NavEntry(key) {
                        AlbumDetailScreen(
                            album = key.albumUi,
                            onBackClick = { backStack.removeLastOrNull() }
                        )
                    }
                }
                else -> error("Unknown NavKey: $key")
            }
        }
    )
}