package com.sizzle.basic_scaffolding_viewmode_navigation
//======================================================================
// BASIC SCAFFOLDING, VIEW MODEL, AND NAVIGATION
//	Created: 9 May 2022 by Jason
//
//	Purpose: Basic, Customizable Layout for Most Projects.  Shows how
//           to add items to a Lazy Column using button, drawer, or
//           floating action button.
//
//	Added Dependencies:
//
//	  // Added for Navigation
//    implementation "androidx.navigation:navigation-runtime-ktx:$nav_version"
//    implementation "androidx.navigation:navigation-compose:$nav_version"
//
//  Where:
//
//     compose_version = '1.1.1'       // Changed to 1.1.1 from 1.0.1
//     nav_version = '2.4.2'           // Added for Navigation Components
//
//======================================================================

//----------------------------------------------------------------------
// Imports
//----------------------------------------------------------------------
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.sizzle.basic_scaffolding_viewmode_navigation.ui.theme.Basic_Scaffolding_ViewMode_NavigationTheme
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sizzle.basic_scaffolding_viewmode_navigation.screens.DetailsScreen
import com.sizzle.basic_scaffolding_viewmode_navigation.screens.HomeScreen
import com.sizzle.basic_scaffolding_viewmode_navigation.screens.SubDetailsScreen


//----------------------------------------------------------------------
// View Model
//----------------------------------------------------------------------
class MainViewModel : ViewModel() {

    //------------------------------------------------------
    // Create List & List Functions
    //------------------------------------------------------
    val itemList = mutableStateListOf<Int>()

    val filtering = mutableStateOf<Boolean>(false)
    val itemListFiltered = mutableStateListOf<Int>()

    fun addItem(item: Int) {
        itemList.add(item)
        println("**** addItem($item)")
    }

    fun clearItemList() {
        itemList.clear()
        println("**** clearItemList()")
    }

    fun filterListGreaterThan(greaterThan: Int) {
        itemListFiltered.clear()
        itemList.forEach { item ->
            if (item > greaterThan) itemListFiltered.add(item)
        }
        println("**** filterListGreaterThan($greaterThan)")
    }

    //------------------------------------------------------
    // Initialize View Model
    //------------------------------------------------------
    init {
        clearItemList()
        filtering.value = false
    }   // End Initializer

}


//----------------------------------------------------------------------
// Main Activity
//----------------------------------------------------------------------
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Basic_Scaffolding_ViewMode_NavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SetupApp()
                }
            }
        }
    }
}


//----------------------------------------------------------------------
// Setup App
//----------------------------------------------------------------------
@Composable
fun SetupApp(model: MainViewModel = MainViewModel()) {

    // Navigation Setup
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.HomeScreen.route,
    ) {
        composable(NavRoutes.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                model = MainViewModel()
            )
        }

        composable(NavRoutes.DetailsScreen.route + "/{Id}") { backStackEntry ->
            val Id = backStackEntry.arguments?.getString("Id")
            DetailsScreen(
                navController = navController,
                model = MainViewModel(),
                Id = Id
            )
        }

        composable(NavRoutes.SubDetailsScreen.route + "/{Id}" + "/{SubId}") { backStackEntry ->
            val Id = backStackEntry.arguments?.getString("Id")
            val SubId = backStackEntry.arguments?.getString("SubId")
            SubDetailsScreen(
                navController = navController,
                model = MainViewModel(),
                Id = Id,
                SubId = SubId
            )
        }

    }       // End NavHost

}       // End Setup App
