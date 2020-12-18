package com.liao.loginunittesting.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liao.loginunittesting.R
import com.liao.loginunittesting.model.DataClass
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private var myDisposable: Disposable? = null
    private val repository = Repository()
    val myLiveData: MutableLiveData<ArrayList<DataClass>> by lazy {
        MutableLiveData<ArrayList<DataClass>>()
    }

    open fun getData() {
        val myObservable = repository.getApiCall()

        val myObserver = object : Observer<ArrayList<DataClass>> {
            override fun onSubscribe(d: Disposable) {
                myDisposable = d
            }

            override fun onNext(t: ArrayList<DataClass>) {
                myLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

        }
        myObservable.map { arrayList ->
            arrayList.forEach {
                if (it.id.toInt() % 2 == 0) {
                    it.image = R.drawable.ic_baseline_mood_24
                } else {
                    it.image = R.drawable.ic_baseline_supervised_user_circle_24
                }
            }
            arrayList
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(myObserver)
    }


    fun getDispose(): Disposable? = myDisposable

    fun getMockData(): List<DataClass> {
        val list = ArrayList<DataClass>()
        list.add(DataClass(R.drawable.ic_baseline_supervised_user_circle_24, "A", "1"))
        list.add(DataClass(R.drawable.ic_baseline_mood_24, "D", "2"))
        list.add(DataClass(R.drawable.ic_baseline_supervised_user_circle_24, "B", "3"))
        list.add(DataClass(R.drawable.ic_baseline_mood_24, "F", "4"))
        list.add(DataClass(R.drawable.ic_baseline_supervised_user_circle_24, "C", "5"))
        list.add(DataClass(R.drawable.ic_baseline_mood_24, "G", "6"))
        return list
    }
}