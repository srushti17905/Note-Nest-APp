package com.example.note_app

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.note_app.ui.theme.Note_AppTheme

class Edit_Page : ComponentActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.getSerializableExtra("NoteDetail", DetailNote::class.java)

        val DeleteNote = mutableStateOf(false)

        val EditTitle = mutableStateOf(data!!.title)
        val EditDetail = mutableStateOf(data.detail)

        enableEdgeToEdge()
        setContent {
            Note_AppTheme {

                if (DeleteNote.value) {

                    Dialog(onDismissRequest = { DeleteNote.value = false }) {

                        Surface(
                            modifier = Modifier
                                .height(180.dp)
                                .width(250.dp),
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        ) {

                            Surface(
                                modifier = Modifier
                                    .height(160.dp)
                                    .padding(8.dp)
                                    .width(130.dp),
                                color = star.yellow, shape = RoundedCornerShape(10.dp)
                            ) {

                                Column(modifier = Modifier.fillMaxSize()) {

                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .height(60.dp)
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "Delete Note ?",
                                            fontSize = 24.sp,
                                            fontFamily = FontFamily(Font(R.font.sun))
                                        )
                                    }

                                    Row(
                                        modifier = Modifier
                                            .height(20.dp)
                                            .fillMaxWidth()
                                    ) {

                                    }

                                    Row(
                                        modifier = Modifier
                                            .height(80.dp)
                                            .fillMaxWidth()
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier
                                                .height(100.dp)
                                                .width(115.dp)
                                        ) {
                                            Button(
                                                onClick = {
                                                    DeleteNote.value = false
                                                },
                                                modifier = Modifier
                                                    .height(50.dp)
                                                    .width(80.dp),
                                                colors = ButtonDefaults.buttonColors(containerColor = Color.White)

                                            ) {
                                                Text(
                                                    text = "No",
                                                    fontSize = 18.sp,
                                                    color = Color.Black,
                                                    fontFamily = FontFamily(Font(R.font.moon))
                                                )
                                            }
                                        }

                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier
                                                .height(100.dp)
                                                .width(115.dp)
                                        ) {
                                            Button(
                                                onClick = {

                                                    val deleteData = Database(applicationContext)
                                                    deleteData.delete(id = data.id)

                                                    Toast.makeText(
                                                        this@Edit_Page,
                                                        "Delete Successfully",
                                                        Toast.LENGTH_SHORT
                                                    ).show()

                                                    val intent = Intent(
                                                        this@Edit_Page,
                                                        MainActivity::class.java
                                                    )
                                                    startActivity(intent)
                                                    finish()

                                                },
                                                modifier = Modifier
                                                    .height(50.dp)
                                                    .width(80.dp),
                                                colors = ButtonDefaults.buttonColors(containerColor = Color.White)

                                            ) {
                                                Text(
                                                    text = "Yes",
                                                    fontSize = 18.sp,
                                                    color = Color.Black,
                                                    fontFamily = FontFamily(Font(R.font.moon))
                                                )
                                            }
                                        }
                                    }

                                }

                            }

                        }

                    }

                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black)
                ) {

                    Row(
                        modifier = Modifier
                            .height(90.dp)
                            .fillMaxWidth()
                    ) {

                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.Bottom,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(85.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.arrow),
                                contentDescription = null,
                                tint = star.yellow,
                                modifier = Modifier
                                    .height(25.dp)
                                    .width(50.dp)
                                    .clickable {

                                        val intent =
                                            Intent(this@Edit_Page, MainActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.Bottom,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(190.dp)
                        ) {

                            Text(
                                text = "Edit / Delete",
                                fontSize = 25.sp,
                                color = Color.White,
                                fontFamily = FontFamily(
                                    Font((R.font.sun))
                                )
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.Bottom,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(60.dp)
                        ) {

                            Image(
                                painter = painterResource(R.drawable.note),
                                contentDescription = null, modifier = Modifier.height(30.dp)
                            )

                        }

                    }

                    Row(
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()
                    ) {

                    }

                    Row(
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier
                            .height(25.dp)
                            .fillMaxWidth()
                    ) {

                        Text(
                            text = "     Edit Title",
                            fontSize = 13.sp,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.sun))
                        )

                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth()
                    ) {

                        Surface(
                            modifier = Modifier
                                .height(130.dp)
                                .width(400.dp)
                                .padding(15.dp),
                            color = star.yellow,
                            shape = RoundedCornerShape(10.dp)
                        ) {

                            Surface(
                                modifier = Modifier
                                    .height(80.dp)
                                    .width(150.dp)
                                    .padding(5.dp),
                                color = Color.Black,
                                shape = RoundedCornerShape(10.dp)
                            ) {

                                // edit title

                                TextField(
                                    value = EditTitle.value,
                                    onValueChange = { EditTitle.value = it },
                                    textStyle = TextStyle(
                                        fontSize = 20.sp,
                                        color = Color.White,
                                        fontFamily = FontFamily(Font(R.font.moon))
                                    ),
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color.Black,
                                        unfocusedContainerColor = Color.Black,
                                        cursorColor = star.yellow,
                                        focusedIndicatorColor = star.yellow,
                                        unfocusedIndicatorColor = Color.Black
                                    ),
                                    minLines = 1,
                                    maxLines = 1
                                )

                            }

                        }

                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                    ) {

                    }

                    Row(
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier
                            .height(25.dp)
                            .fillMaxWidth()
                    ) {

                        Text(
                            text = "     Edit Description",
                            fontSize = 13.sp,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.sun))
                        )

                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                    ) {

                        Surface(
                            modifier = Modifier
                                .height(200.dp)
                                .padding(15.dp)
                                .width(400.dp),
                            shape = RoundedCornerShape(10.dp),
                            color = star.yellow
                        ) {

                            Surface(
                                modifier = Modifier
                                    .height(150.dp)
                                    .width(150.dp)
                                    .padding(5.dp),
                                shape = RoundedCornerShape(10.dp),
                                color = Color.Black
                            ) {
                                // edit detail

                                TextField(
                                    value = EditDetail.value,
                                    onValueChange = { EditDetail.value = it },
                                    textStyle = TextStyle(fontSize = 20.sp , fontFamily = FontFamily(Font(R.font.moon))),
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color.Black,
                                        unfocusedContainerColor = Color.Black,
                                        unfocusedIndicatorColor = Color.Black,
                                        focusedIndicatorColor = star.yellow,
                                        cursorColor = star.yellow
                                    ),
                                    minLines = 1,
                                    maxLines = 10
                                )

                            }

                        }


                    }

                    Row(
                        modifier = Modifier
                            .height(30.dp)
                            .fillMaxWidth()
                    ) {

                    }

                    Row(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                    ) {

                        Surface(
                            modifier = Modifier
                                .height(130.dp)
                                .width(180.dp), color = Color.Black
                        ) {
                            Button(
                                onClick = {

                                    val updateNote = Database(applicationContext)
                                    updateNote.updateData(
                                        id = data.id,
                                        newTitle = EditTitle.value,
                                        newDetail = EditDetail.value
                                    )

                                    if (EditTitle.value != "" && EditDetail.value != "") {

                                        val intent =
                                            Intent(this@Edit_Page, MainActivity::class.java)
                                        startActivity(intent)
                                        finish()

                                    } else {

                                        Toast.makeText(
                                            this@Edit_Page,
                                            "complete your note for save",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                    }

                                }, colors = ButtonDefaults.buttonColors(
                                    containerColor = star.yellow
                                ),
                                modifier = Modifier
                                    .height(50.dp)
                                    .padding(37.dp)
                                    .width(50.dp), border = BorderStroke(2.dp, color = Color.White)
                            ) {
                                Text(
                                    text = "Save",
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                    fontFamily = FontFamily(
                                        Font(R.font.sun)
                                    )
                                )
                            }
                        }

                        Surface(
                            modifier = Modifier
                                .height(130.dp)
                                .width(180.dp), color = Color.Black
                        ) {
                            Button(
                                onClick = {

                                    DeleteNote.value = true

                                }, colors = ButtonDefaults.buttonColors(
                                    containerColor = star.yellow
                                ),
                                modifier = Modifier
                                    .height(50.dp)
                                    .padding(37.dp)
                                    .width(50.dp), border = BorderStroke(2.dp, color = Color.White)
                            ) {
                                Text(
                                    text = "Delete",
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                    fontFamily = FontFamily(
                                        Font(R.font.sun)
                                    )
                                )
                            }


                        }


                    }
                }

            }
        }
    }
}
