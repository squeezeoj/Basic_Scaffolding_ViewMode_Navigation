package com.sizzle.basic_scaffolding_viewmode_navigation.screens

//----------------------------------------------------------------------
// Imports
//----------------------------------------------------------------------
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sizzle.basic_scaffolding_viewmode_navigation.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


//----------------------------------------------------------------------
// Home Screen - Setup Scaffolding
//----------------------------------------------------------------------
@Composable
fun HomeScreen(
    navController: NavHostController,
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
                model = MainViewModel(),
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
    onAddItem: (Int) -> Unit
) {
    Column(Modifier.fillMaxSize()) {

        Button(
            onClick = { onAddItem((1..99).random()) },
        ) {
            Text(text = "Add Item")
        }

        Divider()

        ShowList(itemList = itemList)

    }
}


@Composable
fun ShowList(
    itemList: List<Int>
) {
    LazyColumn() {
        items(items = itemList){ index ->
            Text("$index")
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
    model: MainViewModel,
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