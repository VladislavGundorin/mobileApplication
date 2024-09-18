package com.example.mobileapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.mobileapplication.model.RMCharacter
import com.example.mobileapplication.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await

class CharacterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var character by remember { mutableStateOf<RMCharacter?>(null) }

            LaunchedEffect(Unit) {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val response = RetrofitInstance.api.getCharacter(393).await()
                        character = response
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            character?.let { char ->
                CharacterScreen(char)
            } ?: Text("Загрузка...")
        }
    }
}

@Composable
fun CharacterScreen(character: RMCharacter) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = rememberImagePainter(character.image),
            contentDescription = character.name,
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Имя: ${character.name}")
        Text(text = "Статус: ${character.status}")
        Text(text = "Вид: ${character.species}")
        Text(text = "Тип: ${character.type ?: "Неизвестен"}")
        Text(text = "Пол: ${character.gender}")
        Text(text = "Происхождение: ${character.origin.name}")
        Text(text = "Местоположение: ${character.location.name}")
    }
}
