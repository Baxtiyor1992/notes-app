package com.example.kundalik.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kundalik.R
import com.example.kundalik.databinding.ActivityMainBinding
import com.example.kundalik.domain.Notes
import com.example.kundalik.ui.viewmodel.NotesViewModel
import com.example.kundalik.ui.viewmodel.obtainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.apply {
            buttonAdd.setOnClickListener {
                intentActivity(this@MainActivity, AddNoteActivity::class.java)
            }
            searchImageView.setOnClickListener {
                intentActivity(this@MainActivity, SearchActivity::class.java)
            }
        }

        showRecyclerview()
    }

    private fun showRecyclerview() {
        viewModel = obtainViewModel(this@MainActivity)
        with(binding) {
            viewModel.allNotes.observe(this@MainActivity)
            { notes ->
                val adapter = NotesAdapter(notes)
                rvNotes.adapter = adapter
                rvNotes.layoutManager = LinearLayoutManager(this@MainActivity)
                adapter.setOnItemClickListener(object : NotesAdapter.OnItemClickListener {
                    override fun onItemClick(notes: Notes) {
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra("notes", notes)
                        startActivity(intent)
                    }
                })

            }
        }
    }

    private fun <T> intentActivity(context: Context, targetActivity: Class<T>) {
        val intent = Intent(context, targetActivity)
        context.startActivity(intent)
    }
}