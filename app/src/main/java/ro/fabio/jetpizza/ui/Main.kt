package ro.fabio.jetpizza.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ro.fabio.jetpizza.MainActivity
import ro.fabio.jetpizza.model.Pizza
import ro.fabio.jetpizza.model.allPizzas
import ro.fabio.jetpizza.ui.checkout.CheckoutScreen
import ro.fabio.jetpizza.ui.configure.ConfigureScreen
import ro.fabio.jetpizza.ui.home.HomeScreen


@Composable
fun Main() {
    val navController = rememberNavController();
    NavHost(navController = navController, startDestination = "pizzaconfig"){
        composable(
            route = "home"
        ){
            HomeScreen(
                pizzas = allPizzas,
                onPizzaSelected = { /* to be implemented */ pizza : Pizza -> Log.d("TagTestPizzaClick","Clicked") }   //every item of type Pizza does -> function
            )
        }

        composable(
            route = "pizzaconfig"
        ){
            ConfigureScreen(
                selectedPizza = allPizzas.first()
            )
        }
    }

}