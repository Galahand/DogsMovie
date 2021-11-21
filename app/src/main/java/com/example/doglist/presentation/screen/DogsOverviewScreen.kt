package com.example.doglist.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.doglist.R
import com.example.doglist.domain.model.ResultState
import com.example.doglist.presentation.model.DogUi
import com.example.doglist.presentation.screen.components.DogItem
import com.example.doglist.presentation.utils.getNetworkThrowableMessage
import com.example.doglist.presentation.viewmodel.DogOverviewViewModel
import com.example.doglist.presentation.theme.DoglistTheme
import com.example.doglist.presentation.theme.backButtonColor
import kotlinx.coroutines.launch

@Composable
fun DogsOverviewScreen(
    vm: DogOverviewViewModel = hiltViewModel()
) {
    val dogs = vm.dogs
    val value = dogs.value

    if (value !is ResultState.Success) {
        LaunchedEffect(true) {
            vm.getDogs()
        }
    }

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DogsOverviewAppBar {
                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Not yet implemented!",
                        actionLabel = "Hide",
                        duration = SnackbarDuration.Short,
                    )
                }
            }
        }
    ) {
        when (value) {
            is ResultState.Success -> DogListBody(value.data)
            is ResultState.Loading -> MessageBody(stringResource(id = R.string.dogs_overview_getting_doggos))
            is ResultState.Error -> MessageBody(
                message = stringResource(getNetworkThrowableMessage(value.throwable)),
                retryAction = { vm.getDogs() }
            )
        }
    }
}

@Composable
fun DogListBody(dogs: List<DogUi>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
    ) {
        items(dogs) {
            DogItem(dog = it)
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun MessageBody(
    message: String,
    retryAction: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message)
        Spacer(modifier = Modifier.height(12.dp))
        if (retryAction != null) {
            Button(onClick = retryAction) {
                Text(text = stringResource(R.string.copy_retry))
            }
        }
    }
}

@Composable
fun DogsOverviewAppBar(
    onBackButtonPressed: () -> Unit,
) {
    val buttonSize = 50.dp
    TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = buttonSize),
                text = stringResource(R.string.dogs_overview_app_bar_title),
                textAlign = TextAlign.Center,
            )
        },
        navigationIcon = {
            IconButton(
                modifier = Modifier.size(buttonSize),
                onClick = onBackButtonPressed
            ) {
                Icon(
                    modifier = Modifier.size(35.dp),
                    imageVector = Icons.Rounded.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = backButtonColor()
                )
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}

@Composable
@Preview
fun PreviewScreen() {
    DoglistTheme {
        Surface(color = MaterialTheme.colors.background) {
            DogListBody(dogs = listOf(
                DogUi(
                    name = "Spots",
                    description = "White with black spots, he is a bodyguard.",
                    age = 2,
                    image = "image"
                ),
                DogUi(
                    name = "Chief",
                    description = "Black (formely) White with black spots, he don't trust anyone.",
                    age = 5,
                    image = "image"
                )
            ))
        }
    }
}

@Composable
@Preview
fun PreviewLoading() {
    DoglistTheme {
        Surface(color = MaterialTheme.colors.background) {
            MessageBody("Getting your dogos") {}
        }
    }
}