package qupath.ext.simplepreferences

import javafx.beans.property.BooleanProperty
import javafx.beans.property.IntegerProperty
import javafx.beans.property.StringProperty;
import javafx.scene.control.MenuItem
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import qupath.ext.simplepreferences.packages.PreferencesTestOptions
import qupath.fx.prefs.annotations.BooleanPref
import qupath.fx.prefs.annotations.DirectoryPref
import qupath.fx.prefs.annotations.IntegerPref
import qupath.fx.prefs.annotations.PrefCategory
import qupath.fx.prefs.annotations.StringPref
import qupath.lib.common.GeneralTools;
import qupath.fx.prefs.controlsfx.PropertySheetUtils;
import qupath.lib.common.Version;
import qupath.lib.gui.QuPathGUI
import qupath.lib.gui.actions.ActionTools;
import qupath.lib.gui.actions.annotations.ActionIcon;
import qupath.lib.gui.actions.annotations.ActionConfig
import qupath.lib.gui.actions.annotations.ActionMenu;
import qupath.lib.gui.extensions.QuPathExtension
import qupath.lib.gui.prefs.PathPrefs
import qupath.fx.dialogs.FileChoosers
import qupath.fx.dialogs.Dialogs
import qupath.fx.prefs.PrefUtils
import qupath.lib.gui.localization.QuPathResources
import javax.swing.Action
import qupath.fx.utils.FXUtils
/**
 * This is a demo to provide a simple preferences for creating a new QuPath extension that adds fields to preferences.
 */
class SimplePreferencesTest implements QuPathExtension {
	static final Logger logger = LoggerFactory.getLogger(SimplePreferencesTest.class);
	// Setting the variables here is enough for them to be available in the extension
	String name = "Groovy Preferences test"
	String description = "This is a demo to show how Groovy Preferences work"
	Version QuPathVersion = Version.parse("v0.5.0")
	@Override
//	public String getName() {
//		return QuPathResources.getString("Extension.PreferencesTest");
//	}
	@Override
	public void installExtension(QuPathGUI qupath) {

		logger.info("Starting Preferences test in version {}", QuPathVersion);

		var prefs = new TestPreferences();
		qupath.getPreferencePane()
				.getPropertySheet()
				.getItems()
				.addAll(PropertySheetUtils.parseAnnotatedItemsWithResources(QuPathResources.getLocalizedResourceManager(), prefs));
		addMenuItem(qupath)
	}

//	@Override
//	public String getDescription() {
//		return QuPathResources.getString("Extension.Preferences.test"); //TODO ????
//	}
//
//	/**
//	 * Returns the version stored within this jar, because it is matched to the QuPath version.
//	 */
//	@Override
//	public Version getQuPathVersion() {
//		return getVersion();
//	}
//
//	// Test
//	private static StringProperty testPreference = null;
//	static {
//		testPreference = PathPrefs.createPersistentPreference("Test preference", "A string goes here");
//	}
//
//
//	/**
//	 * Set a String value to the preference.
//	 * @param path
//	 */
//	public static void setPreferencesString(final String path) {
//		testPreference.set(path);
//	}
//
//	/**
//	 * Return a string value from a preference.
//	 * @return
//	 */
//	public static String getPreferencesString() {
//		return testPreference.get();
//	}
//
//	/**
//	 * Property representing the path to a local ImageJ installation, or null if no path has been set.
//	 * @return
//	 */
//	public static StringProperty preferencesString() {
//		return testPreference;
//	}
//	@ActionMenu(value = ["Menu.Extensions", "Preferences test>"])
//	@ActionConfig("Action.ImageJ.setPluginsDirectory")
//	public final Action actionPlugins = ActionTools.createAction(() -> promptToSetPluginsDirectory());

//	static void promptToSetPluginsDirectory() {
//		String path = getPreferencesString();
//		File dir = FileChoosers.promptForDirectory("Set ImageJ plugins directory", path == null ? null : new File(path));
//		if (dir != null && dir.isDirectory())
//			setPreferencesString(dir.getAbsolutePath());
//	}


	private String addMenuItem(QuPathGUI qupath) {
		def preferencesMenu = qupath.getMenu("Extensions>${name}", true)
		def prefMenu1 = new MenuItem("Output current values for SimplePreferences extension.")
//		def prefMenu2 = new MenuItem("Create a dialog based on a Preference")
		String inputPreference
		prefMenu1.setOnAction(e -> {
			inputPreference = Dialogs.showInputDialog("Test input to change Preference", "Change your prefrence here", "")
		})
		preferencesMenu.getItems() << prefMenu1

//		prefMenu2.setOnAction(e -> {
//			//TODO if statement based on a simple boolean preference
//			inputPreference = Dialogs.showInputDialog("Test input to change Preference", "Change your prefrence here", "")
//		})
//		preferencesMenu.getItems() << prefMenu2

		return inputPreference
	}


	//@PrefCategory("Prefs.TestPreferences")
	public static class TestPreferences {

		private PreferencesTestOptions options = PreferencesTestOptions.getInstance();

		//What is this
		//@BooleanPref("Prefs.TestPreferences.testPreference")
		public final BooleanProperty testThreadsPreference = PathPrefs.createPersistentPreference(
				"tp_testThreadsPreference", options.testThreadsPreferencesEnabled());

//		@BooleanPref("Prefs.TestPreferences.dialog")
//		public final BooleanProperty createDialogPreference = PathPrefs.createPersistentPreference(
//				"tp_testDialogPreference", options.createComplexDialog());
//
//		@IntegerPref("Prefs.TestPreferences.threads")
//		public final IntegerProperty threadsCountPreference = PathPrefs.createPersistentPreference(
//				"tp_threadCount", options.getMaxThreads());
//
//		@StringPref("Prefs.TestPreferences.testString")
//		public final StringProperty testStringPreference = PathPrefs.createPersistentPreference(
//				"tp_testString", String.join(" ", options.getStringPreference()));


		private TestPreferences() {

			// Set options using any values previously stored
//			options.setStringPreference(testStringPreference.get());
//			options.setPathMemoization(pathMemoization.get());
//			options.createDialogPreference(createDialogPreference.get());
			options.setThreadsPreferencesEnabled(testThreadsPreference.get());
//			options.setMaxThreads(getMaxThreads.get());


//			// Listen for property changes
			testThreadsPreference.addListener((v, o, n) -> options.setThreadsPreferencesEnabled(n));
//			filesOnly.addListener((v, o, n) -> options.setFilesOnly(n));
//			useParallelization.addListener((v, o, n) -> options.setRequestParallelization(n));
//			memoizationTimeMillis.addListener((v, o, n) -> options.setMemoizationTimeMillis(n.intValue()));
//
//			pathMemoization.addListener((v, o, n) -> options.setPathMemoization(n));
//			useExtensions.addListener((v, o, n) -> fillCollectionWithTokens(n, options.getUseAlwaysExtensions()));
//			skipExtensions.addListener((v, o, n) -> fillCollectionWithTokens(n, options.getSkipAlwaysExtensions()));

		}

	}
}
