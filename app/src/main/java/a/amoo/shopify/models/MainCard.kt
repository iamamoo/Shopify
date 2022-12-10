package a.amoo.shopify.models

data class MainCard(
    // This pojo class helps us to fetch data from firestore
    val url : String? = null,
    val category : String? = null,
    val title : String? = null,
    val description : String? = null,
    val price : Int? = null
)
