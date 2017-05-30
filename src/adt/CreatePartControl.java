package adt;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.ui.part.ViewPart;

public class CreatePartControl extends ViewPart {
   Label label;
   public CreatePartControl() {
   }
   public void createPartControl(Composite parent) {
      label = new Label(parent, SWT.WRAP);
      label.setText("Hello World");
   }
   public void setFocus() {
      // set focus to my widget.  For a label, this doesn't
      // make much sense, but for more complex sets of widgets
      // you would decide which one gets the focus.
   }
}
/*
 * class createPartControl extends ViewPart {
 
	Labellabel;
	
	public void createPartControl(Composite parent) {
    viewer = new ListViewer(parent);
    viewer.setContentProvider(new ContentProvider());
    viewer.setLabelProvider(new LabelProvider());
    viewer.addDoubleClickListener(new IDoubleClickListener() {
       public void doubleClick(DoubleClickEvent event) {
          editSelection();
       }
    });
    // A service will be providing our input
    IPersonService service = (IPersonService) getSite().getService(
       IPersonService.class);
    viewerInput = new ArrayList(service.getPeople());
    viewer.setInput(viewerInput);
    // register myself as a selection provider for this view
    getSite().setSelectionProvider(viewer);
 }
*/


