package pe.edu.upeu.navigationjpc.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import pe.edu.upeu.navegacionjpc.ui.presentation.screens.CalcUPeU
import pe.edu.upeu.navigationjpc.ui.presentation.screens.HomeScreen
import pe.edu.upeu.navigationjpc.ui.presentation.screens.ProfileScreen
import pe.edu.upeu.navigationjpc.ui.presentation.screens.SettingsScreen
import pe.edu.upeu.navigationjpc.ui.theme.ThemeType
import pe.edu.upeu.navigationjpc.utils.isNight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppDrawer(
    darkMode: MutableState<Boolean>,
    themeType: MutableState<ThemeType>
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Lista de destinos para el Drawer
    val drawerItems = listOf(
        DrawerItem("Home", "home"),
        DrawerItem("Profile", "profile"),
        DrawerItem("Settings", "settings"),
        DrawerItem("Calc", "calc")
    )
    var selectedItem by remember { mutableStateOf("home") }
    // Drawer con navegación
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                //.fillMaxSize()

            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Menú", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 16.dp))
                    drawerItems.forEach { item ->
                        // Resalta el item seleccionado
                        val backgroundColor = if (item.route == selectedItem) Color.DarkGray else Color.Transparent
                        Text(
                            text = item.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(backgroundColor)
                                .padding(8.dp)
                                .clickable {
                                    selectedItem = item.route
                                    navController.navigate(item.route)
                                    scope.launch { drawerState.close() }
                                }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    ) {
        Scaffold(
            /*topBar = { CustomTopAppBar(
                "",
                darkMode = darkMode,
                themeType = themeType,
                navController = navController,
                scope = scope,
                scaffoldState = drawerState,
                openDialog={}
            )
            }*/
            topBar = {
                TopAppBar(
                    title = { Text("Jetpack Compose Drawer") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            content = { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    NavigationHost(navController)
                }
            },
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        )
    }
}

data class BottomNavItem(val title: String, val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Home", "home", Icons.Default.Home),
        BottomNavItem("Profile", "profile", Icons.Default.Person),
        BottomNavItem("Settings", "settings", Icons.Default.Settings)
    )

    // Estado para gestionar el elemento seleccionado
    var selectedItem by remember { mutableStateOf("home") }

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem == item.route,
                onClick = {
                    selectedItem = item.route
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.White,
                    indicatorColor = Color.LightGray,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("profile") { ProfileScreen() }
        composable("settings") { SettingsScreen() }
        composable("calc") { CalcUPeU() }
    }
}

data class DrawerItem(val title: String, val route: String)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val themeType= remember{ mutableStateOf(ThemeType.RED) }
    val darkThemex= isNight()
    val darkTheme = remember { mutableStateOf(darkThemex) }
    MyAppDrawer(darkMode = darkTheme, themeType =themeType  )
}