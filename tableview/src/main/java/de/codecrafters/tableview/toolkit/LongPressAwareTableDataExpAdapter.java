package de.codecrafters.tableview.toolkit;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.codecrafters.tableview.ExpandTableView;
import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.listeners.TableDataLongClickListener;

/**
 * A {@link TableDataAdapter} that supports different row rendering by long clicking them.
 * If the user does a long click on a row, the method {@link LongPressAwareTableDataExpAdapter#getLongPressCellView(int, int, ViewGroup)}
 * is called, else the {@link LongPressAwareTableDataExpAdapter#getDefaultCellView(int, int, ViewGroup)} is called.
 *
 * @author ISchwarz
 */
public abstract class LongPressAwareTableDataExpAdapter<T> extends TableDataAdapter<T> {

    private int expandedRow = -1;

    /**
     * Creates a new {@link LongPressAwareTableDataExpAdapter} with the given paramters.
     *
     * @param context   The context that shall be used.
     * @param data      The data that shall be displayed.
     * @param tableView The table to listen for long presses by the user.
     */
    public LongPressAwareTableDataExpAdapter(final Context context, final List<T> data, final ExpandTableView<T> tableView) {
        super(context, data);
        tableView.addDataClickListener(new InternalDataClickListener());
        tableView.addDataLongClickListener(new InternalDataLongClickListener());
    }

    @Override
    public final View getCellView(final int rowIndex, final int columnIndex, final ViewGroup parentView) {
        final View view;
        if (rowIndex == expandedRow) {
            view = getLongPressCellView(rowIndex, columnIndex, parentView);
        } else {
            view = getDefaultCellView(rowIndex, columnIndex, parentView);
        }
        return view;
    }

    /**
     * The cell view that is displayed to the user when the row is collapsed.
     *
     * @param rowIndex    The index of the row where this cell is displayed.
     * @param columnIndex The index of the column where this cell is displayed.
     * @param parentView  The parentView (the row) of this cell.
     * @return The "default-version" of this cell view.
     */
    public abstract View getDefaultCellView(final int rowIndex, final int columnIndex, final ViewGroup parentView);

    /**
     * The cell view that is displayed to the user when the use long pressed the row containing this cell.
     *
     * @param rowIndex    The index of the row where this cell is displayed.
     * @param columnIndex The index of the column where this cell is displayed.
     * @param parentView  The parentView (the row) of this cell.
     * @return The "long-press-version" of this cell view.
     */
    public abstract View getLongPressCellView(final int rowIndex, final int columnIndex, final ViewGroup parentView);

    private class InternalDataLongClickListener implements TableDataLongClickListener<T> {

        @Override
        public boolean onDataLongClicked(final int rowIndex, final T clickedData) {
            expandedRow = rowIndex;
            notifyDataSetChanged();
            return true;
        }
    }

    private class InternalDataClickListener implements TableDataClickListener<T> {

        @Override
        public void onDataClicked(final int rowIndex, final T clickedData) {
            expandedRow = -1;
            notifyDataSetChanged();
        }
    }
}
