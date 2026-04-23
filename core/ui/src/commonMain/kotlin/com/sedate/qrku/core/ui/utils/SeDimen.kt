package com.sedate.qrku.core.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object SeDimen {
	val Dp0 = 0.dp
	val Dp1 = 1.dp
	val Dp2 = 2.dp
	val Dp3 = 3.dp
	val Dp4 = 4.dp
	val Dp5 = 5.dp
	val Dp6 = 6.dp
	val Dp7 = 7.dp
	val Dp8 = 8.dp
	val Dp9 = 9.dp
	val Dp10 = 10.dp
	val Dp11 = 11.dp
	val Dp12 = 12.dp
	val Dp13 = 13.dp
	val Dp14 = 14.dp
	val Dp15 = 15.dp
	val Dp16 = 16.dp
	val Dp17 = 17.dp
	val Dp18 = 18.dp
	val Dp19 = 19.dp
	val Dp20 = 20.dp
	val Dp21 = 21.dp
	val Dp22 = 22.dp
	val Dp23 = 23.dp
	val Dp24 = 24.dp
	val Dp25 = 25.dp
	val Dp26 = 26.dp
	val Dp27 = 27.dp
	val Dp28 = 28.dp
	val Dp29 = 29.dp
	val Dp30 = 30.dp
	val Dp31 = 31.dp
	val Dp32 = 32.dp
	val Dp33 = 33.dp
	val Dp34 = 34.dp
	val Dp35 = 35.dp
	val Dp36 = 36.dp
	val Dp37 = 37.dp
	val Dp38 = 38.dp
	val Dp39 = 39.dp
	val Dp40 = 40.dp
	val Dp41 = 41.dp
	val Dp42 = 42.dp
	val Dp43 = 43.dp
	val Dp44 = 44.dp
	val Dp45 = 45.dp
	val Dp46 = 46.dp
	val Dp47 = 47.dp
	val Dp48 = 48.dp
	val Dp49 = 49.dp
	val Dp50 = 50.dp
	val Dp51 = 51.dp
	val Dp52 = 52.dp
	val Dp56 = 56.dp
	val Dp70 = 70.dp
	val Dp75 = 75.dp
	val Dp80 = 80.dp
	val Dp86 = 86.dp
	val Dp95 = 95.dp
	val Dp140 = 140.dp
	val Dp160 = 160.dp
	val Dp180 = 180.dp
	val Dp240 = 240.dp
	val Dp320 = 320.dp
}

@Composable
fun Int.toDpValue(): Dp {
	return with(LocalDensity.current) { this@toDpValue.toDp() }
}