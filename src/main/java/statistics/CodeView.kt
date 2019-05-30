package statistics

import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Text

/**
 * This method assumes that the chosen layout is a [org.eclipse.swt.layout.GridLayout] with 10 columns.
 * @since 0.2
 */
fun codeView(composite: Composite, columnWidth: Int) {
    Text(composite, SWT.H_SCROLL or SWT.V_SCROLL).apply {
        layoutData = GridData(GridData.FILL_BOTH).apply {
            horizontalSpan = columnWidth
        }
    }
}

fun codeLabel(composite: Composite, columnWidth: Int) {
    Label(composite, SWT.NONE).apply {
        text = "Code:"
        layoutData = GridData().apply { horizontalSpan = columnWidth }
    }
}
