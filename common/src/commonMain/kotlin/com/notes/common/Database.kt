package com.notes.common

import com.notes.database.NoteDatabase
import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): NoteDatabase {
    val driver = driverFactory.createDriver()
    NoteDatabase.Schema.create(driver)
    return NoteDatabase(driver)
}
