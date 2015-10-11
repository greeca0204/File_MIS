package doc.extremetable.view;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.AbstractHtmlView;
import org.extremecomponents.table.view.DefaultStatusBar;
import org.extremecomponents.table.view.DefaultToolbar;
import org.extremecomponents.util.HtmlBuilder;

public class DocHtmlView extends AbstractHtmlView {

	@Override
	protected void beforeBodyInternal(TableModel arg0) 
	{

        
        getTableBuilder().tableStart();

        getTableBuilder().theadStart();
        
        statusBar(getHtmlBuilder(), getTableModel());
        
        getTableBuilder().filterRow();

        getTableBuilder().headerRow();

        getTableBuilder().theadEnd();

        getTableBuilder().tbodyStart();

	}
	
	@Override
	protected void afterBodyInternal(TableModel arg0) {
        getCalcBuilder().defaultCalcLayout();

        getTableBuilder().tbodyEnd();

        getTableBuilder().tableEnd();
        
        toolbar(getHtmlBuilder(), getTableModel());

	}
	
    protected void toolbar(HtmlBuilder html, TableModel model) {
        new DocToolBar(html, model).layout();
    }

    protected void statusBar(HtmlBuilder html, TableModel model) {
        new DefaultStatusBar(html, model).layout();
    }


}
