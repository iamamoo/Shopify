package a.amoo.shopify.viewmodels

import a.amoo.shopify.repository.ShoeRepo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import a.amoo.shopify.models.MainCard
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.launch

class WatchesViewModel : ViewModel() {
    init {
        viewModelScope.launch {
            data()
        }
    }

    private val firebaseRepository: ShoeRepo = ShoeRepo()

    private val watchList: MutableLiveData<List<MainCard>> by lazy {
        MutableLiveData<List<MainCard>>().also {
            data()
        }
    }

    fun getWatchList(): LiveData<List<MainCard>> {
        return watchList
    }

     fun data() {
        firebaseRepository.getWatchList().addOnCompleteListener {
            if (it.isSuccessful) {
                val result = it.result
                if (result!!.isEmpty) {
                    //No more results
                } else {
                    //Results ready to be loaded
                    if (watchList.value == null) {
                        watchList.value = result.toObjects(MainCard::class.java)
                    } else {
                        watchList.value =
                            watchList.value!!.plus(result.toObjects(MainCard::class.java))
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