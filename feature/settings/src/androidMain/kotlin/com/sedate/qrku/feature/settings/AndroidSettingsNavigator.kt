package com.sedate.qrku.feature.settings

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import com.sedate.qrku.feature.settings.contract.SettingsNavigator

class AndroidSettingsNavigator(
	private val context: Context
) : SettingsNavigator {

	override fun openPrivacyPolicy() {
		context.startActivity(
			Intent(
				Intent.ACTION_VIEW,
				"https://your-privacy-url.com".toUri()
			).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
		)
	}

	override fun contactDeveloper() {
		context.startActivity(
			Intent(
				Intent.ACTION_SENDTO,
				"mailto:developer@email.com".toUri()
			).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
		)
	}

	override fun rateApp() {
		context.startActivity(
			Intent(
				Intent.ACTION_VIEW,
				"market://details?id=${context.packageName}".toUri()
			).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
		)
	}
}