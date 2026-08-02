package com.muslimqi.design

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform

/** Compatibility helpers kept separate so the main design file remains readable. */
internal fun DrawScope.rotate(
    degrees: Float,
    pivot: Offset,
    block: DrawScope.() -> Unit
) {
    withTransform({ rotate(degrees, pivot) }) {
        block()
    }
}

@Composable
internal fun NavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit
) {
    androidx.compose.material3.NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = icon,
        label = label
    )
}
