package com.kashif.travel_booking.activities.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kashif.travel_booking.MainActivity
import com.kashif.travel_booking.R
import com.kashif.travel_booking.activities.dashboard.DashboardActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashScreen(onGetStartedClick = {
                startActivity(Intent(this,DashboardActivity::class.java))
            })
        }
    }
}

@Composable
@Preview
fun SplashScreen(onGetStartedClick:()->Unit={}){
    StatusTopBarColor()
    Column(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout() {
            val(backgroundImg,title,subtitle,startbyn)=createRefs()
            Image(painter = painterResource(R.drawable.splash_bg),
                contentDescription = null,
                modifier = Modifier.constrainAs(backgroundImg){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }.fillMaxSize())
            val styleText= buildAnnotatedString {
                append("Discover your\nDream ")
                withStyle(style = SpanStyle(color = colorResource(R.color.orange))){
                    append("Flight")
                }
                append("\nEasily")
            }
            Text(text = styleText,
                fontSize = 53.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .padding(horizontal = 16.dp)
                    .constrainAs(title){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )
            Text(text = stringResource(R.string.subtitle_splash),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.orange),
                modifier = Modifier
                    .padding(top = 32.dp, start = 16.dp)
                    .constrainAs(subtitle){
                        top.linkTo(title.bottom)
                        start.linkTo(title.start)

            })
            Box(modifier = Modifier.constrainAs(startbyn){
                bottom.linkTo(parent.bottom)
            }) {
                GradientButton(onCLick = onGetStartedClick, text = "Get Started",32)
            }
        }
    }
}

@Composable
fun StatusTopBarColor(){
    val systemUIController= rememberSystemUiController()

    SideEffect {
        systemUIController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }
}