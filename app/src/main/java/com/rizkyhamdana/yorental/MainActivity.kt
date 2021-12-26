package com.rizkyhamdana.yorental

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizkyhamdana.yorental.data.DataResponse
import com.rizkyhamdana.yorental.databinding.ActivityMainBinding
import com.rizkyhamdana.yorental.detail.DetailsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: ListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Daftar Kendaraan Rental"

        showLoading(true)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        adapter = ListAdapter()
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = LinearLayoutManager(this)
        mainViewModel.getData().observe(this, {
            adapter.setProducts(it)
            showLoading(false)
        })

        adapter.setOnItemClickCallback(object : ListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: DataResponse) {
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra(DetailsActivity.EXTRA_DATA, data)
                startActivity(intent)
            }

        })

    }
    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}