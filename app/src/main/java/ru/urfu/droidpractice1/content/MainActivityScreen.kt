@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import ru.urfu.droidpractice1.SecondActivity
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.widthIn
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.material3.LocalTextStyle
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import kotlinx.coroutines.launch
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Button
import android.content.Intent
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.activity.compose.rememberLauncherForActivityResult
import android.app.Activity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.mutableStateOf


val fontKristolit = FontFamily(
    Font(R.font.kristolit_trial_black_italic)
)
val fontCopyright = FontFamily(
    Font(R.font.copyright_bold_italic)
)
val fontDewi = FontFamily(
    Font(R.font.rf_dewi_extended_bold)
)

@Composable
fun MainActivityScreen() {

    var isRead by rememberSaveable { mutableStateOf(false) }

    var likes by rememberSaveable { mutableStateOf(35) }
    var dislikes by rememberSaveable { mutableStateOf(7) }

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->

        if (result.resultCode == Activity.RESULT_OK) {
            isRead = result.data?.getBooleanExtra("is_read", false) ?: false
        }
    }

    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title),
                            fontFamily = fontKristolit,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )
            }) { innerPadding ->
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(8.dp)
                    .verticalScroll(scrollState)
            ) {
                Introduction()
                HolySeePavilion()
                USAPavilion()

                val articleText = stringResource(R.string.text1)

                Button(
                    onClick = {
                        val articleText = articleText

                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, articleText)
                        }

                        context.startActivity(
                            Intent.createChooser(intent, "Поделиться статьёй")
                        )
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Поделиться")
                }

                Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Button(
                        onClick = {
                            likes++
                        }
                    ) {
                        Text("👍 $likes")
                    }

                    Button(
                        onClick = {
                            dislikes++
                        }
                    ) {
                        Text("👎 $dislikes")
                    }
                }


                Box(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            val intent = Intent(context, SecondActivity::class.java)
                            intent.putExtra("is_read", isRead)
                            launcher.launch(intent)
                        },
                        colors = ButtonDefaults.buttonColors(
                            if (isRead) {
                                Color.LightGray
                            } else {
                                Color.Black
                            }
                        ),

                        ) {
                        Text(
                            text = stringResource(R.string.next),
                            textAlign = TextAlign.Center,
                            fontFamily = fontDewi
                        )
                    }
                }


            }

        }
    }
}

@Composable
fun Introduction() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 43.dp,
                vertical = 12.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = stringResource(id = R.string.marker),
            fontFamily = fontDewi,
            fontSize = 16.sp
        )
        Text(
            text = stringResource(id = R.string.heading1),
            fontFamily = fontKristolit,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                horizontal = 50.dp
            )
        )
        Text(
            text = stringResource(id = R.string.heading2),
            fontFamily = fontCopyright,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }
    Text(
        text = stringResource(id = R.string.text1),
        fontFamily = fontCopyright
    )
    Column ( modifier = Modifier.padding(top = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.marker1),
            fontFamily = fontDewi,
            fontSize = 12.sp
        )
        Text(
            text = stringResource(id = R.string.authors1),
            fontFamily = fontKristolit
        )
    }
    var scrdolState = rememberScrollState()
    Row (
        modifier = Modifier
            .horizontalScroll(scrdolState)
            .padding(vertical = 50.dp)
    ) {
        Text(
            text = stringResource(id = R.string.pavilion1),
            fontFamily = fontDewi,
            modifier = Modifier.widthIn(max = 250.dp)
        )
        Text(
            text = stringResource(id = R.string.pavilion2),
            fontFamily = fontDewi,
            modifier = Modifier.widthIn(max = 250.dp)
        )
    }
}

@Composable
fun HolySeePavilion() {
    Column {
        Text(
            text = stringResource(id = R.string.heading3),
            textAlign = TextAlign.Center,
            fontFamily = fontCopyright,
            fontSize = 29.sp,

        )
        Text(
            text = stringResource(id = R.string.footnote1),
            fontFamily = fontDewi,
            modifier = Modifier.padding(vertical = 16.dp),
            style = LocalTextStyle.current.copy(
                textIndent = TextIndent(
                    firstLine = 70.sp
                )
            )
        )
        Text(
            text = stringResource(id = R.string.text2), //text2
            fontFamily = fontDewi,
            style = LocalTextStyle.current.copy(
                textIndent = TextIndent(
                    firstLine = 70.sp
                )
            )
        )
    }

    val articles = listOf(
        Pair(
            R.drawable.eye_of_the_soul1,
            stringResource(R.string.footnote2)
        ),
        Pair(
            R.drawable.eye_of_the_soul2,
            stringResource(R.string.footnote2)
        )
    )

    PhotoPager(articles)

    Text(
        text = stringResource(id = R.string.text3),
        fontFamily = fontDewi,
        style = LocalTextStyle.current.copy(
            textIndent = TextIndent(
                firstLine = 70.sp
            )
        ),
        modifier = Modifier.padding(vertical = 50.dp),
    )
}


