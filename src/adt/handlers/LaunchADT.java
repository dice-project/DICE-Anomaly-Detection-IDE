package adt.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class LaunchADT extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
	
		MessageDialog.openInformation(
				window.getShell(),
				"Adt",
				"Launched -- Dice Anomaly Detection");
		
		try {
		
			String line;
			Process p = Runtime.getRuntime().exec("python /Users/ioan/Documents/workspace/adt/src/adt/script.py");
			p.waitFor();
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while((line=input.readLine())!=null){
				System.out.println(line);
			}
			BufferedReader iput = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while((line=iput.readLine())!=null){
				System.out.println(line);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
