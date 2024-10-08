package com.example.mobileapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mobileapplication.ui.theme.MobileApplicationTheme

class SignInActivity : ComponentActivity() {
    private val tag = "SignInActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "SignInActivity created")
        setContent {
            MobileApplicationTheme {
                SignInScreen()
            }
        }
    }
}

@Composable
fun SignInScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it
                Log.d("SignInScreen", "Email entered: $email")},

            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it
                Log.d("SignInScreen", "Password entered: $password")
                            },
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                Log.d("SignInScreen", "Sign In button clicked")
                if (email == "test" && password == "test") {
                    Toast.makeText(context, "Вход успешен", Toast.LENGTH_SHORT).show()

                    val intent = Intent(context, HomeActivity::class.java)
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "Неверный email или пароль", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Войти")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Зарегистрироваться",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                Log.d("SignInScreen", "Register button clicked")
                val intent = Intent(context, RegisterActivity::class.java)
                context.startActivity(intent)
            }
        )
    }
}