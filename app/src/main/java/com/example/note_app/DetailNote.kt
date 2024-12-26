package com.example.note_app

import java.io.Serializable

data class DetailNote(var id: Int, var title: String, var detail: String) : Serializable

data class showNote(var show : String)