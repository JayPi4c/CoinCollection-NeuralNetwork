package com.JayPi4c;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public static final String lineSeparator = System
			.getProperty("line.separator");

	public static void main(String args[]) throws IOException {

		File allInOneFile = new File(getExecutionPath() + "/allData.csv");
		if (!allInOneFile.exists())
			allInOneFile.createNewFile();

		BufferedWriter allWriter = new BufferedWriter(new FileWriter(
				allInOneFile));

		for (int i = 0; i <= 7; i++) {
			File directory = new File(getExecutionPath() + "/" + i);
			System.out.println("Directory " + directory.getAbsolutePath()
					+ "; number of files inside: " + directory.list().length);
			System.out.println();

			File finalFile = new File(directory.getParentFile()
					.getAbsolutePath() + "/" + i + ".csv");

			if (!finalFile.exists())
				finalFile.createNewFile();

			BufferedWriter BW = new BufferedWriter(new FileWriter(finalFile));
			for (int j = 1; j <= directory.list().length; j++) {
				String path = directory.getPath() + "/" + i + "IMG" + j
						+ ".JPG";
				System.out.println("expected Path: " + path);
				File f = new File(path);

				BufferedImage img = ImageIO.read(f);
				// BufferedImage grayScale = getGrayScaleImage(img);

				BW.write(i + "");
				allWriter.write(i + "");

				for (int x = 0; x < img.getWidth(); x++) {
					for (int y = 0; y < img.getHeight(); y++) {
						int rgb = img.getRGB(x, y);
						int r = (rgb >> 16) & 0xFF;
						int g = (rgb >> 8) & 0xFF;
						int b = (rgb & 0xFF);
						int gray = (r + g + b) / 3;
						String text = gray + "";
						BW.write("," + text);
						allWriter.write("," + text);
					}
				}
				BW.newLine();
				allWriter.newLine();

			}
			BW.close();
		}
		allWriter.close();

	}

	public static String getExecutionPath() {
		String absolutePath = new File(".").getAbsolutePath();
		File file = new File(absolutePath);
		absolutePath = file.getParentFile().toString();
		return absolutePath;
	}
}
