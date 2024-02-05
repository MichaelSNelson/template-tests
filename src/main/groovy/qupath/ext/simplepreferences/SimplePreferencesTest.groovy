package qupath.ext.simplepreferences

import javafx.beans.property.StringProperty;
import javafx.scene.control.MenuItem
import qupath.lib.common.GeneralTools;
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

	// Setting the variables here is enough for them to be available in the extension
	String name = "Groovy Preferences test"
	String description = "This is a demo to show how Groovy Preferences work"
	Version QuPathVersion = Version.parse("v0.5.0")
	@Override
	public String getName() {
		return QuPathResources.getString("Extension.PreferencesTest");
	}


	@Override
	public String getDescription() {
		return QuPathResources.getString("Extension.ImageJ.description"); //TODO ????
	}

	/**
	 * Returns the version stored within this jar, because it is matched to the QuPath version.
	 */
	@Override
	public Version getQuPathVersion() {
		return getVersion();
	}
	@Override
	void installExtension(QuPathGUI qupath) {
		addMenuItem(qupath)
	}
	// Path to ImageJ - used to determine plugins directory
	private static StringProperty testPreference = null;


	static {
		// Try to default to the most likely ImageJ path on a Mac
		if (GeneralTools.isMac() && new File("/Applications/ImageJ/").isDirectory())
			testPreference = PathPrefs.createPersistentPreference("ijPath", "/Applications/ImageJ");
		else
			testPreference = PathPrefs.createPersistentPreference("ijPath", null);
	}

	/**
	 * Set a String value to the preference.
	 * @param path
	 */
	public static void setPreferencesString(final String path) {
		testPreference.set(path);
	}

	/**
	 * Return a string value from a preference.
	 * @return
	 */
	public static String getPreferencesString() {
		return testPreference.get();
	}

	/**
	 * Property representing the path to a local ImageJ installation, or null if no path has been set.
	 * @return
	 */
	public static StringProperty preferencesString() {
		return testPreference;
	}
	@ActionMenu(value = ["Menu.Extensions", "Preferences test>"])
	@ActionConfig("Action.ImageJ.setPluginsDirectory")
	public final Action actionPlugins = ActionTools.createAction(() -> promptToSetPluginsDirectory());

	static void promptToSetPluginsDirectory() {
		String path = getPreferencesString();
		File dir = FileChoosers.promptForDirectory("Set ImageJ plugins directory", path == null ? null : new File(path));
		if (dir != null && dir.isDirectory())
			setPreferencesString(dir.getAbsolutePath());
	}


	private void addMenuItem(QuPathGUI qupath) {
		def menu = qupath.getMenu("Extensions>${name}", true)
		def menuItem = new MenuItem("Output current values for SimplePreferences extension.")
		menuItem.setOnAction(e -> {
			Dialogs.showMessageDialog(name,
					"Let's try a simple Dialog")
		})
		menu.getItems() << menuItem

	}
//	public static class testPreferences {
//
//		private BioFormatsServerOptions options = BioFormatsServerOptions.getInstance();
//
//		@BooleanPref("Prefs.BioFormats.enable")
//		public final BooleanProperty enableBioformats = PathPrefs.createPersistentPreference("bfEnableBioformats", options.bioformatsEnabled());
//		@BooleanPref("Prefs.BioFormats.localOnly")
//		public final BooleanProperty filesOnly = PathPrefs.createPersistentPreference("bfFilesOnly", options.getFilesOnly());
//		@BooleanPref("Prefs.BioFormats.useParallelization")
//		public final BooleanProperty useParallelization = PathPrefs.createPersistentPreference("bfUseParallelization", options.requestParallelization());
//		@IntegerPref("Prefs.BioFormats.memoizationTimeMillis")
//		public final IntegerProperty memoizationTimeMillis = PathPrefs.createPersistentPreference("bfMemoizationTimeMillis", options.getMemoizationTimeMillis());
//
//		@DirectoryPref("Prefs.BioFormats.pathMemoization")
//		public final StringProperty pathMemoization = PathPrefs.createPersistentPreference("bfPathMemoization", options.getPathMemoization());
//		@StringPref("Prefs.BioFormats.alwaysUseExtensions")
//		public final StringProperty useExtensions = PathPrefs.createPersistentPreference("bfUseAlwaysExtensions", String.join(" ", options.getUseAlwaysExtensions()));
//		@StringPref("Prefs.BioFormats.skipExtensions")
//		public final StringProperty skipExtensions = PathPrefs.createPersistentPreference("bfSkipAlwaysExtensions", String.join(" ", options.getSkipAlwaysExtensions()));
//
//
//		private BioFormatsPreferences() {
//
//			// Set options using any values previously stored
//			options.setFilesOnly(filesOnly.get());
//			options.setPathMemoization(pathMemoization.get());
//			options.setBioformatsEnabled(enableBioformats.get());
//			options.setRequestParallelization(useParallelization.get());
//			options.setMemoizationTimeMillis(memoizationTimeMillis.get());
//			fillCollectionWithTokens(useExtensions.get(), options.getUseAlwaysExtensions());
//			fillCollectionWithTokens(skipExtensions.get(), options.getSkipAlwaysExtensions());
//
//			// Listen for property changes
//			enableBioformats.addListener((v, o, n) -> options.setBioformatsEnabled(n));
//			filesOnly.addListener((v, o, n) -> options.setFilesOnly(n));
//			useParallelization.addListener((v, o, n) -> options.setRequestParallelization(n));
//			memoizationTimeMillis.addListener((v, o, n) -> options.setMemoizationTimeMillis(n.intValue()));
//
//			pathMemoization.addListener((v, o, n) -> options.setPathMemoization(n));
//			useExtensions.addListener((v, o, n) -> fillCollectionWithTokens(n, options.getUseAlwaysExtensions()));
//			skipExtensions.addListener((v, o, n) -> fillCollectionWithTokens(n, options.getSkipAlwaysExtensions()));
//
//		}
//
//	}
}
