package com.chuu.photogallery.photo_detail_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.chuu.photogallery.R

@Composable
fun PhotoDetailScreen(
    imageUrl: String,
    title: String
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        AsyncImage(
            model = imageUrl,
            modifier = Modifier.fillMaxWidth()
                .height(400.dp),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            placeholder = painterResource(id = R.drawable.placeholder)
        )

        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PhotoDetailScreenPreview() {
    PhotoDetailScreen(
        imageUrl = "",
        title = ""
    )
}