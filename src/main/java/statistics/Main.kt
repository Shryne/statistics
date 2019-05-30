package statistics

import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell

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

    codeView(shell, codeWidth)
    statistics(shell, statisticsWidth)

    shell.setSize(800, 1000)
    shell.open()

    while (!shell.isDisposed) {
        if (!display.readAndDispatch()) {
            display.sleep()
        }
    }

    display.dispose()
}
