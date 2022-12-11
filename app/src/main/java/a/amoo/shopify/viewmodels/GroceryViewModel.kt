package a.amoo.shopify.viewmodels

import a.amoo.shopify.repository.ShoeRepo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import a.amoo.shopify.models.MainCard
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroceryViewModel : ViewModel() {

    private val firebaseRepository: ShoeRepo = ShoeRepo()

    private val groceryList: MutableLiveData<List<MainCard>> by lazy {
        MutableLiveData<List<MainCard>>().also {
            data()
        }
    }

    fun getGroceryList(): LiveData<List<MainCard>> {
        return groceryList
    }

     fun data() {
        viewModelScope.launch(Dispatchers.IO) {
            firebaseRepository.getGroceryList().addOnCompleteListener {
                if (it.isSuccessful) {
                    val result = it.result
                    if (result!!.isEmpty) {
                        //No more results
                    } else {
                        //Results ready to be loaded
                        if (groceryList.value == null) {
                            groceryList.value = result.toObjects(MainCard::class.java)
                        } else {
                            groceryList.value =
                                groceryList.value!!.plus(result.toObjects(MainCard::class.java))
                        }

                        val lastItem: DocumentSnapshot = result.documents[result.size() - 1]
                        firebaseRepository.lastVisible = lastItem
                    }
                } else {
                    Log.d("VIEW_MODEL_SHOE", "Error : ${it.exception!!.message}")
                }
            }
        }
    }

}