package com.jsm.core.lang;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.tinylog.Logger;

import com.jsm.core.Workspace;
import com.jsm.core.config.Configuration;
import com.jsm.core.config.ConfigurationManager;

/**
 * The class provides the ability to get values from dictionaries in which there
 * are different names for widgets and messages. Dictionaries are manipulated as
 * property files which store a key and value pair, internally the values are
 * loaded into memory for quick access to the values. If a value is not found in
 * the dictionary, a string with the content "undefined" is returned.
 * 
 * @author jhondev-code
 * @version 1.0.0
 */
public class DictionaryManager {

	private static DictionaryManager localInstance;
	private Configuration langConfig;
	private File dictionary;
	private Map<String, String> dictionaryProperties;
	private Map<String, String> words;

	/**
	 * Obtain the path of the dictionary using the corresponding configuration
	 * manager to later process the content.
	 */
	private DictionaryManager() {
		this.langConfig = ConfigurationManager.getInstance().getLangConfig();
		this.process();
	}

	/**
	 * It makes the corresponding call to the methods in charge of preparing the
	 * file and the one in charge of reading the values.
	 */
	private void process() {
		this.open();
		this.readWords();
	}

	/**
	 * This method is used to load the information about the dictionary and then
	 * proceed to open the file.
	 */
	private void open() {
		this.dictionaryProperties = this.langConfig.get(List.of("name", "path", "url"));
		this.dictionary = new File(this.dictionaryProperties.get("path").replace("${WORKSPACE}", Workspace.WORKSPACE));
	}

	/**
	 * It is in charge of reading the keys and values of the dictionary in order to
	 * store them in a map that will be used to consult and thus guarantee quick
	 * access to the values.
	 */
	private void readWords() {
		this.words = new HashMap<>();
		Properties config = new Properties();
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(this.dictionary));
			config.load(bis);
			Enumeration<Object> keys = config.keys();
			while (keys.hasMoreElements()) {
				Object key = keys.nextElement();
				this.words.put(key.toString(), config.get(key).toString());
			}
			config.values();
		} catch (IOException ioe) {
			Logger.error(ioe);
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException ioe) {
					Logger.error(ioe);
				}
			}
		}
	}

	/**
	 * @return the names of the dictionary developers
	 */
	public String getDictionaryAuthor() {
		return this.dictionaryProperties.get("author");
	}

	/**
	 * @return the url where the dictionary is hosted
	 */
	public String getDictionaryURL() {
		return this.dictionaryProperties.get("url");
	}

	/**
	 * Finds a value on the map that was preloaded with the dictionary values.
	 * 
	 * @param key the key to searching for value
	 * @return the value of the key otherwise it returns the value "undefined"
	 */
	public String value(String key) {
		String word = this.words.get(key);
		return word != null ? word : "undefined";
	}

	/**
	 * Create an instance to be able to access the functionalities.
	 */
	public static DictionaryManager getInstance() {
		if (localInstance == null) {
			localInstance = new DictionaryManager();
		}
		return localInstance;
	}

}
