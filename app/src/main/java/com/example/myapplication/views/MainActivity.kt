package com.example.myapplication.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setupBinding()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = mainViewModel
        binding.rvProducts.apply {
            val decoration = DividerItemDecoration(this@MainActivity, VERTICAL)
            addItemDecoration(decoration)
        }
    }

    @DelicateCoroutinesApi
    fun getProducts(view: View) {
        binding.progress.visibility = View.VISIBLE
        GlobalScope.launch {
            mainViewModel.getProducts()
        }

        mainViewModel.productsLiveData.observe(this, Observer {
            binding.progress.visibility = View.GONE
            if (it != null) {
                mainViewModel.setAdapterData(it)
            } else {
                Toast.makeText(this, getString(R.string.error_product_msg), Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}