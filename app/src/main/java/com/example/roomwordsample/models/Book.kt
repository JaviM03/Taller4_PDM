package com.example.roomwordsample.models


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book (
    @PrimaryKey
    val imdbID: String = "N/a",
    val Title:String = "N/A",
    val Year:String = "N/A",
    val Released: String = "N/A",
    val Runtime:String = "N/A",
    val Genre:String = "N/A",
    val Director:String = "N/A",
    val Actors:String = "N/A",
    val Plot:String = "N/A",
    val Language:String = "N/A",
    val imdbRating:String = "N/A",
    val Poster:String = "N/A"
)

data class BookPreview(
    val Title: String = "N/A",
    val Year: String = "N/A",
    val imdbID: String = "N/A",
    val Type: String = "N/A",
    val Poster: String = "N/A",
    var selected: Boolean = false
)

data class OmbdBookResponse (
    val Search: List<BookPreview>,
    val totalResults: String,
    val Response: String
)