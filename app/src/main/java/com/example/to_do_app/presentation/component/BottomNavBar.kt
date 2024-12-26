package com.example.to_do_app.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.to_do_app.R

@Composable
fun BottomNavBar(
    navController: NavHostController,
    currentRoute: String,
    onFabClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        NavigationBar(
            containerColor = Color(0xFFF0E8FF),
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.ic_home),
                        contentDescription = "Home",
                        tint = if (currentRoute == "home") Color(0xFF6A40F3) else Color(0xFFB69DF8)
                    )
                },
                selected = currentRoute == "home",
                onClick = { navController.navigate("home") }
            )

            Spacer(modifier = Modifier.width(56.dp))

            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Profile",
                        tint = if (currentRoute == "profile") Color(0xFF6A40F3) else Color(0xFFB69DF8)
                    )
                },
                selected = currentRoute == "profile",
                onClick = { navController.navigate("profile") }
            )
        }

        FloatingActionButton(
            onClick = onFabClick,
            containerColor = Color(0xFF6A40F3),
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-24).dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "Add",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun BottomNavBarPreview() {
    val navController = rememberNavController()
    BottomNavBar(
        navController = navController,
        currentRoute = "home",
        onFabClick = { }
    )
}
