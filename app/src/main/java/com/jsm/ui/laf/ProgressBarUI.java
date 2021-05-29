package com.jsm.ui.laf;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class ProgressBarUI extends BasicProgressBarUI {

	public static ComponentUI createUI(JComponent c) {
		return new ProgressBarUI();
	}

	@Override
	public void paint(Graphics g, JComponent c) {

		if (!(g instanceof Graphics2D)) {
			return;
		}

		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.GRAY);
		g2.fillRect(0, 0, c.getWidth(), c.getHeight());
		if (progressBar.isIndeterminate()) {
			paintIndeterminate(g, c);
		} else {
			paintDeterminate(g, c);
		}

	}

	@Override
	protected void paintIndeterminate(Graphics g, JComponent c) {
		Insets b = progressBar.getInsets(); // area for border
		int barRectWidth = progressBar.getWidth() - (b.right + b.left);
		int barRectHeight = progressBar.getHeight() - (b.top + b.bottom);

		if (barRectWidth <= 0 || barRectHeight <= 0) {
			return;
		}

		Graphics2D g2 = (Graphics2D) g;

		// Paint the bouncing box.
		boxRect = getBox(boxRect);
		if (boxRect != null) {
			g2.setPaint(Color.GREEN);
			g2.fillRect(boxRect.x, boxRect.y, boxRect.width, boxRect.height);
		}
	}

	@Override
	protected void paintDeterminate(Graphics g, JComponent c) {
		Insets b = progressBar.getInsets(); // area for border
		int barRectWidth = progressBar.getWidth() - (b.right + b.left);
		int barRectHeight = progressBar.getHeight() - (b.top + b.bottom);

		if (barRectWidth <= 0 || barRectHeight <= 0) {
			return;
		}

		// amount of progress to draw
		int amountFull = getAmountFull(b, barRectWidth, barRectHeight);

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);

		if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
			g2.fillRect(0, 0, amountFull, c.getHeight());
		} else {
            // VERTICAL
		}
	}

}
