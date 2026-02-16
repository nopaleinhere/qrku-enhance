package com.sedate.qrku.core.common.constants

import kotlin.Int.Companion

object SeConst {
	/* ==============================
	String Constants
	================================= */
	val String.Companion.EMPTY get() = ""
	val String.Companion.DOT get() = "."
	val String.Companion.COMMA get() = ","

	/* ==============================
	Integer Constants
	================================= */
	val Companion.ZERO get() = 0
	val Companion.ONE get() = 1
	val Companion.TWO get() = 2
	val Companion.THREE get() = 3
	val Companion.TEN get() = 10
	val Companion.ELEVEN get() = 11
	val Companion.TWELVE get() = 12
	val Companion.ONE_HUNDRED get() = 100
	val Companion.TWO_HUNDRED get() = 200
	val Companion.FIVE_HUNDRED_TWELVE get() = 512
	val Companion.ONE_THOUSAND get() = 1000
	val Companion.ONE_THOUSAND_EIGHTY get() = 1080

	/* ==============================
	Double Constants
	================================= */
	val Double.Companion.ZERO get() = 0.0
	val Double.Companion.ONE get() = 1.0

	/* ==============================
	Float Constants
	================================= */
	val Float.Companion.ZERO get() = 0f
	val Float.Companion.POINT_TWELVE get() = 12f
	val Float.Companion.ZER0_POINT_SIX get() = 0.6f
	val Float.Companion.ONE get() = 1f
	val Float.Companion.TWO get() = 2f
	val Float.Companion.THREE get() = 3f

	/* ==============================
	Char Constants
	================================= */
	val Char.Companion.EMPTY get() = ' '
	val Char.Companion.DOT get() = '.'
	val Char.Companion.COMMA get() = ','
	val Char.Companion.ZERO get() = '0'
	val Char.Companion.ONE get() = '1'

	/* ==============================
	Long Constants
	================================= */
	val Long.Companion.ZERO get() = 0L
	val Long.Companion.FIVE_THOUSAND get() = 5000L

	/* ==============================
	Other Constants
	================================= */
	const val HTTP = "http://"
	const val HTTPS = "https://"

	const val UTF_8 = "UTF-8"
}