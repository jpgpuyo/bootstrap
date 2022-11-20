package com.jpuyo.bootstrap.ui.features.home.users.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.jpuyo.bootstrap.R
import com.jpuyo.bootstrap.features.users.domain.model.User
import com.jpuyo.bootstrap.ui.theme.AppTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserListScreen(
    state: UsersListState,
    onTriggerEvent: (UserListEvents) -> Unit,
    onUserClicked: (userId: String) -> Unit
) {
    val isRefreshing by remember { mutableStateOf(state.isRefreshing) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.user_list_title),
                        style = MaterialTheme.typography.h5
                    )
                },
                backgroundColor = MaterialTheme.colors.secondary,
            )
        },
        content = {
            when {
                state.isLoading -> LoadingView()
                else -> UserListView(
                    isRefreshing,
                    onTriggerEvent,
                    state,
                    onUserClicked
                )
            }
        })
    LaunchedEffect(Unit) {
        onTriggerEvent(UserListEvents.GetUsers)
    }
}

@Composable
private fun LoadingView() {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.secondary)
            .fillMaxSize()
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun EmptyView(errorMessage: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = errorMessage.ifEmpty { stringResource(id = R.string.user_list_empty) },
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
private fun UserListView(
    isRefreshing: Boolean,
    onTriggerEvent: (UserListEvents) -> Unit,
    state: UsersListState,
    onUserClicked: (userId: String) -> Unit
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = { onTriggerEvent(UserListEvents.GetUsers) }) {
        LazyColumn(
            Modifier
                .background(MaterialTheme.colors.secondary)
                .fillMaxSize()
                .padding(
                    horizontal = 16.dp,
                    vertical = 15.dp
                )
        ) {
            items(state.users) { user ->
                UserDetail(
                    user = user,
                    onItemClicked = onUserClicked
                )
            }
        }
        if (state.isEmptyView) {
            EmptyView(state.errorMessage)
        }
    }
}

@Composable
fun UserDetail(
    user: User,
    onItemClicked: (userId: String) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(15.dp), modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable {
                onItemClicked(user.id)
            }
    ) {
        Column(
            Modifier
                .background(color = MaterialTheme.colors.primary)
                .fillMaxSize()
                .padding(start = 22.dp, end = 22.dp, top = 16.dp, bottom = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = user.fullName,
                style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onPrimary),
                modifier = Modifier.align(Alignment.Start),
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    DetailText(
                        text1 = stringResource(R.string.user_list_id),
                        text2 = user.id
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                    DetailText(
                        text1 = stringResource(R.string.user_list_email),
                        text2 = user.email
                    )
                }
            }
        }
    }
}

@Composable
fun DetailText(text1: String, text2: String) {
    Column {
        Text(
            text = text1,
            style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onPrimary)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = text2,
            style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onPrimary)
        )
    }
}

@Preview
@Composable
fun EmptyViewPreview() {
    AppTheme {
        EmptyView(errorMessage = "")
    }
}

@Preview
@Composable
fun UserPreview() {
    AppTheme {
        UserDetail(
            user = User(
                id = "7",
                email = "michael.lawson@reqres.in",
                fullName = "Michael Lawson",
                avatar = "https://reqres.in/img/faces/7-image.jpg"
            ),
            onItemClicked = {})
    }
}

@Preview
@Composable
fun UserListScreenPreview() {
    val user = User(
        id = "7",
        email = "michael.lawson@reqres.in",
        fullName = "Michael Lawson",
        avatar = "https://reqres.in/img/faces/7-image.jpg"
    )
    AppTheme {
        UserListScreen(
            state = UsersListState(
                listOf(user)
            ),
            onTriggerEvent = {},
            onUserClicked = {}
        )
    }
}
