package com.doubletech.composeworkplace

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.doubletech.composeworkplace.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown

@ExperimentalMaterial3Api
@Composable
fun ExpandableCard(
    title: String,
    titleFontSize: TextUnit = MaterialTheme.typography.titleMedium.fontSize,
    titleFontWeight: FontWeight = FontWeight.Bold,
    description: String,
    descriptionFontSize: TextUnit = MaterialTheme.typography.labelMedium.fontSize,
    descriptionFontWeight: FontWeight = FontWeight.Normal,
    descriptionMaxLines: Int = 3,
    shapes: CornerBasedShape = MaterialTheme.shapes.medium,
    padding: Dp = 12.dp
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            // Yatayda ve dikeyde kartlar arası boşluk
            .padding(horizontal = 16.dp, vertical = 8.dp)
            // İçeriğin animasyonlu olarak genişlemesi/daralması
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .background(Color.White), // Kartın arka plan rengi
        // Card'ın container rengi, default LightGray
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        shape = shapes,
        // Kartın tıklandığında açılıp kapanabilmesi
        onClick = { expandedState = !expandedState }
    ) {
        // Kart içerikleri, üst üste gelecek şekilde Column ile yerleştiriliyor
        Column(Modifier.padding(padding)) {
            // Üst kısımda bir satır: Başlık ve sağda dönme animasyonlu ok
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    fontSize = titleFontSize,
                    fontWeight = titleFontWeight,
                    maxLines = 1, // Başlık tek satırı geçmesin
                    overflow = TextOverflow.Ellipsis,
                    // Text alanı boşluğu doldurduktan sonra IconButton sağda kalsın
                    modifier = Modifier.weight(1f)
                )
                // Ok ikonuna tıklandığında da expandedState değiştirilir
                IconButton(
                    onClick = { expandedState = !expandedState },
                    modifier = Modifier.rotate(rotationState) // Ok ikonunu döndürüyoruz
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            }
            // expandedState true ise açıklama metnini göster
            if (expandedState) {
                Text(
                    text = description,
                    fontSize = descriptionFontSize,
                    fontWeight = descriptionFontWeight,
                    maxLines = descriptionMaxLines,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun ExpandableCardPreview() {
    ExpandableCard(
        title = "Sample Title",
        description = "First off, Art isn’t your average clown—he’s a brutal, " +
                "silent killer with a sadistic streak that makes Pennywise look " +
                "like a children’s entertainer."
    )
}
