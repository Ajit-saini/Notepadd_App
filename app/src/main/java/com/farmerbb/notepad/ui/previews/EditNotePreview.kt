package com.farmerbb.notepad.ui.previews

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.farmerbb.notepad.R
import com.farmerbb.notepad.model.Note
import com.farmerbb.notepad.model.NoteContents
import com.farmerbb.notepad.model.NoteMetadata
import com.farmerbb.notepad.ui.components.AppBarText
import com.farmerbb.notepad.ui.components.BackButton
import com.farmerbb.notepad.ui.components.DeleteButton
import com.farmerbb.notepad.ui.components.SaveButton
import com.farmerbb.notepad.ui.content.EditNoteContent
import java.util.Date

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun EditNote(note: Note) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { BackButton() },
                title = { AppBarText(note.title) },
                backgroundColor = colorResource(id = R.color.primary),
                actions = {
                    SaveButton()
                    DeleteButton()
                }
            )
        },
        content = {
            EditNoteContent(note.text)
        }
    )
}

@Preview
@Composable
fun EditNotePreview() = MaterialTheme {
    EditNote(
        note = Note(
            metadata = NoteMetadata(
                metadataId = -1,
                title = "Title",
                date = Date(),
                hasDraft = false
            ),
            contents = NoteContents(
                contentsId = -1,
                text = "This is some text",
                draftText = null
            )
        )
    )
}