package com.doubletech.composeworkplace

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Text

@Composable
fun GoogleSignUpButton(
    modifier: Modifier = Modifier
) {
    // Butona basılınca yüklenme göstermek için bir state tutuyoruz.
    var clicked by remember { mutableStateOf(false) }

    // Surface, Material 3'te etkileşimli kart/buton benzeri alanlar yaratmak için kullanılıyor.
    Surface(
        // Butona tıklanınca 'clicked' değerini tersine çeviriyoruz (true/false).
        onClick = { clicked = !clicked },
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 4.dp,
        modifier = modifier
    ) {
        // İçerik alanında animasyon desteği sağlamak için animateContentSize ekliyoruz.
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            // Dikeyde ve yatayda merkezliyoruz.
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // R.drawable.ic_google_logo, proje içindeki logo dosyası olmalı.
            Icon(
                painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = "Google Button",
                // Google'ın kurumsal renklerini olduğu gibi göstermek için 'Unspecified' kullanıyoruz.
                tint = Color.Unspecified,
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Butonun üzerinde gösterilecek metin.
            Text(
                text = "Sign Up with Google",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            // Kullanıcı butona basarsa, yanında CircularProgressIndicator gösteriyoruz.
            if (clicked) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    // Küçük bir yüklenme çubuğu görünümü için boyutu azaltıyoruz.
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GoogleButtonPreview() {
    GoogleSignUpButton()
}
