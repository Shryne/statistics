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

/**
 * @since 0.3
 */
fun className(text: String): String {
    var inComment = false
    for (i in 0..text.length - 6) {
        if (inComment) {
            if (text.substring(i..(i + 1)) == "*/") {
                inComment = false
            } else {
                continue
            }
        } else {
            if (text.substring(i..(i + 1)) == "/*") {
                inComment = true
            } else if (text.substring(i..(i + 4)) == "class") {
                if (text[i + 4] == ' ') {
                    return "INVALID CLASS"
                }
                var name = ""
                for (j in (i + 6) until text.length) {
                    if (text[j] == ' ') {
                        return name
                    } else {
                        name += text[j]
                    }
                }
                return name
            }
        }
    }
    return "NO CLASS"
}

/**
 * @since 0.5
 */
fun privateMethods(text: String): String {
    var number = 0
    for (i in 0..text.length - 9) {
        if (text.substring(i..(i + 6)) == "private") {
            if (text[i + 6] == ' ') {
                continue
            }
            for (j in (i + 8)..text.length - 1) {
                if (text[j] == '(') {
                    ++number
                } else if ((text[j] == ' ') or (text[j] == '=')) {
                    break
                }
            }
        }
    }
    return "private methods = $number"
}


/**
 * @since 0.5
 */
fun publicMethods(text: String): String {
    var number = 0
    for (i in 0..text.length - 8) {
        if (text.substring(i..(i + 5)) == "public") {
            if (text[i + 5] == ' ') {
                continue
            }
            for (j in (i + 7)..text.length - 1) {
                if (text[j] == '(') {
                    ++number
                } else if ((text[j] == ' ') or (text[j] == '=')) {
                    break
                }
            }
        }
    }
    return "public methods = $number"
}
