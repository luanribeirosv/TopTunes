package com.toptunes.presentation.album_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.toptunes.presentation.component.EmptyAlbumsState
import com.toptunes.presentation.component.TopTunesSnackbarScaffold
import com.toptunes.presentation.model.AlbumUi
import com.toptunes.presentation.theme.TopTunesTheme
import com.toptunes.presentation.util.ObserveAsEvents
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import toptunes.presentation.generated.resources.Res
import toptunes.presentation.generated.resources.top_albums

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumListRoot(
    onAlbumSelect: (AlbumUi) -> Unit,
    viewModel: AlbumListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is AlbumListEvent.OnError -> {
                snackbarHostState.showSnackbar(event.error.asStringAsync())
            }
        }
    }

    AlbumListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is AlbumListAction.OnSelectAlbum -> onAlbumSelect(action.album)
            }
            viewModel.onAction(action)
        },
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun AlbumListScreen(
    state: AlbumListState,
    onAction: (AlbumListAction) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    TopTunesSnackbarScaffold(
        snackbarHostState = snackbarHostState
    ) {
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(70.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }

            return@TopTunesSnackbarScaffold
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(Res.string.top_albums),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
            )

            if (state.albums.isEmpty()) {
                EmptyAlbumsState(modifier = Modifier.weight(1f))
                return@TopTunesSnackbarScaffold
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = WindowInsets.navigationBars.asPaddingValues()
            ) {
                itemsIndexed(state.albums, key = { _, album -> album.id }) { index, album ->
                    AlbumListItem(
                        rank = index + 1,
                        album = album,
                        onClick = {
                            onAction(
                                AlbumListAction.OnSelectAlbum(album)
                            )
                        }
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 128.dp),
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AlbumListScreenPreview() {
    TopTunesTheme {
        AlbumListScreen(
            state = AlbumListState(
                isLoading = false,
                albums = listOf(
                    AlbumUi(
                        id = "1",
                        name = "Sample Album",
                        artist = "Sample Artist",
                        urlImage = "",
                        formattedReleaseDate = "01/01/2024",
                        category = "Pop",
                        price = "$9.99"
                    ),
                    AlbumUi(
                        id = "2",
                        name = "Another Album",
                        artist = "Another Artist",
                        urlImage = "",
                        formattedReleaseDate = "02/02/2024",
                        category = "Rock",
                        price = "$7.99"
                    )
                )
            ),
            onAction = {},
            snackbarHostState = remember { SnackbarHostState() }
        )
    }
}
