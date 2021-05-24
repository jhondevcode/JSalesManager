package com.jsm.core;

import java.io.File;

import javax.swing.JOptionPane;

/**
 * This class is in charge of handling the verification operations in the work
 * folder, which is required for the operation of certain components that store
 * metadata, records, temporary data, retrieved data, etc.
 * 
 * @author jhondev-code
 * @version 1.0.0
 */
public class Workspace {

	public static final String PATH_SEPARATOR = System.getProperty("file.separator");
	public static final String WORKSPACE = System.getProperty("user.home") + PATH_SEPARATOR + ".jsm";

	public static final String TEMP_DIR = WORKSPACE + PATH_SEPARATOR + "temp";
	public static final String CONFIG_DIR = WORKSPACE + PATH_SEPARATOR + "config";

	public Workspace() {
	}

	/**
	 * It calls all the verifiers in order of importance, if one of the verifiers
	 * returns false, the program will terminate its execution.
	 */
	public void startChecking() {
		if (this.checkWorkspaceDir()) {
			if (this.checkConfigDir()) {
				if (!this.checkTempDir()) {
					this.errorExit("Unable to open temp directory");
				}
			} else {
				this.errorExit("Cannot open the settings directory");
			}
		} else {
			this.errorExit("Cannot open working directory");
		}
	}

	/**
	 * Verify the existence of the working directory. This directory is very
	 * important since it will store configuration files, registers, temporary
	 * files, etc.
	 */
	public boolean checkWorkspaceDir() {
		File workspace = new File(WORKSPACE);
		if (!workspace.exists()) {
			return workspace.mkdir();
		} else {
			return true;
		}
	}

	/**
	 * Verify the existence of the temporary folder on the disk. The temporary
	 * folder is important since there will be stored temporary data of processes
	 * that are executed within the program, for example: downloads, parts of
	 * requests, advance of documents, etc.
	 */
	public boolean checkTempDir() {
		File tempDir = new File(TEMP_DIR);
		if (!tempDir.exists()) {
			return tempDir.mkdir();
		} else {
			return true;
		}
	}

	/**
	 * Verify the existence of the configuration directory. This directory is very
	 * important since it contains important configuration files for the program.
	 */
	public boolean checkConfigDir() {
		File configDir = new File(CONFIG_DIR);
		if (!configDir.exists()) {
			return configDir.mkdir();
		} else {
			return true;
		}
	}

	/**
	 * Show an error message indicating the cause and then close the program.
	 */
	private void errorExit(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
		System.exit(1);
	}

}
