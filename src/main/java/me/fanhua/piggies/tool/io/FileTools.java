package me.fanhua.piggies.tool.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public final class FileTools {

	private FileTools() {}

	public static boolean copyFile(File inFile, File outFile) {
		return copyFile(inFile, outFile, StandardCopyOption.REPLACE_EXISTING);
	}

	public static boolean copyFile(File inFile, File outFile, CopyOption... options) {
		if (!inFile.exists()) return false;
		try {
			Files.copy(inFile.toPath(), outFile.toPath(), options);
		} catch (IOException ioe) {
			return false;
		}
		return true;
	}

	public static boolean copyDirectory(File inDir, File outDir) {
		return copyDirectory(inDir, outDir, StandardCopyOption.REPLACE_EXISTING);
	}

	public static boolean copyDirectory(File inDir, File outDir, CopyOption... options) {
		if (!inDir.exists()) return false;
		if (!outDir.exists())
			try {
				Files.copy(inDir.toPath(), outDir.toPath(), options);
			} catch (IOException ioe) {
				return false;
			}
		var list = inDir.list();
		if (list == null) return false;
		for (var name : list)
			if (!copy(new File(inDir, name), new File(outDir, name), options)) return false;
		return true;
	}

	public static boolean copy(File inFile, File outFile) {
		return copyFile(inFile, outFile, StandardCopyOption.REPLACE_EXISTING);
	}

	public static boolean copy(File inFile, File outFile, CopyOption... options) {
		return inFile.isDirectory() ? copyDirectory(inFile, outFile) : copyFile(inFile, outFile);
	}

}
