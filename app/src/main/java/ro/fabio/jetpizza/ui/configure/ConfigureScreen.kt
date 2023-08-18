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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.fabio.jetpizza.model.Pizza
import ro.fabio.jetpizza.model.ExtraItem
import ro.fabio.jetpizza.model.allPizzas
import ro.fabio.jetpizza.model.extraItems
import ro.fabio.jetpizza.ui.common.Minus
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



            Box (
                modifier = Modifier.fillMaxHeight()
            ){

                //Suprapunerea este bottom-up (bottom = front, up = back)

                pizzaConfigurationBody(modifier = Modifier.padding(bottom = 60.dp), selectedPizza = selectedPizza)

                //in the front
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color = Color(0xff3b6a00))
                    .align(alignment = Alignment.BottomEnd),

                )
            }

        }


    }
}

@Composable
fun pizzaConfigurationBody(
    modifier: Modifier,
    selectedPizza: Pizza
){

    //ToDo: now, the extraItemsMutableList is not needed to be a mutableState, can be left as extraItems
    //var extraItemsMutableList = remember{ mutableStateOf(extraItems) }

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

        Spacer(modifier = Modifier.padding(10.dp))

       extraItems.forEachIndexed {
            index, extraItem -> extraItemRow(
                modifier = Modifier.padding(start = 24.dp, top = 5.dp, bottom = 5.dp, end = 24.dp),
                itemName = extraItem.name,
                itemPrice = extraItem.price,
                itemAmount = extraItem.amount.value,
                itemIndex = index,
                incrementAction = {
                    //ToDo: leaving comments here to emphasize problem -> solution
                    //ToDo: i had to change ExtraItem to have the "amount" a mutable state itself
                    //ToDo: now, the extraItemsMutableList is not needed to be a mutableState
//                    var tempList = extraItemsMutableList
//                    tempList.value[index].amount++
//                    extraItemsMutableList = tempList
                                  extraItem.amount.value++
                                  },
                decrementAction = {
                                  if(extraItem.amount.value>0) extraItem.amount.value--
//                    var tempList = extraItemsMutableList
//                    if(tempList.value[index].amount > 0) tempList.value[index].amount--
//                    extraItemsMutableList = tempList
                                  },
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

    }
}

@Composable
fun extraItemRow(
    modifier: Modifier,
    itemName: String,
    itemPrice: Float,
    itemAmount: Int,
    itemIndex: Int,
    incrementAction: () -> Unit,
    decrementAction: () -> Unit,
){

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            itemName,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight(600)
        )

        Spacer(modifier = Modifier.weight(1f))

        addExtraQuantityRow(
            amount = itemAmount,
            incrementAction = incrementAction,
            decrementAction = decrementAction,
        )

        Spacer(modifier = Modifier.padding(horizontal = 5.dp))

        Text(
            text = String.format("%.2f", itemAmount * itemPrice),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight(600)
        )
    }

}

@Composable
fun doughTypeSelect(
    modifier: Modifier
){

    var selectedButton = remember { mutableStateOf(0) }

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
//            Button(
//                modifier = Modifier.weight(1f),
//                onClick = { /*TODO*/ }
//            ) {
//                Text(
//                    text = "Thin",
//                    style = MaterialTheme.typography.bodyMedium,
//                    fontWeight = FontWeight(500)
//                )
//            }

            doughButton(
                modifier = Modifier.weight(1f),
                isSelected = selectedButton.value == 0,
                onClick = { selectedButton.value = 0 },
                selectColor = Color(0xff84bc4d),
                text = "Thin")


            Spacer(modifier = Modifier.padding(horizontal = 5.dp))

//            Button(
//                modifier = Modifier.weight(1f),
//                onClick = { /*TODO*/ }
//            ) {
//                Text(
//                    text = "Fluffy",
//                    style = MaterialTheme.typography.bodyMedium,
//                    fontWeight = FontWeight(500)
//                )
//            }

            doughButton(
                modifier = Modifier.weight(1f),
                isSelected = selectedButton.value == 1,
                onClick = { selectedButton.value = 1 },
                selectColor = Color(0xff84bc4d),
                text = "Fluffy")


        }
//        Button(modifier = modifier.fillMaxWidth(),
//            onClick = { /*TODO*/ }) {
//            Text(
//                text = "Cheesy Crust",
//                style = MaterialTheme.typography.bodyMedium,
//                fontWeight = FontWeight(500)
//            )
//        }

        doughButton(
            modifier = modifier.fillMaxWidth(),
            isSelected = selectedButton.value == 2,
            onClick = { selectedButton.value = 2 },
            selectColor = Color(0xff84bc4d),
            text = "Cheesy Crust")
    }
}

@Composable
fun doughButton(
    modifier: Modifier,
    isSelected: Boolean,
    onClick: () -> Unit,
    selectColor: Color,
    text: String
){
    Button(

        //modifier = modifier.background(if (isSelected) selectColor else  Color(0xff3b6a00)),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = if (isSelected) selectColor else  MaterialTheme.colorScheme.primary),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight(500)
        )
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
fun addExtraQuantityRow(
    amount: Int,
    incrementAction: () -> Unit,
    decrementAction: () -> Unit,
){

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedIconButton(onClick = incrementAction) {
            Icon(Icons.Filled.Add,contentDescription = null)
        }

        Text(
            amount.toString(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight(600)
        )

        OutlinedIconButton(onClick = decrementAction) {
            Icon(Icons.Filled.Minus,contentDescription = null)
        }
    }

}


