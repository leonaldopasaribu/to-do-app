package com.example.to_do_app.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.to_do_app.R
import com.example.to_do_app.core.entity.TaskEntity

@Composable
fun TaskCard(todo: TaskEntity, navController: NavController) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .clickable{
                navController.navigate("detailTask/${todo.id}")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = todo.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.width(320.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clock),
                        contentDescription = "Time",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "09:00 PM",
                        fontSize = 12.sp,
                        color = Color(0xFF6A40F3)
                    )
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_task),
                    contentDescription = "Task",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified,
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    color = statusColor(todo.isCompleted),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    text = statusText(todo.isCompleted)
                )
            }
        }
    }
}

fun statusColor(status: Boolean): Color {
    return when (status) {
        true -> Color(0xFFB59EFF)
        false-> Color(0xFF8DD1FF)
    }
}

fun statusText(status: Boolean): String {
    return when (status){
        true -> "Done"
        false -> "To-Do"
    }
}

@Preview
@Composable
fun TaskCardPreview() {
    val navController = rememberNavController()
    val todo = TaskEntity(
        id = 1,
        title = "Sample Task",
        isCompleted = true,
        userId = 1
    )
    TaskCard(todo = todo, navController)
}

