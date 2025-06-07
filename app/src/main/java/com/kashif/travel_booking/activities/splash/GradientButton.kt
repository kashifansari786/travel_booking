package com.kashif.travel_booking.activities.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kashif.travel_booking.R

/**
 * Created by Mohammad Kashif Ansari on 02,June,2025
 */

@Composable
@Preview
fun GradientButton(onCLick:()->Unit={},text:String="Get Started",padding:Int=0){
  Button(onClick = onCLick,
    modifier = Modifier
      .fillMaxWidth()
      .padding(padding.dp),
    shape = RoundedCornerShape(16.dp),
    colors = ButtonDefaults.buttonColors(
      containerColor = Color.Transparent,
      contentColor = Color.White
    ),
    elevation = ButtonDefaults.buttonElevation(
      defaultElevation = 0.dp,
      pressedElevation = 0.dp
    ), contentPadding = PaddingValues(0.dp)
    )
  {
    Box(modifier = Modifier.background(
      brush = Brush.linearGradient(
        colors = listOf(
          colorResource(R.color.purple),
          colorResource(R.color.pink)
        )
      ),
      shape = RoundedCornerShape(50.dp)
    ).fillMaxWidth().padding(vertical = 12.dp), contentAlignment = Alignment.Center){
      Text(text=text, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
  }
}