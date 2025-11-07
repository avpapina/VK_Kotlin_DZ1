package com.hfad.rk1_papina

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val numbers = mutableListOf<Int>()
    private lateinit var adapter: ItemAdapter
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        setupRecyclerView()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        val columns = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 3 else 4

        layoutManager = GridLayoutManager(this, columns)
        adapter = ItemAdapter(numbers)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.setPadding(0, 0, 0, 80.dpToPx())
    }

    private fun setupClickListeners() {
        val fab = findViewById<View>(R.id.fab)
        fab.setOnClickListener {
            val newNumber = numbers.size + 1
            numbers.add(newNumber)
            adapter.notifyItemInserted(numbers.size - 1)
            recyclerView.smoothScrollToPosition(numbers.size - 1)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        layoutManager.spanCount = if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) 3 else 4
    }

    private fun Int.dpToPx(): Int = (this * resources.displayMetrics.density).toInt()
}