package com.testapp.mckinleytest

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.testapp.mckinleytest.data.models.Data
import com.testapp.mckinleytest.presentation.CreateUserViewModel
import com.testapp.mckinleytest.presentation.GetUserViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: GetUserViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.List, "list")
                }
            }, title = { Text("Contacts") }, backgroundColor = MaterialTheme.colors.primary)
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "fab icon")
            }
        },
        content = { UserListScreen(viewModel) }
        // UserListScreen(viewModel)

    )
}

@Composable
fun UserListScreen(
    viewModel: GetUserViewModel = hiltViewModel()

) {
    val state = viewModel.userDetailState.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.users) { user ->
                UserListItem(user = user, onItemClick = {
                    // navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                })
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun UserListItem(user: Data, onItemClick: (Data) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { },
        horizontalArrangement = Arrangement.Center

    ) {
        AsyncImage(model = user.avatar, contentDescription = null, modifier = Modifier.height(150.dp).width(100.dp))
        Column(
            modifier = Modifier
                .height(150.dp).wrapContentWidth(Alignment.CenterHorizontally)
                .clickable { onItemClick(user) },
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "${user.firstName}  ${user.lastName}",
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${user.email}",
                color = Color.Black,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        IconButton(onClick = {}, modifier = Modifier.align(Alignment.CenterVertically)) {
            Icon(Icons.Filled.Call, "list", modifier = Modifier.align(CenterVertically))
        }
        IconButton(onClick = {},modifier = Modifier.align(Alignment.CenterVertically)) {
            Icon(Icons.Filled.Message, "list", modifier = Modifier.align(CenterVertically))
        }
    }
}


@Composable
fun createUser(viewModel: CreateUserViewModel) {
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = emailState.text, onValueChange = { it -> viewModel.setEmail(it) }, Modifier.padding(20.dp), label = {
            })

            OutlinedTextField(value = passwordState.text, onValueChange = { viewModel.setPassword(it) }, Modifier.padding(20.dp), label = {
            })

            Button(onClick = {
                viewModel.createUser()
            }, shape = RoundedCornerShape(100)) {
                Text(
                    text = "ADD USER",
                    color = Color.White
                )
            }
        }
    }
}
