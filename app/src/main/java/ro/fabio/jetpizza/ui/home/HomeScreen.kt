package ro.fabio.jetpizza.ui.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.OutlinedIconButton
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
import ro.fabio.jetpizza.ui.ImageAssets
import ro.fabio.jetpizza.ui.common.UrlImage


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        pizzas = allPizzas,
        onPizzaSelected = {}
    )
}

@Composable
fun HomeScreen(
    pizzas: List<Pizza>,
    onPizzaSelected: (Pizza) -> Unit //onPizzaSelected is of type Pizza and does function passed from Main.kt
) {

    var searchText = remember { mutableStateOf(TextFieldValue("")) }

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

            Header(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                onProfileClick = {"/* ready for later implementation */"},
                onFavoritesClick = {"/* ready for later implementation */"},
                onMoreClick = {"/* ready for later implementation */"},
            )

            Spacer(modifier = Modifier.padding(10.dp))

            SearchField(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                searchTextState = searchText
            )

            Spacer(modifier = Modifier.padding(10.dp))

            PizzaColumn(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                pizzas = searchedPizzaList,
                onPizzaClick = onPizzaSelected // onPizzaClick gets passed the function from onPizzaSelected
            )
        }
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
    onProfileClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onMoreClick: () -> Unit
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(ImageAssets.Logo, "logo")



        Row() {

            OutlinedIconButton(
                onClick = onProfileClick,
            ) {
                Icon(
                    Icons.Outlined.Person,
                    contentDescription = "profile"
                )
            }

            OutlinedIconButton(
                onClick = onProfileClick,
            ) {
                Icon(
                    Icons.Outlined.Favorite,
                    contentDescription = "profile"
                )
            }

            OutlinedIconButton(
                onClick = onProfileClick,
            ) {
                Icon(
                    Icons.Outlined.MoreVert,
                    contentDescription = "profile"
                )
            }
        }

    }

}

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    searchTextState: MutableState<TextFieldValue>
){


    Box(modifier = modifier
        .fillMaxWidth()
    ){
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
    modifier: Modifier = Modifier,
    pizzas: List<Pizza>,
    onPizzaClick: (Pizza) -> Unit //onPizzaClick is of type Pizza and does function passed from onPizzaSelected
) {
    val state = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(state)
            .padding(bottom = 24.dp),
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
                    .clickable {
                        onPizzaClick(pizza)
                    }
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