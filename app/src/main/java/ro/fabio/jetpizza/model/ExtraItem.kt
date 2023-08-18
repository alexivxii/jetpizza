package ro.fabio.jetpizza.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class ExtraItem (
    val name: String,
    val price: Float,
    //var amount: Int,
    var amount: MutableState<Int>,
)

val extraItems = listOf(
    ExtraItem("Sos dulce", 1.99f, mutableStateOf(0)),
    ExtraItem("Sos picant", 2.99f,mutableStateOf(0)),
    ExtraItem("Foccacia", 3.99f, mutableStateOf(0)),
    ExtraItem("Surpriza", 4.99f, mutableStateOf(0)),
)