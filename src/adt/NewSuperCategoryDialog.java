package adt;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class NewSuperCategoryDialog extends TitleAreaDialog {

    private Text configurationFile;
    private Text commandArguments;
   
    /**
     * Nasty trick to transmit command line arguments and configuration file from one menu to the other
     * */
    public static String confFile = "conf.ini";
    public static String cmdArgs = "";
    
    public NewSuperCategoryDialog(Shell parentShell) {
        super(parentShell);
    }

    @Override
    public void create() {
        super.create();
        setTitle("DICE Anomaly Detection Configuration");
        setMessage("This form allows guided configuration for the Anomaly Detection Tool", IMessageProvider.INFORMATION);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        GridLayout layout = new GridLayout(2, false);
        container.setLayout(layout);

        configurationFileLocation(container);
        commandLineArguments(container);
        
  
        return area;
    }

    private void configurationFileLocation(Composite container) {
        Label lbtFirstName = new Label(container, SWT.NONE);
        lbtFirstName.setText("Configuration File Path");

        GridData dataText = new GridData();
        dataText.grabExcessHorizontalSpace = true;
        dataText.horizontalAlignment = GridData.FILL;

        configurationFile = new Text(container, SWT.BORDER);
        configurationFile.setLayoutData(dataText);
    }

    private void commandLineArguments(Composite container) {
        Label lbtLastName = new Label(container, SWT.NONE);
        lbtLastName.setText("Command line options");

        GridData dataText = new GridData();
        dataText.grabExcessHorizontalSpace = true;
        dataText.horizontalAlignment = GridData.FILL;
        
        commandArguments = new Text(container, SWT.BORDER);
        commandArguments.setLayoutData(dataText);
    }

 

    @Override
    protected boolean isResizable() {
        return true;
    }

    // save content of the Text fields because they get disposed
    // as soon as the Dialog closes
    private void saveInput() {
        confFile = configurationFile.getText();
        cmdArgs = commandArguments.getText();

    }

    @Override
    protected void okPressed() {
        saveInput();
        super.okPressed();
    }

    public String getConfigurationFile() {
        return confFile;
    }

    public String getCommandLineArguments() {
        return cmdArgs;
    }
}
