package com.sedate.qrku.core.ui.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class NavigationState : Navigator {
	private val tabBackStacks =
		mutableMapOf<SeDestination, SnapshotStateList<Routes>>()

	private var _currentTab: SeDestination = SeDestination.WRITE
	val currentTab: SeDestination
		get() = _currentTab

	val navBackStack = mutableStateListOf<Routes>()

	init {
		initializeTabs()
		switchToTab(_currentTab)
	}

	private fun initializeTabs() {
		SeDestination.entries.forEach { destination ->
			val root = destination.route
			tabBackStacks[destination] = mutableStateListOf(root)
		}
	}

	override fun navigate(route: Routes) {
		tabBackStacks[_currentTab]?.add(route)
		syncCurrentStack()
	}

	override fun pop() {
		val stack = tabBackStacks[_currentTab] ?: return
		if (stack.size > 1) {
			stack.removeLast()
			syncCurrentStack()
		}
	}

	override fun replace(route: Routes) {
		val stack = tabBackStacks[_currentTab] ?: return
		if (stack.isNotEmpty()) stack.removeLast()
		stack.add(route)
		syncCurrentStack()
	}

	override fun resetTo(route: Routes) {
		val stack = tabBackStacks[_currentTab] ?: return
		stack.clear()
		stack.add(route)
		syncCurrentStack()
	}

	override fun navigateToTopLevelDestination(destination: SeDestination) {
		if (destination == _currentTab) return

		_currentTab = destination
		switchToTab(destination)
	}

	private fun switchToTab(destination: SeDestination) {
		val stack = tabBackStacks[destination] ?: return
		navBackStack.clear()
		navBackStack.addAll(stack)
	}

	private fun syncCurrentStack() {
		val stack = tabBackStacks[_currentTab] ?: return
		navBackStack.clear()
		navBackStack.addAll(stack)
	}
}