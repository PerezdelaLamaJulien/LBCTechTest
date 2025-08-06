package com.jperez.lbc.feature.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.CircleAlert
import com.composables.icons.lucide.Lucide
import com.jperez.lbc.feature.theme.LbcTheme

@Composable
fun ErrorView(
onRetry: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Lucide.CircleAlert, contentDescription = "Error Icon", tint = Color.Red)
        Text("Something went wrong, please try again !", color = Color.Red)
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors().copy(
                contentColor = Color.Red,
                containerColor = MaterialTheme.colorScheme.surface ,
            ),
            border = BorderStroke(width = 1.dp, color = Color.Red)
        ) {
            Text("Retry")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorViewPreview() {
    LbcTheme {
        ErrorView(
            onRetry = {}
        )
    }
}