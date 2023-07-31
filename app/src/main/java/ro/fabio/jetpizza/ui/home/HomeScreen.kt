package ro.fabio.jetpizza.ui.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.fabio.jetpizza.model.Pizza
import ro.fabio.jetpizza.model.allPizzas
import ro.fabio.jetpizza.ui.common.UrlImage


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        pizzas = allPizzas,
    )
}

@Composable
fun HomeScreen(
    pizzas: List<Pizza>
) {

    var searchText = remember { mutableStateOf(TextFieldValue("")) }

//    Log.d("SearchTag","AAAAA")
//    Log.d("SearchTag",searchText.value.toString().lowercase())

    val searchedPizzaList : List<Pizza> = pizzas.filter { listItem -> listItem.name.lowercase().contains(searchText.value.text.toString().lowercase()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ){

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Spacer(modifier = Modifier.padding(10.dp))

            SearchField(searchTextState = searchText)

            Spacer(modifier = Modifier.padding(10.dp))

            PizzaColumn(pizzas = searchedPizzaList)
        }
    }
}

@Composable
fun SearchField(
    searchTextState: MutableState<TextFieldValue>
){


    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchTextState.value,
            onValueChange = {newValue -> searchTextState.value = newValue},
            trailingIcon = {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = "")
            }
        )
    }

}

@Composable
private fun PizzaColumn(
    pizzas: List<Pizza>,
    modifier: Modifier = Modifier
) {
    val state = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(state)
            .padding(start = 24.dp, top = 0.dp, end = 24.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        pizzas.forEach { pizza ->
            Box(
                modifier = Modifier
                    .border(
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clip(shape = RoundedCornerShape(16.dp))
            ){
                pizzaCard(
                    pizza
                )
            }

        }
    }
}

@Composable
fun pizzaCard(
    pizza: Pizza,
){
    Box(
        modifier = Modifier.
        fillMaxWidth(),
    ){
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ){

            pizzaImage(pizza)

            Box(
                modifier = Modifier.padding(10.dp)
            ){
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = pizza.name,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight(600)
                    )

                    Text(
                        text = pizza.description,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight(500)
                    )

                    Text(
                        text = pizza.price.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight(600)
                    )
                }
            }



        }
    }
}

@Composable
fun pizzaImage(
    pizza: Pizza,
    ){

    UrlImage(
        modifier = Modifier
            .fillMaxSize(),
        url = pizza.imageUrl,
        //placeholder dar e nullable
        contentDescription = "pick a pizza",
        contentScale = ContentScale.Crop

    )

}