package com.testapp.mckinleytest.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testapp.mckinleytest.R
import com.testapp.mckinleytest.destinations.LoginScreenDestination
import com.testapp.mckinleytest.destinations.RegisterScreenDestination
import com.testapp.mckinleytest.ui.theme.ColorBg
import com.testapp.mckinleytest.ui.theme.ColorButton
import com.testapp.mckinleytest.ui.theme.PurpleBg
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.testapp.mckinleytest.ui.theme.Teal200

@RootNavGraph(start = true)
@Destination
@Composable
fun WelcomeScreen(
    navigator: DestinationsNavigator
) {

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(40.dp))
                .height(378.dp)
                .fillMaxWidth(),
            backgroundColor = Teal200
        ) {
            Image(
                painter = painterResource(R.drawable.lock),
                contentDescription = "Welcome Screen",
                modifier = Modifier
                    .fillMaxSize()
            )

        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Welcome to Mckinley Rice ",
            fontSize = 26.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "\n" + " Login/Register",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = Modifier.height(64.dp))
        Row(
           modifier = Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.Center
        ) {

            Button(
                onClick = {
                          navigator.navigate(RegisterScreenDestination)
                },
                // Uses ButtonDefaults.ContentPadding by default
                contentPadding = PaddingValues(
                    start = 40.dp,
                    top = 12.dp,
                    end = 40.dp,
                    bottom = 12.dp
                ),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.DarkGray)
            ) {

                Text(
                    "Register",
                    fontSize = 20.sp
                )
            }
            Button(
                onClick = {
                    navigator.navigate(LoginScreenDestination)
                },
                // Uses ButtonDefaults.ContentPadding by default
                contentPadding = PaddingValues(
                    start = 40.dp,
                    top = 12.dp,
                    end = 40.dp,
                    bottom = 12.dp
                ),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Teal200,
                    contentColor = Color.White)
            ) {
                Text(
                    "Login",
                    fontSize = 20.sp
                )
            }
        }
    }

}