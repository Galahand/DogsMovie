package com.example.doglist.presentation.screen.components

import androidx.annotation.PluralsRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.doglist.R
import com.example.doglist.presentation.model.DogUi
import com.example.doglist.presentation.theme.DoglistTheme

@Composable
fun DogItem(dog: DogUi) {
    Box(
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            modifier = Modifier.clip(RoundedCornerShape(size = 8.dp))
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.fillMaxWidth(0.3f))
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(
                        text = dog.name,
                        style = MaterialTheme.typography.h6,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = dog.description, style = MaterialTheme.typography.subtitle1)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(
                            id = R.string.dogs_almost_years,
                            dog.age,
                            pluralResource(resId = R.plurals.year, quantity = dog.age)
                        ),
                        style = MaterialTheme.typography.subtitle2,
                    )
                } 
            }
        }
        Image(
            modifier = Modifier
                .fillMaxWidth(0.3f),
            painter = rememberImagePainter(
                data = dog.image,
                builder = {
                    crossfade(true)
                    transformations(RoundedCornersTransformation(dpToPx(8.dp)))
                    placeholder(R.drawable.ic_launcher_foreground)
                    size(DOG_IMAGE_WIDTH, DOG_IMAGE_HEIGHT)
                }
            ),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
    }
}

@Composable
fun pluralResource(
    @PluralsRes resId: Int,
    quantity: Int,
    vararg formatArgs: Any? = emptyArray()
): String {
    return LocalContext.current.resources.getQuantityString(resId, quantity, *formatArgs)
}

@Composable
fun dpToPx(dp: Dp): Float {
    return with(LocalDensity.current) { dp.toPx() }
}

@Preview
@Composable
fun DogItemPreview() {
    DoglistTheme {
        Surface(color = MaterialTheme.colors.background) {
            DogItem(
                DogUi(
                    name = "Firulais",
                    description = "Smoking blanco con negro, sombra de bigote",
                    age = 2,
                    image = "myImage",
                )
            )
        }
    }
}

private const val DOG_IMAGE_WIDTH = 666
private const val DOG_IMAGE_HEIGHT = 999