package ui.weight

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TooltipButton(text: String, tooltip: String, type: Int = -1, listener: (() -> Unit)?) {
    TooltipArea(
        modifier = Modifier.padding(start = 12.dp),
        tooltip = {
            Surface(
                modifier = Modifier.shadow(4.dp),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = tooltip,
                    modifier = Modifier.padding(10.dp)
                )
            }

        },
    ) {
        if (type == 1){
            Button(onClick = { listener?.invoke() }) { Text(text) }
        }else if (type == 2){
            Button(onClick = {}, modifier = Modifier.clickable(enabled = false){},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                ) { Text(text) }
        }else {
            Button(onClick = { listener?.invoke() }) { Text(text) }
        }
    }
}