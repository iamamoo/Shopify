package a.amoo.shopify.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import kotlinx.coroutines.tasks.await

class ShoeRepo {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var lastVisible: DocumentSnapshot? = null
    val pageSize: Long = 10

    fun getShoeList(): Task<QuerySnapshot> {
        val lastVisible: DocumentSnapshot? = null
        val pageSize: Long = 10


        return if (lastVisible == null) {
            //Load First Page
            db.collection("main_card")
                .limit(10)
                .get()
        } else {
            db.collection("main_card")
                .startAfter(lastVisible)
                .limit(pageSize)
                .get()
        }
    }

    fun getFashionList(): Task<QuerySnapshot> {

        return if (lastVisible == null) {
            //Load First Page
            db
                .collection("fashion")
                .limit(10)
                .get()
        } else {
            db.collection("fashion")
                .startAfter(lastVisible)
                .limit(pageSize)
                .get()
        }
    }

    fun getWatchList(): Task<QuerySnapshot> {

        return if (lastVisible == null) {
            //Load First Page
            db
                .collection("watch")
                .limit(10)
                .get()
        } else {
            db.collection("watch")
                .startAfter(lastVisible)
                .limit(pageSize)
                .get()
        }
    }

    fun getGlassesList(): Task<QuerySnapshot> {
        val lastVisible: DocumentSnapshot? = null
        val pageSize: Long = 10


        return if (lastVisible == null) {
            //Load First Page
            db
                .collection("glasses")
                .limit(10)
                .get()
        } else {
            db.collection("glasses")
                .startAfter(lastVisible)
                .limit(pageSize)
                .get()
        }
    }

    fun getGroceryList(): Task<QuerySnapshot> {
        val lastVisible: DocumentSnapshot? = null
        val pageSize: Long = 10


        return if (lastVisible == null) {
            //Load First Page
            db
                .collection("grocery")
                .limit(10)
                .get()
        } else {
            db.collection("grocery")
                .startAfter(lastVisible)
                .limit(pageSize)
                .get()
        }
    }

    fun getJewelleryList(): Task<QuerySnapshot> {
        val lastVisible: DocumentSnapshot? = null
        val pageSize: Long = 10


        return if (lastVisible == null) {
            //Load First Page
            db
                .collection("jewellery")
                .limit(10)
                .get()
        } else {
            db.collection("jewellery")
                .startAfter(lastVisible)
                .limit(pageSize)
                .get()
        }
    }

}