@Composable
fun USAPavilion() {
    Column {
        Text(
            text = stringResource(id = R.string.heading4),
            textAlign = TextAlign.Center,
            fontFamily = fontCopyright,
            fontSize = 29.sp,

            )
        Text(
            text = stringResource(id = R.string.footnote3),
            fontFamily = fontDewi,
            modifier = Modifier.padding(vertical = 16.dp),
            style = LocalTextStyle.current.copy(
                textIndent = TextIndent(
                    firstLine = 70.sp
                )
            )
        )
        Text(
            text = stringResource(id = R.string.text4), //text2
            fontFamily = fontDewi,
            style = LocalTextStyle.current.copy(
                textIndent = TextIndent(
                    firstLine = 70.sp
                )
            )
        )
    }

    Column (modifier = Modifier.padding(vertical = 40.dp)) {
        Image(
            painter = painterResource(id = R.drawable.usa),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.authors2),
            fontFamily = fontKristolit,
            fontSize = 12.sp
        )
        Text(
            text = stringResource(id = R.string.footnote7),
            fontFamily = fontCopyright,
            fontSize = 12.sp,
            lineHeight = 10.sp
        )
    }
    //image

    Text(
        text = stringResource(id = R.string.text5),
        fontFamily = fontDewi,
        style = LocalTextStyle.current.copy(
            textIndent = TextIndent(
                firstLine = 70.sp
            )
        )
    )

    val articles = listOf(
        Pair(
            R.drawable.allora_and_calzadilla,
            stringResource(R.string.footnote4)
        ),
        Pair(
            R.drawable.sovereignty,
            stringResource(R.string.footnote5)
        ),
        Pair(
            R.drawable.space,
            stringResource(R.string.footnote6)
        )
    )

    PhotoPager(articles)


    Text(
        text = stringResource(id = R.string.text6),
        fontFamily = fontDewi,
        style = LocalTextStyle.current.copy(
            textIndent = TextIndent(
                firstLine = 70.sp
            )
        ),
        modifier = Modifier.padding(bottom = 20.dp)
    )
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoPager(articles: List<Pair<Int, String>>) {
    val pagerState = rememberPagerState(pageCount = { articles.size })
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier.padding(vertical = 40.dp)
    ) {
        IconButton(
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        pagerState.currentPage - 1
                    )
                }
            },
            enabled = pagerState.currentPage > 0,
            modifier = Modifier.padding(top = 110.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Предыдущее фото"
            )
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val article = articles[page]
                Image(
                    painter = painterResource(id = article.first),
                    contentDescription = null,
                    modifier = Modifier.padding(vertical = 40.dp)
                )
                Text(
                    text = article.second,
                    fontFamily = fontCopyright,
                    fontSize = 12.sp,
                    lineHeight = 10.sp
                )
            }
        }

        IconButton(
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        pagerState.currentPage + 1
                    )
                }
            },
            enabled = pagerState.currentPage < articles.size - 1,
            modifier = Modifier.padding(top = 110.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Следующее фото"
            )
        }
    }
}

@Composable
fun ArticleScreen(сontent: @Composable () -> Unit) {
    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.article_title)
                        )
                    }
                )
            }) { innerPadding ->
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(8.dp)
                    .verticalScroll(scrollState)
            ) {
                сontent()
            }
        }
    }
}


@Preview( showBackground = true)
@Composable
fun MainScreenPreview() {
    ArticleScreen{
        Introduction()
    }
}


@Preview(
    showBackground = true,
    heightDp = 2000
)
@Composable
fun HolySeePavilionPreview() {
    ArticleScreen{
        HolySeePavilion()
    }
}


@Preview(
    showBackground = true,
    heightDp = 2000
)
@Composable
fun USAPavilionPreview() {
    ArticleScreen{
        USAPavilion()
    }
}

