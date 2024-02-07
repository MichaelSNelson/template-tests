package qupath.ext.simplepreferences;

import javafx.scene.control.MenuItem
import org.slf4j.Logger
import org.slf4j.LoggerFactory;
import qupath.lib.common.Version;
import qupath.lib.gui.QuPathGUI;
import qupath.lib.gui.extensions.QuPathExtension
import qupath.fx.dialogs.FileChoosers
import qupath.fx.dialogs.Dialogs

/**
 * This is a demo to provide a simplepreferences for creating a new QuPath extension in Groovy.
 * <p>
 * <b>Important!</b> For your extension to work in QuPath, you need to make sure the name & package
 * of this class is consistent with the file
 * <pre>
 *     /resources/META-INF/services/qupath.lib.gui.extensions.QuPathExtension
 * </pre>
 */
class SimpleGUITest implements QuPathExtension {
	static final Logger logger = LoggerFactory.getLogger(SimpleGUITest.class);
	// Setting the variables here is enough for them to be available in the extension
	String name = "Groovy GUI tests"
	String description = "This is just a demo to show how Groovy GUIs work"
	Version QuPathVersion = Version.parse("v0.5.0")

	@Override
	void installExtension(QuPathGUI qupath) {
		addMenuItem(qupath)
	}

	private void addMenuItem(QuPathGUI qupath) {
		def menu = qupath.getMenu("Extensions>${name}", true)


		def menuItem = new MenuItem("Groovy simple Dialog")
		menuItem.setOnAction(e -> {
			Dialogs.showMessageDialog("The dialog title goes here",
					"This is a simple text Dialog")
		})

		def menuItem2 = new MenuItem("Groovy single File Chooser")
		menuItem2.setOnAction(e -> {
			def csvFile = FileChoosers.promptForFile(
					"Choose input file", FileChoosers.createExtensionFilter("Text File", "*.csv", "*.txt"))

			Dialogs.showMessageDialog("Your selected file",
					"This is a simple Dialog showing that you selected $csvFile")
		})


		menu.getItems() << menuItem
		menu.getItems() << menuItem2
	}
	
}
