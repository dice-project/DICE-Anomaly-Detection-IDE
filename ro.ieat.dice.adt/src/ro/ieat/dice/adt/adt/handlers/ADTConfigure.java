package ro.ieat.dice.adt.adt.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import ro.ieat.dice.adt.adt.NewSuperCategoryDialog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ADTConfigure extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

		NewSuperCategoryDialog dialog = new NewSuperCategoryDialog(window.getShell());
		dialog.create();

		if (dialog.open() == Window.OK) {
			System.err.println(dialog.getConfigurationFile());
			System.err.println(dialog.getCommandLineArguments());
		}

		return null;
	}
}
