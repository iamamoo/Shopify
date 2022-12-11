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

class GlassesViewModel : ViewModel() {

    private val firebaseRepository: ShoeRepo = ShoeRepo()

    private val glassesList: MutableLiveData<List<MainCard>> by lazy {
        MutableLiveData<List<MainCard>>().also {
            data()
        }
    }

    fun getGlassesList(): LiveData<List<MainCard>> {
        return glassesList
    }

     fun data() {
         viewModelScope.launch(Dispatchers.IO) {
             firebaseRepository.getGlassesList().addOnCompleteListener {
                 if (it.isSuccessful) {
                     val result = it.result
                     if (result!!.isEmpty) {
                         //No more results
                     } else {
                         //Results ready to be loaded
                         if (glassesList.value == null) {
                             glassesList.value = result.toObjects(MainCard::class.java)
                         } else {
                             glassesList.value =
                                 glassesList.value!!.plus(result.toObjects(MainCard::class.java))
                         }

                         val lastItem: DocumentSnapshot = result.documents[result.size() - 1]
                         firebaseRepository.lastVisible = lastItem
                     }
                 } else {
                     Log.d("VIEW_MODEL_FASHION", "Error : ${it.exception!!.message}")
                 }
             }
         }
    }

}