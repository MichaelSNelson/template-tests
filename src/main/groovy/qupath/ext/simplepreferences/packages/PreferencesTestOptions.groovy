package qupath.ext.simplepreferences.packages

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qupath.lib.common.Version;

/**
 * Container for various options for a simple test of the QuPath Preferences.
 *
 * modified from
 * @author Pete Bankhead
 *
 */

//accessing private variables in a class https://www.youtube.com/watch?v=UJJIW7NJln4

public class PreferencesTestOptions {

    private static final Logger logger = LoggerFactory.getLogger(PreferencesTestOptions.class);


    private static PreferencesTestOptions instance = new PreferencesTestOptions();

    private boolean testThreadsPreferencesEnabled = true;

    private PreferencesTestOptions() {}

    /**
     * Get the static instance of BioFormatsServerOptions, available to servers being constructed.
     * @return
     */
    public static PreferencesTestOptions getInstance() {
        return instance;
    }

    /**
     * Returns true if Bio-Formats is enabled and may be used to read images.
     * @return
     */
    public boolean testThreadsPreferencesEnabled() {
        return testThreadsPreferencesEnabled;
    }

    public void setThreadsPreferencesEnabled(final boolean testThreadsPreferencesEnabled) {
        this.testThreadsPreferencesEnabled = testThreadsPreferencesEnabled;
    }


//    private int maxThreads = -1
//
//    private boolean createComplexDialog = true;
//
//    private String stringPreference = "";
//    int getMaxThreads() {
//        if (maxThreads <= 0) {
//            maxThreads = Math.min(Math.max(2, Runtime.getRuntime().availableProcessors()), 32);
//            logger.info("Setting max number of threads readers to {}", maxThreads);
//        }
//        return testThreadsPreferencesEnabled ? maxThreads : 1;
//    }
//
//    void setMaxThreads(int maxThreads) {
//        this.maxThreads = maxThreads;
//    }

//    public String getStringPreference(){
//        return stringPreference;
//    }
//    public void setStringPreference(final String stringPreference){
//        this.stringPreference = stringPreference;
//    }
//
//    /**
//     * Returns true if multiple readers may be created for different threads, to enable parallel image reading.
//     * @return
//     */
//    public boolean createComplexDialog() {
//        return createComplexDialog;
//    }

}