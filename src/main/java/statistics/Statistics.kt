package statistics

import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Tree
import org.eclipse.swt.widgets.TreeItem
import java.io.File

/**
 * This method assumes that the chosen layout is a [org.eclipse.swt.layout.GridLayout] with 10 columns.
 * @since 0.2
 */
fun statistics(composite: Composite, columnWidth: Int) {
    Tree(composite, SWT.NONE).apply {
        val roots = File.listRoots()
        for (i in roots.indices) {
            val root = TreeItem(this, 0)
            root.text = roots[i].toString()
            root.data = roots[i]
            TreeItem(root, 0)
        }
        addListener(SWT.Expand) {
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
        layoutData = GridData(GridData.FILL_BOTH).apply {
            horizontalSpan = columnWidth
        }
    }
}

fun statisticsLabel(composite: Composite, columnWidth: Int) {
    Label(composite, SWT.NONE).apply {
        text = "Statistics:"
        layoutData = GridData().apply { horizontalSpan = columnWidth }
    }
}
