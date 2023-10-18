package com.farmerbb.notepad.ui.previews

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.farmerbb.notepad.R
import com.farmerbb.notepad.model.NoteMetadata
import com.farmerbb.notepad.ui.components.AppBarText
import com.farmerbb.notepad.ui.components.MoreButton
import com.farmerbb.notepad.ui.content.NoteListContent
import java.util.Date

@Composable
private fun NoteList(notes: List<NoteMetadata>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { AppBarText(stringResource(id = R.string.app_name)) },
                backgroundColor = colorResource(id = R.color.primary),
                actions = {
                    MoreButton()
                }
            )
        },
        content = {
            NoteListContent(notes)
        }
    )
}

@Preview
@Composable
fun NoteListPreview() = MaterialTheme {
    NoteList(
        notes = listOf(
            NoteMetadata(
                metadataId = -1,
                title = "Test Note 1",
                date = Date(),
                hasDraft = false
            ),
            NoteMetadata(
                metadataId = -1,
                title = "Test Note 2",
                date = Date(),
                hasDraft = false
            )
        )
    )
}

@Preview
@Composable
fun NoteListEmptyPreview() = MaterialTheme {
    NoteList(notes = emptyList())
}
