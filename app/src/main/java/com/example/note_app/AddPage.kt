package com.example.note_app

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.note_app.ui.theme.Note_AppTheme


class AddPage : ComponentActivity() {


    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val title = mutableStateOf("")
        val detail = mutableStateOf("")

        setContent {

            Note_AppTheme {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                            .background(Color.Black)
                    ) {

                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .height(120.dp)
                                .width(50.dp)
                                .background(Color.Black)
                        ) {

                            Icon(
                                painter = painterResource(R.drawable.arrow),
                                contentDescription = null,
                                tint = Color.White, modifier = Modifier
                                    .height(30.dp)
                                    .padding(5.dp)
                                    .clickable {

                                        val intent = Intent(this@AddPage, MainActivity::class.java)
                                        startActivity(intent)
                                        finish()

                                    }
                            )

                        }
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .height(120.dp)
                                .width(250.dp)
                                .background(Color.Black)
                        ) {
                            Text(
                                text = "Add Note",
                                fontSize = 28.sp,
                                color = Color.White,
                                fontFamily = FontFamily(
                                    Font(R.font.sun)
                                )
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .height(120.dp)
                                .width(40.dp)
                                .background(Color.Black)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.tick),
                                contentDescription = null, tint = star.pink,
                                modifier = Modifier
                                    .height(20.dp)
                                    .width(30.dp)
                                    .clickable {

                                        if (title.value != "" && detail.value != "") {

                                            val intent =
                                                Intent(this@AddPage, MainActivity::class.java)

                                            // insert

                                            val Noteadd = Database(applicationContext)
                                            Noteadd.insertData(
                                                title = title.value,
                                                detail = detail.value
                                            )
                                            startActivity(intent)
                                            finish()
                                        } else {
                                            Toast
                                                .makeText(
                                                    this@AddPage,
                                                    "Write Something for Save",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        }

                                    }
                            )
                        }

                    }


                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(50.dp)
                            .width(100.dp)
                    ) {

                        Text(
                            text = "Title",
                            fontSize = 13.sp,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.sun))
                        )

                    }


                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier
                            .height(90.dp)
                            .width(400.dp)
                    ) {

                        TextField(
                            value = title.value,
                            onValueChange = { title.value = it },

                            textStyle = TextStyle(
                                fontSize = 18.sp,
                                color = Color.Black,
                                fontFamily = FontFamily(Font(R.font.moon))
                            ),
                            modifier = Modifier
                                .height(70.dp)
                                .width(300.dp),
                            shape = RoundedCornerShape(10.dp),
                            leadingIcon = {
                                Image(
                                    painter = painterResource(R.drawable.title2),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(50.dp)
                                        .padding(10.dp)
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = star.yellow,
                                focusedContainerColor = star.yellow,
                                unfocusedIndicatorColor = Color.Black,
                                focusedIndicatorColor = Color.Black,
                                cursorColor = Color.White
                            ), minLines = 1, maxLines = 1
                        )

                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(50.dp)
                            .width(130.dp)
                    ) {

                        Text(
                            text = "Description",
                            fontSize = 13.sp, fontFamily = FontFamily(Font(R.font.sun)),
                            color = Color.White,
                            modifier = Modifier.padding(start = 8.dp)
                        )

                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier
                            .height(200.dp)
                            .width(400.dp)
                    ) {

                        TextField(
                            value = detail.value,
                            onValueChange = { detail.value = it },
                            textStyle = TextStyle(
                                fontSize = 18.sp,
                                color = Color.Black,
                                fontFamily = FontFamily(Font(R.font.moon))
                            ),

                            modifier = Modifier
                                .height(150.dp)
                                .width(300.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = star.blur,
                                unfocusedContainerColor = star.blur,
                                unfocusedIndicatorColor = Color.Black,
                                focusedIndicatorColor = Color.Black, cursorColor = Color.White
                            ), minLines = 1, maxLines = 2
                        )

                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .background(Color.Black)
                    ) {

                        Image(
                            painter = painterResource(R.drawable.multicolor),
                            contentDescription = null, modifier = Modifier.height(150.dp)
                        )

                    }
                }

            }
        }
    }
}

