package com.jsm.ui.widget;

import java.awt.HeadlessException;
import java.awt.Taskbar;
import java.awt.Taskbar.Feature;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

/**
 * @author jhondev-code
 * @version 1.0.0
 */
public class ProcessWindow extends JDialog {

	private String message;
	private JProgressBar progressBar;

	/**
	 * ss
	 */
	public ProcessWindow(Window parent, String message) {
		super(parent);
		this.setUndecorated(true);
		this.initUI();
	}

	/**
	 * ss
	 */
	private void initUI() {
		this.progressBar = new JProgressBar();
	}

	/**
	 * ss
	 */
	public void setMinimum(int min) {
		this.progressBar.setMinimum(min);
	}

	/**
	 * ss
	 */
	public void setMaximum(int max) {
		this.progressBar.setMaximum(max);
	}

	/**
	 * ss
	 */
	public void setValue(int value) {
		if (!this.progressBar.isIndeterminate()) {
			this.progressBar.setValue(value);
			this.updateTaskBar(value);
		}
	}

	/**
	 * 
	 */
	public void setIndeterminate(boolean newValue) {
		this.progressBar.setIndeterminate(newValue);
	}

	private void updateTaskBar(int value) {
		try {
			if (Taskbar.isTaskbarSupported()) {
				Taskbar taskbar = Taskbar.getTaskbar();
				if (taskbar.isSupported(Feature.PROGRESS_VALUE_WINDOW)) {
					taskbar.setWindowProgressValue(this, value);
				}
			}
		} catch (Exception ex) {
			/* ignored */
		}
	}

	/**
	 * ss
	 */
	public void execute() {
	}

	/**
	 * ss
	 */
	public void finih() {
		this.updateTaskBar(0);
	}

}
