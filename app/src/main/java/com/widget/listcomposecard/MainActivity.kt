package com.widget.listcomposecard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.widget.listcomposecard.core.GraphEcgWidget
import com.widget.listcomposecard.core.GraphMode
import com.widget.listcomposecard.ui.theme.ListComposeCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ListComposeCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WidgetsListApp()
                }
            }
        }
    }
}

data class CardData(
    val title: String,
    val seriesLength: Int,
    var graphMode: GraphMode,
    var start : Boolean
)

@Composable
fun WidgetsListApp() {

    val TAG = "ListApp"
    val itemsList = remember { mutableStateListOf<CardData>() }
    val lazyListState = rememberLazyListState()

//  Include back press support
    backPressSupport(itemsList)

    Column {
        LazyColumn(
            modifier = Modifier.weight(10f),
            contentPadding = PaddingValues(vertical = 8.dp),
            state = lazyListState,
            ) {

            items(itemsList) { item ->
                WidgetCard(itemsList = itemsList, item = item)
            }
        }

        FloatingActionButton(
            contentColor = Color.White,
            containerColor = Color.Gray,
            modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 16.dp, bottom = 16.dp),
            onClick = {
                itemsList.add(CardData(
                    title = "Graph #${itemsList.size + 1}",
                    seriesLength = 120+50*(itemsList.size),
                    graphMode = if (itemsList.size % 2 == 0) GraphMode.Flowing else GraphMode.Overlay,
                    start = true))
            }
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add Item")
        }
    }

    LaunchedEffect(lazyListState.canScrollForward) {
        if (lazyListState.canScrollForward && itemsList.isNotEmpty()) {
            lazyListState.scrollToItem(itemsList.lastIndex)
        }
    }
}

@Composable
fun WidgetCard(itemsList: SnapshotStateList<CardData>, item: CardData) {

    println(" [WidgetCard] $item.title ${item.seriesLength} ${item.graphMode}")

    var checked by remember { mutableStateOf(item.start) }
    var mode by remember { mutableStateOf(item.graphMode) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        ),
    ) {
        Column(
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(28.dp)
            ) {
                ItalicText(text = item.title,
                    modifier = Modifier.padding(start = 8.dp))

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {
                        showDeleteDialog = true },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        modifier = Modifier
                            .size(SwitchDefaults.IconSize)
                    )
                }
            }

            HorizontalLine(color = Color.Black)

            RectangleArea(seriesLength = item.seriesLength, mode = item.graphMode, toggle = checked)

            HorizontalLine(color = Color.Black)

            Row (
                modifier = Modifier.align(Alignment.End),
            ) {

                CustomizedSwitch(
                    checked = if (mode == GraphMode.Overlay) true else false,
                    onCheckedChange = {
                        mode = if (it) GraphMode.Overlay else GraphMode.Flowing
                        item.graphMode = mode
                    }
                )

                ItalicText(text = item.graphMode.name,
                    modifier = Modifier.padding(start = 8.dp, top=12.dp))

                Spacer(modifier = Modifier.weight(1f))

                ItalicText(text = if (checked) "Stop" else "Start",
                    modifier = Modifier.padding(end = 8.dp, top=12.dp))

                CustomizedSwitch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        item.start = checked
                    }
                )
            }
        }
    }

    if (showDeleteDialog) {

        showAlertDialog(
            title = "Delete Widget [${item.title}]",
            text = "Are you sure delete?",
            onDismiss = { showDeleteDialog = false },
            onOkClick = {
                Log.d("Back", "delete item")
                itemsList.remove(item)
            }
        )
    }
}

@Composable
fun CustomizedSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White, // Change color of the thumb when switch is checked
            uncheckedThumbColor = Color.Gray, // Change color of the thumb when switch is unchecked
            checkedTrackColor = Color.Gray, // Change color of the track when switch is checked
            uncheckedTrackColor = Color.LightGray // Change color of the track when switch is unchecked
        ),
        thumbContent = if (checked) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize)
                )
            }
        } else {
            null
        }
    )
}

@Composable
fun ItalicText(text: String, modifier: Modifier) {
    Text(
        text = text,
        style = TextStyle(
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp),
        modifier = modifier
    )
}

@Composable
fun HorizontalLine(color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color)
    )
}

@Composable
fun RectangleArea(seriesLength : Int, mode : GraphMode, toggle : Boolean) {

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    GraphEcgWidget(screenWidthDp, screenHeightDp/6, seriesLength, mode, toggle)

}

