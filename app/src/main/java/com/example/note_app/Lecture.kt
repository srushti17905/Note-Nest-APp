package com.example.note_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.note_app.ui.theme.Note_AppTheme

class Lecture : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Note_AppTheme {
                Scaffold(floatingActionButton = {
//                    FloatingActionButton(onClick = {
//
//                    }) { }

//                    SmallFloatingActionButton(onClick = {
//
//
//                    }) { }

//                    LargeFloatingActionButton(onClick = {
//
//                    }) { }

//                    ExtendedFloatingActionButton(text = {
//                        Text(text = "Add")
//
//                    }, icon = {
//                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
//
//                    }, onClick = {
//
//                    })

                }, topBar = {

//                    MediumTopAppBar(title = {
//                        Text(text = "Lecture")
//                    }, actions = {
//                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
//                    }, navigationIcon = {
//                        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
//
//                    }, colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.DarkGray))

                    LargeTopAppBar(
                        title = {
                            Text(text = "Lecture")
                        },
                        actions = {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                        },
                        navigationIcon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = null
                            )

                        },
                        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.DarkGray)
                    )

                }, modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(Color.White)
                    ) {


                        Button(onClick = {

                        }) { }
                        FilledTonalButton(onClick = {

                        }) {

                        }
                        OutlinedButton(onClick = {

                        }) { }
                        ElevatedButton(onClick = {

                        }) { }

                        TextButton(onClick = {

                        }) {
                            Text(text = "Text")
                        }
                    }
                }
            }
        }
    }
}
