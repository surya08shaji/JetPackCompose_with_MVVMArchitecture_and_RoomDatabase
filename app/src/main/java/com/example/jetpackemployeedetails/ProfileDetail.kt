package com.example.jetpackemployeedetails

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.jetpackemployeedetails.ui.theme.JetPackEmployeeDetailsTheme

class ProfileDetail : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackEmployeeDetailsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ShowData()
                }
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun ShowData() {
    val context = LocalContext.current
    val intent = (context as ProfileDetail).intent

    val image = intent.getStringExtra("image")
    val name = intent.getStringExtra("name")
    val username = intent.getStringExtra("username")
    val email = intent.getStringExtra("email")
    val street = intent.getStringExtra("street")
    val suite = intent.getStringExtra("suite")
    val city = intent.getStringExtra("city")
    val zipcode = intent.getStringExtra("zipcode")
    val phone = intent.getStringExtra("phone")
    val website = intent.getStringExtra("website")
    val companyname = intent.getStringExtra("companyname")
    val catchPhrase = intent.getStringExtra("catchPhrase")
    val bs = intent.getStringExtra("bs")

    androidx.constraintlayout.compose.ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (profile) = createRefs()
        val image1 = rememberImagePainter(image)

        Image(painter = image1,
            contentDescription = "Image Url",
            modifier = Modifier
                .size(100.dp)
                .clip(shape = CircleShape)
                .constrainAs(profile) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
//                    end.linkTo(parent.end, margin = 16.dp)
                }

//                .padding(16.dp)
        )

        val (back) = createRefs()
        Text(text = "Back",
            color = Color.Blue,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .constrainAs
                    (back) {
                    top.linkTo(parent.top, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
                .clickable(onClick = {
                    val i = Intent(context, MainActivity::class.java)
                    context.startActivity(i)
                })
        )


        val(column1) = createRefs()
            Column(
                modifier = Modifier
                    .constrainAs(column1){
                        top.linkTo(profile.bottom, margin = 16.dp)
                        start.linkTo(parent.start, margin = 8.dp)
                    }
            )
            {
                Text(
                    text = "Name",
                    color = Color.Gray,
                    fontSize = 14.sp, textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "User Name ", color = Color.Gray, fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "E-mail ID ", color = Color.Gray, fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "Street ", color = Color.Gray, fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "Suite ", color = Color.Gray, fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "City ", color = Color.Gray, fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "ZipCode ", color = Color.Gray, fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "Phone ", color = Color.Gray, fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "Website ", color = Color.Gray, fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "Company Name ", color = Color.Gray, fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "Catch Phrase  ", color = Color.Gray, fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "BS  ", color = Color.Gray, fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
            }


        val(column2) = createRefs()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(column2){
                        top.linkTo(profile.bottom, margin = 16.dp)
                        start.linkTo(column1.end, margin = 10.dp)
                    }
            ) {

                Text(
                    text = name ?: "Null",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
//                textAlign = TextAlign.Center,
                    maxLines = 1,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = username ?: "Null",
//                textAlign = TextAlign.Center,
                    maxLines = 1,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = email ?: "Null",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = street ?: "Null",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(6.dp)
                )

                Text(
                    text = suite ?: "Null",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(6.dp)
                )

                Text(
                    text = city ?: "Null",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(7.dp)
                )

                Text(
                    text = zipcode ?: "Null",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(7.dp)
                )

                Text(
                    text = phone ?: "Null",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(6.dp)
                )

                Text(
                    text = website ?: "Null",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = companyname ?: "Null",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(10.dp)
                )

                Text(
                    text = catchPhrase ?: "Null",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(6.dp)
                )

                Text(
                    text = bs ?: "Null",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(6.dp)
                )
            }
//        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    JetPackEmployeeDetailsTheme {
        ShowData()
    }
}