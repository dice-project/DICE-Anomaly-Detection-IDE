package ro.ieat.dice.adt.adt.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;

import ro.ieat.dice.adt.adt.Activator;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class LaunchADT extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Anomaly Detection",
				"You just launched the Dice Anomaly Detection Tool using the configured parameters");

		try {
			String arguments = "-f " + ro.ieat.dice.adt.adt.NewSuperCategoryDialog.confFile + " "
					+ ro.ieat.dice.adt.adt.NewSuperCategoryDialog.cmdArgs;
			/*
			 * Change path for the anomaly detection script so that it can be
			 * used in the DICE IDE
			 */
			Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
			URL fileUrl = bundle.getEntry("script.py");
			URI fileUri = FileLocator.resolve(fileUrl).toURI();
			File file = new File(fileUri);

			Process p = Runtime.getRuntime().exec("python " + file.toString() + " " + arguments);
			p.waitFor();

			BufferedReader reader;
			String line;

			StringBuilder inputSb = new StringBuilder();
			reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = reader.readLine()) != null) {
				inputSb.append(line);
			}
			reader.close();

			StringBuilder errorSb = new StringBuilder();
			reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((line = reader.readLine()) != null) {
				errorSb.append(line);
			}
			reader.close();

			MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Anomaly Detection",
					"Result:\n" + inputSb.toString() + "\nErrors:\n" + errorSb.toString());
		} catch (IOException | InterruptedException | URISyntaxException e) {
			MessageDialog.openError(Display.getCurrent().getActiveShell(), "Anomaly Detection",
					"An error occured while executing the Dice Anomaly Detection Tool: " + e.getMessage());
		}
		return null;
	}
}
