@file:Suppress("unused")

import java.text.Normalizer

private val diacriticsRegex = Regex("\\p{InCombiningDiacriticalMarks}+")

fun String.stripDiacriticals(): String {
  return Normalizer.normalize(this, Normalizer.Form.NFD)
      .replace(diacriticsRegex, "")
}
