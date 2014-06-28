/**
 * PieDemo.java
 * Created by pgirard at 3:18:15 PM on Oct 19, 2010
 * in the com.qagwaai.starmalaccamax.client.view.widget package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp.widget;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.PieChart;

/**
 * @author pgirard
 * 
 */
public final class PieChartWidget extends VerticalPanel {

    /**
	 * 
	 */
    private PieChart viz;
    /**
	 * 
	 */
    private DataTable data;
    /**
	 * 
	 */
    private PieChart.Options options;

    /**
     * 
     * @param title
     *            the title of the chart
     */
    public PieChartWidget(final String title) {
        /* create a datatable */
        data = DataTable.create();
        data.addColumn(ColumnType.STRING, "Resource");
        data.addColumn(ColumnType.NUMBER, "Count");
        data.addRows(1);
        data.setValue(0, 0, "Loading");
        data.setValue(0, 1, 100);

        /* create pie chart */

        options = PieChart.Options.create();
        options.setWidth(400);
        options.setHeight(240);
        options.set3D(true);
        options.setTitle(title);
        viz = new PieChart(data, options);
        this.add(viz);
    }

    /**
     * 
     * @return the data for this widget
     */
    public DataTable getData() {
        return data;
    }

    /**
     * force a redraw of the widget
     */
    public void redraw() {
        // viz.removeFromParent();
        // viz = new PieChart(data, options);
        // this.add(viz);
        viz.draw(data, options);
    }

}
