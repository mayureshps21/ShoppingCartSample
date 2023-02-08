package com.kotlin.mvvm.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.mvvm.R

@Composable
@Preview
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(modifier = Modifier.padding(15.dp), elevation = 5.dp) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.str_login),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 25.sp
                    ),

                    )
                Spacer(modifier = Modifier.height(40.dp))
                OutlinedTextField(
                    value = username,
                    label = { Text(stringResource(R.string.str_username)) },
                    onValueChange = { newText -> username = newText },
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = password,
                    label = { Text(stringResource(R.string.str_password)) },
                    onValueChange = { newText -> password = newText },
                )
                Spacer(modifier = Modifier.height(20.dp))

                Button(modifier = Modifier.fillMaxWidth(.78f).height(40.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0F0F4A)),
                    content = {
                        Text(text = stringResource(R.string.str_login_screen), color = Color.White)
                    })
                Spacer(modifier = Modifier.height(20.dp))

            }
        }
    }
}