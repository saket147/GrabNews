package `in`.track.grabnews.ui.activity

import `in`.track.grabnews.R
import `in`.track.grabnews.databinding.ActivityMainBinding
import `in`.track.grabnews.injection.ViewModelFactory
import `in`.track.grabnews.ui.viewModel.HeadlinesViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var headlinesViewModel: HeadlinesViewModel

    private var errorSnackbar: Snackbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.headlinesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        headlinesViewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(
            HeadlinesViewModel::class.java)
        binding.viewModel = headlinesViewModel

        headlinesViewModel.errorMessages.observe(this, Observer {
            errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, headlinesViewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}
