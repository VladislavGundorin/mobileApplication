package com.example.mobileapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.example.mobileapplication.ui.theme.MobileApplicationTheme

data class Chat(val name: String, val message: String, val time: String, val imageUrl: String)

@Composable
fun ChatItem(chat: Chat) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(chat.imageUrl),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = chat.name, fontWeight = FontWeight.Bold)
            Text(text = chat.message, maxLines = 1)
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = chat.time, fontSize = 12.sp)
    }
}

@Composable
fun ChatList(chats: List<Chat>) {
    LazyColumn {
        items(chats.size) { index ->
            ChatItem(chat = chats[index])
        }
    }
}

@Composable
fun HomeScreen() {
    val chats = listOf(
        Chat("Вика", "Привет! Гулть пойдешь?", "10:30", "https://rickandmortyapi.com/api/character/avatar/393.jpeg"),
        Chat("Марк", "чек мем", "09:15", "https://rickandmortyapi.com/api/character/avatar/730.jpeg"),
        Chat("Денис", "Увидимся завтра.", "Вчера", "https://rickandmortyapi.com/api/character/avatar/552.jpeg")
    )

    ChatList(chats = chats)
}

class HomeActivity : ComponentActivity() {
    private val tag = "HomeActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "HomeActivity created")
        setContent {
            MobileApplicationTheme {
                HomeScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    MobileApplicationTheme {
        HomeScreen()
    }
}
