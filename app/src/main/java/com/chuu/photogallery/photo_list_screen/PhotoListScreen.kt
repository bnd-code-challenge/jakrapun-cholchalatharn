package com.chuu.photogallery.photo_list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.chuu.photo_service.data.model.PhotoItem
import com.chuu.photogallery.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun PhotoListScreen(
    modifier: Modifier = Modifier,
    viewModel: PhotoListScreenViewModel = koinViewModel(),
    onItemClick: (String, String) -> Unit = {_, _, ->}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PhotoListScreen(
        uiState = uiState,
        onItemClick
    )

}
@Composable
fun PhotoListScreen(
    uiState: PhotoListScreenViewModel.UIState,
    onItemClick: (String, String) -> Unit = {_, _, ->}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize()
                .padding(it),
            columns = GridCells.Fixed(2)
        ) {
            items(uiState.photos) { item ->
                PhotoItemView(
                    item,
                    onItemClick
                )
            }
        }
    }
}

@Composable
fun PhotoItemView(
    photoItem: PhotoItem,
    onItemClick: (String, String) -> Unit = {_, _, ->}
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(24.dp)
            .clickable { 
                onItemClick(
                    photoItem.url,
                    photoItem.title
                )
            },
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = photoItem.thumbnailUrl,
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            placeholder = painterResource(R.drawable.placeholder)
        )

        Text(
            text = photoItem.title,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun PhotoListScreenPreview() {
    PhotoListScreen(
        uiState = PhotoListScreenViewModel.UIState(
            photos = List(size = 10) {
                PhotoItem(
                    albumId = 0,
                    id = 0,
                    title = "Photo 1",
                    url = "",
                    thumbnailUrl = ""
                )
            }
        )
    )
}