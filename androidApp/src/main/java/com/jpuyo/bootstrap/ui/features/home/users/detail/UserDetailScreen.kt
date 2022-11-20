package com.jpuyo.bootstrap.ui.features.home.users.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.sharp.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.jpuyo.bootstrap.R
import com.jpuyo.bootstrap.features.users.domain.model.User
import com.jpuyo.bootstrap.ui.theme.AppTheme
import com.jpuyo.bootstrap.ui.theme.AppTypography

@Composable
fun UserDetailScreen(
    userId: String?,
    userDetailState: UserDetailState,
    onTriggerEvent: (UserDetailEvents) -> Unit,
    onBackClicked: () -> Unit
) {
    val listState = rememberLazyListState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.user_detail_title),
                        style = MaterialTheme.typography.h5
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onBackClicked() }
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.secondary,
            )
        },
        content = {
            Surface(
                color = MaterialTheme.colors.secondary, modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                LazyColumn(Modifier.fillMaxSize(), state = listState) {
                    item {
                        UserDetails(userDetailState.user)
                    }
                }
            }
        }
    )
    LaunchedEffect(Unit) {
        userId?.let {
            onTriggerEvent(UserDetailEvents.GetUserDetail(userId = it))
        }
    }
}

@Composable
fun UserDetails(
    user: User
) {
    Surface(color = MaterialTheme.colors.secondary) {
        Column {
            UserDetailHeader(user)
            Column(Modifier.padding(all = 16.dp)) {
                UserDetailRow(
                    title = stringResource(id = R.string.user_detail_id),
                    description = user.id
                )
                Spacer(modifier = Modifier.height(16.dp))
                UserDetailRow(
                    title = stringResource(id = R.string.user_detail_username),
                    description = user.fullName
                )
                Spacer(modifier = Modifier.height(16.dp))
                UserDetailRow(
                    title = stringResource(id = R.string.user_detail_email),
                    description = user.email
                )
            }
        }
    }
}

@Composable
fun UserDetailHeader(
    user: User
) {
    Column(
        Modifier
            .background(color = MaterialTheme.colors.primary)
            .fillMaxWidth()
    ) {
        Row(Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImage(
                    model = user.avatar,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    loading = { PlaceholderImage(Icons.Sharp.Person) },
                    contentScale = ContentScale.FillBounds,
                )
            }
        }
    }
}

@Composable
fun PlaceholderImage(imageVector: ImageVector) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary),
        contentAlignment = Alignment.TopCenter
    ) {
        Icon(
            imageVector,
            contentDescription = null,
            tint = MaterialTheme.colors.secondary,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
    }
}

@Composable
fun UserDetailRow(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.onSecondary
) {
    Column(modifier) {
        Text(
            text = title.uppercase(),
            style = AppTypography.body2.copy(
                color = textColor,
                fontWeight = FontWeight.Thin
            )
        )
        Text(
            text = description,
            style = AppTypography.body1.copy(color = textColor),
            lineHeight = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailScreenPreview() {
    val user = User(
        id = "7",
        email = "michael.lawson@reqres.in",
        fullName = "Michael Lawson",
        avatar = "https://reqres.in/img/faces/7-image.jpg"
    )
    AppTheme {
        UserDetailScreen(
            userId = "7",
            userDetailState = UserDetailState(user),
            onTriggerEvent = {},
            onBackClicked = {})
    }
}