package com.example.kundalik.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kundalik.R
import com.example.kundalik.databinding.ActivitySearchBinding
import com.example.kundalik.domain.Notes
import com.example.kundalik.ui.viewmodel.NotesViewModel
import com.example.kundalik.ui.viewmodel.obtainViewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.searchScreen) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.apply {
            buttonBack.setOnClickListener{
                finish()
            }
            editTextSearch.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val query = s.toString()
                    searchNotes(query)
                }

                override fun afterTextChanged(s: Editable?) {
                    val query = s.toString()
                    searchNotes(query)
                }

            })
        }


    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            binding.searchScreen.requestFocus()
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(binding.searchScreen, InputMethodManager.SHOW_IMPLICIT)
        }
    }
    private fun searchNotes(query: String) {
        viewModel = obtainViewModel(this@SearchActivity)
        if (query.isEmpty()) {
           showRecyclerview(emptyList())
        }else{
            viewModel.searchNotes(query).observe(this) { notes ->
                showRecyclerview(notes)
            }
        }
    }
    private fun showRecyclerview(notes: List<Notes>) {
        viewModel = obtainViewModel(this@SearchActivity)
        with(binding) {
            val adapter = NotesAdapter(notes)
            rvNotes.adapter = adapter
            rvNotes.layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter.setOnItemClickListener(object : NotesAdapter.OnItemClickListener {
                override fun onItemClick(notes: Notes) {
                    val intent = Intent(this@SearchActivity, DetailActivity::class.java)
                    intent.putExtra("notes", notes)
                    startActivity(intent)
                }
            })
        }
    }
}