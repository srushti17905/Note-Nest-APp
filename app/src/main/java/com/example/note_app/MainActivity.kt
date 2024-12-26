    package com.example.note_app

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.formatWithSkeleton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.note_app.ui.theme.Note_AppTheme

class MainActivity : ComponentActivity() {

    companion object {

        lateinit var NoteDatabase: Database

    }

    // todo( share text to whatsapp )

    object Share {

        fun shareTextToWhatsApp(context: Context, text: String) {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
                `package` = "com.whatsapp" // Specify WhatsApp package
            }

            try {
                context.startActivity(sendIntent)
            } catch (e: ActivityNotFoundException) {
                // Handle the case where WhatsApp is not installed
                Toast.makeText(context, "WhatsApp is not installed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("NewApi", "UnrememberedMutableState")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val data = ArrayList<showNote>()

        enableEdgeToEdge()

        NoteDatabase = Database(applicationContext)

        setContent {

            // latest data show on tht top


            val alldata = viewNote()
            val arrayList = ArrayList<Color>()
            val search = mutableStateOf("")
            val activeStatus = mutableStateOf(false)
            val showData = mutableStateOf("")


            arrayList.add(Color(0xFF9FE7E7))
            arrayList.add(Color(0xFFE7ADAD))
            arrayList.add(Color(0xFF9393E0))
            arrayList.add(Color(0xFFECEC9F))
            arrayList.add(Color(0xFFA8765C))
            arrayList.add(Color(0xFFA6DEA6))
            arrayList.add(Color(0xFF76A9DC))



            Note_AppTheme {

                Scaffold(topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = "All Notes",
                                fontSize = 30.sp,
                                color = Color.White,
                                fontFamily = FontFamily(Font(R.font.sun))
                            )
                        },
                        navigationIcon = {

                            Image(
                                painter = painterResource(R.drawable.note1),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(80.dp)
                                    .padding(25.dp)
                            )

                        },
                        actions = {
                            Image(
                                painter = painterResource(R.drawable.note1),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(80.dp)
                                    .padding(25.dp)
                            )
                        }
                    )
                }) { paddingValues ->

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Black)
                            .padding(paddingValues)
                    ) {

                        SearchBar(
                            query = search.value,
                            onQueryChange = {
                                Log.d(
                                    "===query<===",
                                    "onQueryChange: $it"
                                )
                                search.value = it
                            },
                            onSearch = {
                                Log.d(
                                    "===>search<===",
                                    "onCreate: $it"
                                )

                            },
                            placeholder = { Text("search your note...") },
                            active = activeStatus.value,
                            onActiveChange = {
                                activeStatus.value = it
                                Log.d("active==status", "onCreate: $it")
                            },
                            colors = SearchBarDefaults.colors(
                                dividerColor = Color.Black,
                                inputFieldColors = TextFieldDefaults.colors(
                                    focusedTextColor = Color.White,
                                    cursorColor = star.yellow,
                                    focusedContainerColor = Color.Cyan,
                                    unfocusedLabelColor = Color.Black,
                                    focusedSuffixColor = Color.Cyan,
                                    unfocusedContainerColor = Color.White,
                                )
                            ),
                            shape = SearchBarDefaults.dockedShape,
                            modifier = Modifier
                                .padding(start = 20.dp, end = 20.dp)
                                .fillMaxWidth()
                                .clickable {
                                    val intent = Intent(this@MainActivity, MainActivity::class.java)

                                    intent.putExtra("title", alldata)

                                    startActivity(intent)
                                }

                        ) {


                            if (search.value == data.toString()) {

                                showData.value = "cooking"
                            } else {
                                showData.value = ""
                            }

                            Text(text = showData.value, fontSize = 30.sp, color = Color.White)

                        }

                        Row(
                            modifier = Modifier
                                .height(530.dp)
                                .fillMaxWidth()
                                .background(Color.Black)
                        ) {

                            LazyVerticalGrid(
                                modifier = Modifier.padding(15.dp),
                                columns = GridCells.Fixed(1)
                            ) {
                                items(alldata.size) { index ->

                                    Surface(
                                        modifier = Modifier
                                            .height(130.dp)
                                            .padding(5.dp)
                                            .width(200.dp)
                                            .clickable {

                                            },
                                        color = Color.White,
                                        shape = RoundedCornerShape(15.dp)
                                    ) {


                                        Surface(
                                            modifier = Modifier
                                                .height(120.dp)
                                                .padding(7.dp)
                                                .clickable {

                                                    val intent = Intent(
                                                        this@MainActivity,
                                                        Edit_Page::class.java
                                                    )

                                                    // id

                                                    intent.putExtra("NoteDetail", alldata[index])
                                                    intent.putExtra("title", alldata[index].title)
                                                    intent.putExtra("detail", alldata[index].detail)

                                                    startActivity(intent)
                                                    finish()


                                                }
                                                .width(10.dp),

                                            color = arrayList[index % 7],
                                            shape = RoundedCornerShape(10.dp)
                                        ) {
                                            Column(modifier = Modifier.fillMaxSize()) {

                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .height(40.dp)
                                                ) {

                                                    Row(
                                                        horizontalArrangement = Arrangement.Center,
                                                        verticalAlignment = Alignment.CenterVertically,
                                                        modifier = Modifier
                                                            .height(40.dp)
                                                            .width(30.dp)
                                                    ) {

                                                        Image(
                                                            painter = painterResource(R.drawable.bullet),
                                                            contentDescription = null,
                                                            modifier = Modifier.height(12.dp)
                                                        )
                                                    }

                                                    Row(
                                                        horizontalArrangement = Arrangement.Start,
                                                        verticalAlignment = Alignment.CenterVertically,
                                                        modifier = Modifier
                                                            .width(150.dp)
                                                            .height(40.dp)
                                                    ) {
                                                        Text(
                                                            text = "  ${alldata[index].title}",
                                                            fontSize = 20.sp,
                                                            color = Color.Black,
                                                            fontFamily = FontFamily((Font(R.font.sun)))
                                                        )
                                                    }

                                                    Row(
                                                        horizontalArrangement = Arrangement.End,
                                                        verticalAlignment = Alignment.CenterVertically,
                                                        modifier = Modifier
                                                            .height(40.dp)
                                                            .width(105.dp)
                                                    ) {
                                                        Icon(
                                                            painter = painterResource(R.drawable.share),
                                                            contentDescription = null,
                                                            tint = Color.Black,
                                                            modifier = Modifier
                                                                .height(25.dp)
                                                                .clickable {

                                                                    // TODO( share to whatsapp)

                                                                    Share.shareTextToWhatsApp(
                                                                        context = this@MainActivity,

                                                                        """Title : ${alldata[index].title}
                                                                            Description : ${alldata[index].detail}""".trimMargin()
                                                                    )

                                                                }
                                                        )
                                                    }
                                                }

                                                Row(
                                                    modifier = Modifier
                                                        .height(100.dp)
                                                        .fillMaxWidth()
                                                ) {

                                                    Row(
                                                        modifier = Modifier
                                                            .fillMaxHeight()
                                                            .width(30.dp)
                                                    ) {

                                                    }

                                                    Row(
                                                        horizontalArrangement = Arrangement.Start,
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .height(100.dp)
                                                    ) {
                                                        Text(
                                                            text = "  ${alldata[index].detail}",
                                                            fontSize = 20.sp,
                                                            color = Color.Black,
                                                            fontFamily = FontFamily(Font(R.font.moon)),
                                                            modifier = Modifier.padding(3.dp)
                                                        )
                                                    }
                                                }

                                            }

                                        }

                                    }

                                }


                            }

                        }

                        Surface(
                            modifier = Modifier
                                .width(350.dp)
                                .height(100.dp), color = Color.Transparent
                        ) {

                            Row(
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.Top,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.img),
                                    tint = star.yellow,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(100.dp)
                                        .padding(end = 15.dp, bottom = 10.dp)
                                        .clickable {

                                            val intent =
                                                Intent(this@MainActivity, AddPage::class.java)
                                            startActivity(intent)
                                            finish()

                                        }
                                )
                            }
                        }

                    }

                }


            }
        }
    }
}

private fun viewNote(): ArrayList<DetailNote> {

    val NoteList = ArrayList<DetailNote>()

    val cursur: Cursor =
        MainActivity.NoteDatabase.readableDatabase.rawQuery("SELECT * FROM user", null)

    cursur.use {

        if (it.moveToFirst()) {

            do {

                val id = it.getInt(0)
                val title = it.getString(1)
                val detail = it.getString(2)

                val data = DetailNote(id = id, title = title, detail = detail)
                NoteList.add(data)

            } while (it.moveToNext())

        }

        return NoteList

    }

}