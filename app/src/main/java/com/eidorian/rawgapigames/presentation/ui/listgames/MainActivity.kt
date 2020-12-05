package com.eidorian.rawgapigames.presentation.ui.listgames

import android.graphics.Color
import android.os.Bundle
import android.widget.ListAdapter
import androidx.activity.viewModels
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.eidorian.rawgapigames.R
import com.eidorian.rawgapigames.databinding.ActivityMainBinding
import com.eidorian.rawgapigames.utils.Status.SUCCESS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val recycler = binding.listGames
        val adapter = ListGamesAdapter()
        recycler.adapter = adapter
        recycler.hasFixedSize()

        viewModel.getGamesList().observe(this, Observer { viewState ->
            when(viewState.state){
                SUCCESS ->{
                    adapter.setData(viewState.data)
                }
                else -> binding.listGames.setBackgroundColor(resources.getColor(R.color.colorAccent))
            }
        })
    }
}