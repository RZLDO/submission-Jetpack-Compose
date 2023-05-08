package com.example.submissioncompose.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.submissioncompose.model.AnimalData
import com.example.submissioncompose.ui.theme.SubmissionComposeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JetAnimalApp(
    modifier: Modifier = Modifier,
){
    val groupedAnimal = AnimalData.animal
        .sortedBy { it.animalName }
        .groupBy { it.animalName[0] }
    Box(modifier = modifier){
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton : Boolean by remember{
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn (
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp, top = 16.dp)
        ){
            groupedAnimal.forEach{
                (initial, animals) ->
                stickyHeader {
                    CharacterHeader(initial)
                }
                items(animals, key = {it.id}){ animal ->
                    AnimalListItem(
                        name = animal.animalName,
                        photoUrl = animal.photoUrl,
                        description = animal.description,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            ScrollToTopButton(
                onClick = {
                scope.launch {
                    listState.scrollToItem(index = 0)
                }
            })
        }
    }
}
@Composable
fun CharacterHeader(
    char : Char,
    modifier: Modifier = Modifier
){
    Surface(color = MaterialTheme.colorScheme.primary,modifier = modifier) {
        Text(
            text = char.toString(),
            fontWeight = FontWeight.Black,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}
@Composable
fun AnimalListItem(
    name: String,
    photoUrl : String,
    description: String,
    modifier: Modifier = Modifier
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable {}
    ){
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
            )
        Column(
            modifier = modifier){
            Text(
                text = name,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            )
            Text(
                text = description,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
    }
}

@Composable
fun ScrollToTopButton(
    onClick :() -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        modifier = modifier
            .shadow(elevation = 10.dp, shape = CircleShape)
            .clip(shape = CircleShape)
            .size(56.dp),
        colors = ButtonDefaults.buttonColors(
            Color.White,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ){
        Icon(
            Icons.Filled.KeyboardArrowUp,
            contentDescription = null,
            modifier = Modifier.scale(3f)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun JetAnimalAppPreview(){
    SubmissionComposeTheme {
        JetAnimalApp()
    }
}

@Preview(showBackground = true)
@Composable
fun AnimalListItemPreview(){
    SubmissionComposeTheme {
        AnimalListItem(
            name = "Kuda",
            photoUrl = "",
            description = "Kuda terbang")
    }
}