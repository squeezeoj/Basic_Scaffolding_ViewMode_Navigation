package com.sizzle.basic_scaffolding_viewmode_navigation

sealed class NavRoutes(val route: String) {
    object HomeScreen : NavRoutes("home")
    object DetailsScreen : NavRoutes("details")
    object SubDetailsScreen : NavRoutes("subdetails")
}
