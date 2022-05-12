package com.sizzle.basic_scaffolding_viewmode_navigation.screens

//----------------------------------------------------------------------
// Imports
//----------------------------------------------------------------------
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sizzle.basic_scaffolding_viewmode_navigation.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty0


//----------------------------------------------------------------------
// Home Screen - Setup Scaffolding
//----------------------------------------------------------------------
@Composable
fun HomeScreen(
    model: MainViewModel
) {

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope, scaffoldState) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onAddItem = model::addItem              // Pass View Model Function
            ) },
        drawerContent = {
            DrawerContent(
                onAddItem = model::addItem,             // Pass View Model Function
                onResetList = model::clearItemList      // Pass View Model Function
            ) },
        drawerGesturesEnabled = true,
        content = {
            Content(
                itemList = model.itemList,              // Pass Item List
                onAddItem = model::addItem              // Pass View Model Function
            ) },
        bottomBar = { BottomBar() }
    )

}


//----------------------------------------------------------------------
// Content
//----------------------------------------------------------------------
@Composable
fun Content(
    itemList: List<Int>,
//    itemList: KProperty0<SnapshotStateList<Int>>,
    onAddItem: (Int) -> Unit
) {

    val focusManager = LocalFocusManager.current

    Column(Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = { onAddItem((1..99).random()) },
                modifier = Modifier.height(60.dp)
            ) {
                Text(text = "Add Item")
            }

            Spacer(modifier = Modifier.width(5.dp))

            var text by remember { mutableStateOf("0") }
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.width(150.dp),
                label = { Text("Greater Than") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = "Search Icon"
                    )
                },
                maxLines = 1,
                textStyle = MaterialTheme.typography.subtitle1,
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )

            Spacer(modifier = Modifier.width(5.dp))

            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.LightGray,
                    contentColor = Color.White),
                modifier = Modifier
                    .height(64.dp)
                    .padding(top = 8.dp)
            )
            {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = "Search Icon"
                )
            }




        }

        Divider()

        ShowList(itemList = itemList)

    }
}


@Composable
fun ShowList(
    itemList: List<Int>
) {
    LazyColumn {
        items(itemList){ index ->
            Text("$index", fontSize = 20.sp)
        }
    }
}


//----------------------------------------------------------------------
// Top Bar
//----------------------------------------------------------------------
@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    TopAppBar(
        title = {
            Text(text = "Basic Scaffold")
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
            ) {
                Icon(
                    Icons.Rounded.Menu,
                    contentDescription = "Open Menu Drawer"
                )
            }
        })
}


//----------------------------------------------------------------------
// Drawer
//----------------------------------------------------------------------
@Composable
fun DrawerContent(
    onAddItem: (Int) -> Unit,
    onResetList: () -> Unit
) {
    ClickableText(
        text = AnnotatedString("Clear List"),
        style = TextStyle(color = Color.Blue, fontSize = 20.sp),
        onClick = {
            onResetList()
        }
    )
    ClickableText(
        text = AnnotatedString("Add Three 99's"),
        style = TextStyle(color = Color.Blue, fontSize = 20.sp),
        onClick = {
            onAddItem(99)
            onAddItem(99)
            onAddItem(99)
        }
    )
}


//----------------------------------------------------------------------
// Floating Action Button
//----------------------------------------------------------------------
@Composable
fun FloatingActionButton(
    onAddItem: (Int) -> Unit
) {
    FloatingActionButton(
        onClick = {
            onAddItem((1..99).random())
        },
    ){
        IconButton(onClick = {
            onAddItem((1..99).random())
        }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Floating Action Button",
            )
        }
    }
}


//----------------------------------------------------------------------
// Scaffold Bottom Bar
//----------------------------------------------------------------------
@Composable
fun BottomBar() {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Text("Bottom App Bar")
    }
}