package statistics

import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell
import org.eclipse.swt.widgets.TreeItem

/**
 * @since 0.1
 */
fun main() {
    val display = Display()
    val shell = Shell(display)

    shell.text = "Statistics"

    val layout = GridLayout()
    layout.numColumns = 10
    layout.makeColumnsEqualWidth = true
    shell.layout = layout

    val codeWidth = 7
    val statisticsWidth = layout.numColumns - codeWidth

    codeLabel(shell, codeWidth)
    statisticsLabel(shell, statisticsWidth)

    val codeView = codeView(shell, codeWidth)
    val statistics = statistics(shell, statisticsWidth)

    codeView.addModifyListener {
        statistics.removeAll()
        val classItem = TreeItem(statistics, 0).apply {
            text = className(codeView.text)
        }
        TreeItem(classItem, 0).apply {
            text = "LoC = ${codeView.lineCount}"
        }
        classItem.expanded = true
    }

    shell.setSize(800, 1000)
    shell.open()

    while (!shell.isDisposed) {
        if (!display.readAndDispatch()) {
            display.sleep()
        }
    }

    display.dispose()
}
