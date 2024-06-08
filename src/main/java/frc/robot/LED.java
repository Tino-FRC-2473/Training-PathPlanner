package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class LED {
	private int tick = 0;
	private AddressableLED led;
	private AddressableLEDBuffer ledBuffer;
	private int rainbowFirstPixelHue = 0;
	private int greenVal = 0;
	private boolean forward = true;
	private boolean flashingOn = false;
	private static final int LED_PORT = 9;
	private static final int LED_BUFFER_LENGTH = 300;
	private static final int ONE_HUNDRED_EIGHTY = 180;
	private static final int ONE_HUNDRED = 100;
	private static final int RAINBOW_PIXEL_INCREASE = 3;

	private static final int RED_RGB_R = 255;
	private static final int RED_RGB_G = 0;
	private static final int RED_RGB_B = 0;

	private static final int ORANGE_RGB_R = 255;
	private static final int ORANGE_RGB_G = 60;
	private static final int ORANGE_RGB_B = 0;

	private static final int GREEN_RGB_R = 0;
	private static final int GREEN_RGB_G = 255;
	private static final int GREEN_RGB_B = 0;

	private static final int BLUE_RGB_R = 0;
	private static final int BLUE_RGB_G = 0;
	private static final int BLUE_RGB_B = 255;

	private static final int PURPLE_RGB_R = 166;
	private static final int PURPLE_RGB_G = 99;
	private static final int PURPLE_RGB_B = 255;

	private static final int PINK_RGB_R = 255;
	private static final int PINK_RGB_G = 0;
	private static final int PINK_RGB_B = 90;

	private static final int CR_RGB_R = 255;

	private static final int RAINBOW_S = 255;
	private static final int RAIBOW_V = 128;

	private static final int FLASH_INTERVAL = 12;
	/**
	 * Constructs LED object.
	 */
	public LED() {
		led = new AddressableLED(LED_PORT);
		ledBuffer = new AddressableLEDBuffer(LED_BUFFER_LENGTH);
		led.setLength(ledBuffer.getLength());
		led.start();
		tick = 0;
		flashingOn = false;
	}

	/**
	 * Changes the LED color to red.
	 * @param flash if you want the LEDs to flash with this color.
	 */
	public void redLight(boolean flash) {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			if (flash) {
				ledBuffer.setRGB(i, flashingOn ? RED_RGB_R : 0, flashingOn
					? RED_RGB_G : 0, flashingOn ? RED_RGB_B : 0);
			} else {
				ledBuffer.setRGB(i, RED_RGB_R, RED_RGB_G, RED_RGB_B);
			}
		}
		led.setData(ledBuffer);
		if (flash) {
			tick++;
			if (tick % FLASH_INTERVAL == 0) {
				flashingOn = !flashingOn;
			}
		}
	}

	/**
	 * Changes LED color to green.
	 */
	public void greenLight(boolean flash) {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			if (flash) {
				ledBuffer.setRGB(i, flashingOn ? GREEN_RGB_R : 0, flashingOn
					? GREEN_RGB_G : 0, flashingOn ? GREEN_RGB_B : 0);
			} else {
				ledBuffer.setRGB(i, GREEN_RGB_R, GREEN_RGB_G, GREEN_RGB_B);
			}
		}
		led.setData(ledBuffer);
		if (flash) {
			tick++;
			if (tick % FLASH_INTERVAL == 0) {
				flashingOn = !flashingOn;
			}
		}
	}

	/**
	 * Changes the LED color to orange.
	 * @param flash if you want the LEDs to flash with this color.
	 */
	public void orangeLight(boolean flash) {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			if (flash) {
				ledBuffer.setRGB(i, flashingOn ? ORANGE_RGB_R : 0, flashingOn
					? ORANGE_RGB_G : 0, flashingOn ? ORANGE_RGB_B : 0);
			} else {
				ledBuffer.setRGB(i, ORANGE_RGB_R, ORANGE_RGB_G, ORANGE_RGB_B);
			}
		}
		led.setData(ledBuffer);
		if (flash) {
			tick++;
			if (tick % FLASH_INTERVAL == 0) {
				flashingOn = !flashingOn;
			}
		}
	}

	/**
	 * Changes LED color to green.
	 */
	public void greenLight() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setRGB(i, GREEN_RGB_R, GREEN_RGB_G, GREEN_RGB_B);
		}
		led.setData(ledBuffer);
	}

	/**
	 * Changes the LED color to blue.
	 */
	public void blueLight() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setRGB(i, BLUE_RGB_R, BLUE_RGB_G, BLUE_RGB_B);
		}
		led.setData(ledBuffer);
	}

	/**
	 * Changes the LED color to purple.
	 */
	public void purpleLight() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setRGB(i, PURPLE_RGB_R, PURPLE_RGB_G, PURPLE_RGB_B);
		}
		led.setData(ledBuffer);
	}

	/**
	 * Changes the LED color to pink.
	 */
	public void pinkLight() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setRGB(i, PINK_RGB_R, PINK_RGB_G, PINK_RGB_B);
		}
		led.setData(ledBuffer);
	}

	/**
	 * Turns off the LEDs.
	 */
	public void turnOff() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setRGB(i, 0, 0, 0);
		}
		led.setData(ledBuffer);
	}

	/**
	 * Lights are Green when moving forward and red moving back.
	 */
	public void cr() {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setRGB(i, CR_RGB_R, greenVal, 0);
		}

		if (greenVal >= ONE_HUNDRED) {
			forward = false;
		} else if (greenVal <= 0) {
			forward = true;
		}

		if (forward) {
			greenVal++;
		} else {
			greenVal--;
		}

		led.setData(ledBuffer);
		// // For every pixel
		// for (var i = 0; i < ledBuffer.getLength(); i++) {
		//   // Calculate the hue - hue is easier for rainbows because the color
		//   // shape is a circle so only one value needs to precess
		//   final var hue = (m_rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180;
		//   // Set the value
		//   ledBuffer.setHSV(i, hue, 255, 128);
		// }
		// // Increase by to make the red to yellow
		// System.out.println("hue value: " + m_rainbowFirstPixelHue);
		// if (m_rainbowFirstPixelHue > 53) {
		//   m_rainbowFirstPixelHue = 0;
		// } else {
		//   m_rainbowFirstPixelHue += 3;
		// }
		// // Check bounds
		// m_rainbowFirstPixelHue %= 180;
		// led.setData(ledBuffer);
	}

	/**
	 * Sets the LED color to rainbow.
	 */
	public void rainbow() {
		// For every pixel
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			// Calculate the hue - hue is easier for rainbows because the color
			// shape is a circle so only one value needs to precess
			final var hue = (rainbowFirstPixelHue + (i * ONE_HUNDRED_EIGHTY
				/ ledBuffer.getLength())) % ONE_HUNDRED_EIGHTY;
			// Set the value
			ledBuffer.setHSV(i, hue, RAINBOW_S, RAIBOW_V);
		}
		// Increase by to make the rainbow "move"
		rainbowFirstPixelHue += RAINBOW_PIXEL_INCREASE;

		// Check bounds
		rainbowFirstPixelHue %= ONE_HUNDRED_EIGHTY;
		led.setData(ledBuffer);
	}

	/**
	 * Sets RGB value of LED.
	 * @param r
	 * @param g
	 * @param b
	 */
	public void setRGBVals(int r, int g, int b) {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			// Sets the specified LED to the RGB values for red
			ledBuffer.setRGB(i, r, g, b);
		}
		led.setData(ledBuffer);
	}

	/**
	 * Sets HSV value of LED.
	 * @param hue
	 * @param saturation
	 * @param val
	 */
	public void setHSVVals(int hue, int saturation, int val) {
		for (var i = 0; i < ledBuffer.getLength(); i++) {
			// Sets the specified LED to the HSV values for red
			ledBuffer.setHSV(i, hue, saturation, val);
		}

		led.setData(ledBuffer);
	}

}
