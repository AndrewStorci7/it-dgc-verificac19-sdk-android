/*
 *  ---license-start
 *  eu-digital-green-certificates / dgca-verifier-app-android
 *  ---
 *  Copyright (C) 2021 T-Systems International GmbH and all other contributors
 *  ---
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  ---license-end
 *
 *  Created by nicolamcornelio on 16/09/2021, 16:03
 */

package it.ministerodellasalute.verificaC19sdk.util

import java.text.SimpleDateFormat
import java.util.*

const val YEAR_MONTH_DAY = "yyyy-MM-dd"
const val YEAR_MONTH = "yyyy-MM"
const val YEAR = "yyyy"
const val DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

const val FORMATTED_YEAR_MONTH_DAY = "MMM d, yyyy"
const val FORMATTED_DATE_TIME = "MMM d, yyyy, HH:mm"
const val FORMATTED_BIRTHDAY_DATE = "dd/MM/yyyy"
const val FORMATTED_BIRTHDAY_YEAR_MONTH = "MM/yyyy"
const val FORMATTED_BIRTHDAY_YEAR = "yyyy"
const val FORMATTED_DATE_LAST_SYNC = "dd/MM/yyyy, HH:mm"
const val FORMATTED_VALIDATION_DATE = "HH:mm, dd/MM/yyyy"

/**
 *
 * This object contains useful utilities to deal with datetime values.
 *
 */
object TimeUtility {

    fun String.parseFromTo(from: String, to: String): String {
        return try {
            val parser = SimpleDateFormat(from, Locale.getDefault())
            val formatter = SimpleDateFormat(to, Locale.getDefault())
            return formatter.format(parser.parse(this)!!)
        } catch (ex: Exception) {
            ""
        }
    }

    fun String.formatDateOfBirth(): String {
        var formattedDate = this.parseFromTo(YEAR_MONTH_DAY, FORMATTED_BIRTHDAY_DATE)
        if (formattedDate.isEmpty()) {
            formattedDate = this.parseFromTo(YEAR_MONTH, FORMATTED_BIRTHDAY_YEAR_MONTH)
            if (formattedDate.isEmpty()) {
                formattedDate = this.parseFromTo(YEAR, FORMATTED_BIRTHDAY_YEAR)
            }
        }
        return formattedDate
    }

    /**
     *
     * This method converts a [Long] value, representing a date, to a [String].
     *
     */
    fun Long.parseTo(to: String): String {
        return SimpleDateFormat(to, Locale.getDefault()).format(Date(this))
    }

    /**
     *
     * This method converts a [Date] value, representing a date, to a [String].
     *
     */
    fun Date.parseTo(to: String): String {
        return SimpleDateFormat(to, Locale.getDefault()).format(this)
    }
}