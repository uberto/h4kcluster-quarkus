package com.ubertob.h4kcluster.domain

class UiHub(
      val wordCounter: (String) -> Int,
      val sumNumbers: (Int, Int) -> Int
) {

  fun countWords(text: String): Int {
    val lines = text.split("\n")

    return lines.map(wordCounter).reduce(sumNumbers)
  }

}