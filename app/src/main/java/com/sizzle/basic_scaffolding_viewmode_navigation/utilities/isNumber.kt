package com.sizzle.basic_scaffolding_viewmode_navigation.utilities

//---------------------------------------------------------------------
// IS STRING A NUMBER?
//
// FROM: https://www.techiedelight.com/determine-string-is-number-kotlin/
// USAGE:
//      val s = "100"
//      if (isNumber(s)) print("Number") else print("Not a Number")
//---------------------------------------------------------------------

fun isNumber(s: String): Boolean {
    return try {
        s.toInt()
        true
    } catch (ex: NumberFormatException) {
        false
    }
}