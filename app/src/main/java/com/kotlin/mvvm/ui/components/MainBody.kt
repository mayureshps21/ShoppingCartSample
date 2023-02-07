package com.kotlin.mvvm.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kotlin.mvvm.R

@Composable
fun MainBody(
    paddingValues: PaddingValues,
    clickAddToCartButton: () -> Unit
) {

    Column {
        Image(painter = painterResource(R.drawable.item), contentDescription = "")
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            modifier = Modifier.fillMaxWidth(), text = stringResource(R.string.item_description),
            style = TextStyle(color = Color.Black), fontSize = 25.sp, textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.item_amount),
            style = TextStyle(color = Color(0xFFFFC039)),
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(), text = stringResource(R.string.extra_details),
            style = TextStyle(color = Color.Black), fontSize = 15.sp, textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(modifier = Modifier.background(shape = RectangleShape, color = Color(0xFF0F0F4A))) {
            Button(
                onClick = {
                    clickAddToCartButton()
                }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0F0F4A)),
                modifier = Modifier.fillMaxWidth().height(60.dp)
            ) {
                Text(
                    text = stringResource(R.string.add_to_cart),
                    style = TextStyle(color = Color.White),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}


