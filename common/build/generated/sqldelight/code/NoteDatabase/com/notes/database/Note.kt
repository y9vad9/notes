package com.notes.database

data class Note(
  val id: Long,
  val name: String,
  val text: String
) {
  override fun toString(): String = """
  |Note [
  |  id: $id
  |  name: $name
  |  text: $text
  |]
  """.trimMargin()
}
