package com.farmerbb.notepad.di

import android.content.Context
import android.content.pm.PackageManager
import androidx.datastore.preferences.preferencesDataStore
import com.farmerbb.notepad.Database
import com.farmerbb.notepad.data.NotepadRepository
import com.farmerbb.notepad.model.NoteMetadata
import com.farmerbb.notepad.usecase.artVandelayModule
import com.farmerbb.notepad.usecase.dataMigratorModule
import com.farmerbb.notepad.usecase.keyboardShortcutsModule
import com.farmerbb.notepad.usecase.systemThemeModule
import com.farmerbb.notepad.usecase.toasterModule
import com.farmerbb.notepad.viewmodel.viewModelModule
import com.github.k1rakishou.fsaf.FileChooser
import com.github.k1rakishou.fsaf.FileManager
import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.android.AndroidSqliteDriver
import de.schnettler.datastore.manager.DataStoreManager
import java.util.Date
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val notepadModule = module {
    includes(
        viewModelModule,
        dataMigratorModule,
        toasterModule,
        artVandelayModule,
        keyboardShortcutsModule,
        systemThemeModule
    )

    single { provideDatabase(context = androidContext()) }
    single { NotepadRepository(database = get()) }
    single { DataStoreManager(dataStore = androidContext().dataStore) }
    single { FileManager(appContext = androidContext()) }
    single { FileChooser(appContext = androidContext()) }
}

val Context.dataStore by preferencesDataStore("settings")

@Suppress("Deprecation")
private val Context.isPlayStoreInstalled get() = try {
    packageManager.getPackageInfo("com.android.vending", 0)
    true
} catch(e: PackageManager.NameNotFoundException) {
    false
}

private fun provideDatabase(context: Context) = Database(
    driver = AndroidSqliteDriver(Database.Schema, context, "notepad.db"),
    NoteMetadataAdapter = NoteMetadata.Adapter(dateAdapter = DateAdapter)
)

object DateAdapter: ColumnAdapter<Date, Long> {
    override fun decode(databaseValue: Long) = Date(databaseValue)
    override fun encode(value: Date) = value.time
}