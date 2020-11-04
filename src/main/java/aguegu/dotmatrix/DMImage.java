package aguegu.dotmatrix;

import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class DMImage extends BufferedImage {
	private static final int blockWidth = 13;
	private static final Color backgroundColor = Color.lightGray;
	private static final Color onColor = Color.white;
	private static final Color offColor = Color.gray;

	private final Graphics2D g2d;
	private final boolean[] dot;

	public DMImage() {
		super(blockWidth * 8, blockWidth * 8, BufferedImage.TYPE_BYTE_GRAY);

		dot = new boolean[64];

		g2d = this.createGraphics();
		g2d.setBackground(backgroundColor);
		g2d.clearRect(0, 0, this.getWidth(), this.getHeight());

		BasicStroke bs = new BasicStroke(blockWidth - 1);
		g2d.setStroke(bs);

		this.update();
	}

	public void update() {
		for (int r = 0, y = blockWidth / 2; r < 8; r++, y += blockWidth) {
			for (int c = 0, x = blockWidth / 2; c < 8; c++, x += blockWidth) {
				g2d.setColor(dot[r * 8 + c] ? onColor : offColor);
				g2d.drawLine(x, y, x, y);
			}
		}
	}

	public void setDot(int index, boolean value) {
		try {
			dot[index] = value;
		} catch (ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}
	}

	public static int getBlockWidth() {
		return blockWidth;
	}
}
