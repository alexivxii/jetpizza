package ro.fabio.jetpizza.model

class ExtraItem (
    val name: String,
    val price: Float,
    var amount: Int,
)

val extraItems = listOf(
    ExtraItem("Sos dulce", 1.99f, 0),
    ExtraItem("Sos picant", 2.99f,0),
    ExtraItem("Foccacia", 3.99f,0),
    ExtraItem("Surpriza", 4.99f,0),
)