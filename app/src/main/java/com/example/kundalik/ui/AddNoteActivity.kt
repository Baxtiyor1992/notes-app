package com.example.kundalik.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kundalik.R
import com.example.kundalik.databinding.ActivityAddNoteBinding
import com.example.kundalik.databinding.ActivityMainBinding
import com.example.kundalik.domain.Notes
import com.example.kundalik.ui.viewmodel.NotesViewModel
import com.example.kundalik.ui.viewmodel.obtainViewModel
import com.example.kundalik.utils.Utils.getCurrentDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var notesViewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.addScreen) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        notesViewModel = obtainViewModel(this)

        binding.apply {
            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    binding.apply {
                        val isNotEmpty =
                            editTextTitle.text.isNotEmpty() || editTextDescription.text.isNotEmpty()
                        if (isNotEmpty) {
                            saveButton.visibility = View.VISIBLE
                            deleteButton.visibility = View.VISIBLE
                        } else {
                            saveButton.visibility = View.GONE
                            deleteButton.visibility = View.GONE

                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {}

            }
            editTextTitle.addTextChangedListener(textWatcher)
            editTextDescription.addTextChangedListener(textWatcher)
            saveButton.setOnClickListener {
                val title = editTextTitle.text.toString().trim()
                val description = editTextDescription.text.toString().trim()

                if (title.isNotEmpty() && description.isNotEmpty()) {
                    val date = getCurrentDate()
                    val notes = Notes(date = date, title = title, description = description)
                    notesViewModel.insert(notes)

                    val intent = Intent(this@AddNoteActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@AddNoteActivity, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            }

            deleteButton.setOnClickListener {
                editTextTitle.text.clear()
                editTextDescription.text.clear()
                saveButton.visibility = View.INVISIBLE
                deleteButton.visibility = View.INVISIBLE
            }
            buttonBack.setOnClickListener {
                finish()
            }
        }
    }

}
