package statistics

import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Tree

/**
 * This method assumes that the chosen layout is a [org.eclipse.swt.layout.GridLayout] with 10 columns.
 * @since 0.2
 */
fun statistics(composite: Composite, columnWidth: Int) =
    Tree(composite, SWT.NONE).apply {
        layoutData = GridData(GridData.FILL_BOTH).apply {
            horizontalSpan = columnWidth
        }
    }

fun statisticsLabel(composite: Composite, columnWidth: Int) {
    Label(composite, SWT.NONE).apply {
        text = "Statistics:"
        layoutData = GridData().apply { horizontalSpan = columnWidth }
    }
}

// TODO: If there is comment for the class and this comment has the word class in it, this method will take the
//  following word as the class name.
/**
 * @since 0.3
 */
fun className(text: String): String {
    for (i in 0..text.length - 6) {
        if (text.substring(i..(i + 4)) == "class") {
            if (text[i + 4] == ' ') {
                return "INVALID CLASS"
            }
            var name = ""
            for (j in (i + 6)..text.length - 1) {
                if (text[j] == ' ') {
                    return name
                } else {
                    name += text[j]
                }
            }
            return name
        }
    }
    return "NO CLASS"
}
