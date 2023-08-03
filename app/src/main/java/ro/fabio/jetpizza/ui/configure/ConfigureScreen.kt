package ro.fabio.jetpizza.ui.configure

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.fabio.jetpizza.model.Pizza
import ro.fabio.jetpizza.model.allPizzas
import ro.fabio.jetpizza.ui.home.Header
import ro.fabio.jetpizza.ui.home.pizzaCard


@Preview
@Composable
fun ConfigureScreenPreview(){
    ConfigureScreen(selectedPizza = allPizzas.first())
}

@Composable
fun ConfigureScreen(
    selectedPizza: Pizza
) {
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

            pizzaConfigurationBody(modifier = Modifier, selectedPizza = selectedPizza)

//            Box(modifier = Modifier
//                .fillMaxWidth()
//                .height(60.dp)
//                .background(color = Color(0xff3b6a00)))

        }


    }
}

@Composable
fun pizzaConfigurationBody(
    modifier: Modifier,
    selectedPizza: Pizza
){
    Column(modifier = modifier.verticalScroll( rememberScrollState())) {
        Box(modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 24.dp)) {
            pizzaCard(pizza = selectedPizza)
        }

        Spacer(modifier = Modifier.padding(10.dp))

        sizeSlider(modifier = Modifier.padding(horizontal = 24.dp))

        Divider()

        Spacer(modifier = Modifier.padding(10.dp))

        doughTypeSelect(modifier = Modifier.padding(horizontal = 24.dp))

        Spacer(modifier = Modifier.padding(10.dp))

        Divider()


    }
}

@Composable
fun doughTypeSelect(
    modifier: Modifier
){
    Column(
    ) {

        Text(
            modifier = modifier,
            text = "Choose dough type",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight(600)
        )

        Spacer(modifier = Modifier.padding(5.dp))

        Row(
            modifier = modifier.fillMaxWidth(),
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Thin",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight(500)
                )
            }

            Spacer(modifier = Modifier.padding(horizontal = 5.dp))

            Button(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Fluffy",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight(500)
                )
            }
        }
        Button(modifier = modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
            Text(
                text = "Cheesy Crust",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight(500)
            )
        }
    }
}

@Composable
fun sizeSlider(
    modifier: Modifier,
){

    Column {
        var sliderPosition = remember { mutableStateOf(0f) }
        //Text(text = sliderPosition.value.toString())

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Small",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight(500)
            )

            Text(
                text = "Medium",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight(500)
            )

            Text(
                text = "Large",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight(500)
            )

            Text(
                text = "XLarge",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight(500)
            )
        }

        Slider(
            modifier = modifier,
            value = sliderPosition.value,
            onValueChange = {pos -> sliderPosition.value = pos },
            valueRange = 0f..3f,
            steps = 2,
            onValueChangeFinished = {
                // launch some business logic update with the state you hold
                // viewModel.updateSelectedSliderValue(sliderPosition)
            },
        )
    }
}

@Composable
fun addExtra(){

}


