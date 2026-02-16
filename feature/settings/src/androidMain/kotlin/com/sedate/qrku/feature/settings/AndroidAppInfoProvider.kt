package com.sedate.qrku.feature.settings

import android.content.Context
import com.sedate.qrku.core.common.constants.SeConst.EMPTY
import com.sedate.qrku.core.common.constants.SeConst.ZERO
import com.sedate.qrku.feature.settings.contract.AppInfoProvider

class AndroidAppInfoProvider(
	private val context: Context
) : AppInfoProvider {

	override fun getAppVersion(): String {
		val info = context.packageManager
			.getPackageInfo(context.packageName, Int.ZERO)
		return info.versionName ?: String.EMPTY
	}
}