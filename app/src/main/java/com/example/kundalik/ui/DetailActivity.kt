package com.example.kundalik.ui

import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kundalik.R
import com.example.kundalik.databinding.ActivityDetailBinding
import com.example.kundalik.domain.Notes
import com.example.kundalik.ui.viewmodel.NotesViewModel
import com.example.kundalik.ui.viewmodel.obtainViewModel
import com.example.kundalik.utils.Utils

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.detailScreen) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = obtainViewModel(this)
        val notes : Notes? = if(VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableExtra("notes", Notes::class.java)
        }else{
            intent.getParcelableExtra("notes")
        }
        notes?.let { note->
            binding.apply {
                textViewDate.text = note.date
                editTextTitle.setText(note.title)
                editTextDescription.setText(note.description)

                val textWatcher = object : TextWatcher{
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {}

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        buttonSave.visibility = View.VISIBLE
                        buttonSave.setOnClickListener{
                            val id = note.id
                            val date = Utils.getCurrentDate()
                            val title = editTextTitle.text.toString()
                            val description = editTextDescription.text.toString()
                            val updatedNotes = Notes(id, date, title, description)
                            viewModel.update(updatedNotes)
                            finish()

                        }

                    }

                    override fun afterTextChanged(s: Editable?) {}

                }
                editTextTitle.addTextChangedListener(textWatcher)
                editTextDescription.addTextChangedListener(textWatcher)

                deleteButton.setOnClickListener {
                    viewModel.delete(note)
                    finish()
                }
                buttonBack.setOnClickListener {
                    finish()
                }
            }
        }

    }
}