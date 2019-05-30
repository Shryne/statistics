package statistics

import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*
import java.io.File

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

    val first = Label(shell, SWT.NONE)
    first.layoutData = GridData().apply { horizontalSpan = 7 }
    first.text = "Code:"

    val second = Label(shell, SWT.NONE)
    second.layoutData = GridData().apply { horizontalSpan = 3 }
    second.text = "Statistics:"


    val text = Text(shell, SWT.H_SCROLL or SWT.V_SCROLL)
    text.addModifyListener {
        println(text.lineCount)
    }
    text.layoutData = GridData(GridData.FILL_VERTICAL or GridData.FILL_HORIZONTAL).apply {
        horizontalSpan = 7
    }

    text.text = ""



    val tree = Tree(shell, SWT.NONE)
    val roots = File.listRoots()
    for (i in roots.indices) {
        val root = TreeItem(tree, 0)
        root.text = roots[i].toString()
        root.data = roots[i]
        TreeItem(root, 0)
    }
    tree.addListener(SWT.Expand) {
        val root = it.item as TreeItem
        val items = root.items
        for (i1 in items.indices) {
            if (items[i1].data != null) return@addListener
            items[i1].dispose()
        }
        val file = root.data as File
        val files = file.listFiles() ?: return@addListener
        for (i2 in files.indices) {
            val item = TreeItem(root, 0)
            item.text = files[i2].name
            item.data = files[i2]
            if (files[i2].isDirectory) {
                TreeItem(item, 0)
            }
        }
    }
    tree.layoutData = GridData(GridData.FILL_VERTICAL or GridData.FILL_HORIZONTAL).apply {
        horizontalSpan = 3
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
