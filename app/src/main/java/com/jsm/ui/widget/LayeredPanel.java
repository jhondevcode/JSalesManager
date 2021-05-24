package com.jsm.ui.widget;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.event.MouseInputAdapter;

import org.jdesktop.swingx.JXPanel;

import com.jsm.core.config.ConfigurationManager;

public class LayeredPanel extends JXPanel {

	private Color bgColor;

	public LayeredPanel(int opacity) {
		if (ConfigurationManager.getInstance().getViewConfig().get("opaque").equals("true")) {
			opacity = 255;
		}
		bgColor = new Color(0, 0, 0, opacity);
		this.setOpaque(false);
		this.setLayout(null);

		MouseInputAdapter ma = new MouseInputAdapter() {
		};

		this.addMouseListener(ma);
		this.addMouseMotionListener(ma);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(bgColor);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}
