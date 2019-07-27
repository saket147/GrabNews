package `in`.track.grabnews.ui

import `in`.track.grabnews.model.Article
import `in`.track.grabnews.network.HeadlinesApi
import `in`.track.grabnews.R
import `in`.track.grabnews.base.BaseViewModel
import `in`.track.grabnews.model.HeadLinesDao
import android.view.View
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeadlinesViewModel(private val headLinesDao: HeadLinesDao) : BaseViewModel() {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessages: MutableLiveData<Int> = MutableLiveData()
    val headLinesAdapter: HeadLinesAdapter = HeadLinesAdapter()

    val errorClickListener = View.OnClickListener {
        loadingVisibility.value = View.VISIBLE
        loadHeadlines()
    }

    init {
        loadHeadlines()
    }

    @Inject
    lateinit var headlinesApi: HeadlinesApi

    private lateinit var subscriptons: Disposable

    private fun loadHeadlines() {
        subscriptons = Observable.fromCallable { headLinesDao.headlinesList }
            .concatMap { dbHeadlineList ->
                if (dbHeadlineList.isEmpty())
                    headlinesApi.getHeadlines("us").concatMap { apiHeadlineList ->
                        apiHeadlineList.articles?.let { headLinesDao.insertAll(it) }
                        Observable.just(apiHeadlineList.articles)
                    }
                else
                    Observable.just(dbHeadlineList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveHeadlinesStart() }
            .doOnTerminate { onRetrieveHeadlinesFinish() }
            .subscribe(
                { result->
                    result?.let { onRetrieveHeadlinesSuccess(result) }
                },
                {
                    onRetrieveHeadlinesError()
                }
            )
    }

    private fun onRetrieveHeadlinesStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessages.value = null
    }

    private fun onRetrieveHeadlinesFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveHeadlinesSuccess(articleList: List<Article>) {
        loadingVisibility.value = View.GONE
        headLinesAdapter.updateArticleList(articleList)
    }

    private fun onRetrieveHeadlinesError() {
        errorMessages.value = R.string.headlines_error
    }

    override fun onCleared() {
        super.onCleared()
        subscriptons.dispose()
    }
}