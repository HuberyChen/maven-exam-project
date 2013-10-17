package com.hubery.exam.utils.fileOperation;

import java.io.File;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

public class UnFileFactory implements IFileOperation {

	public void fileOpeartion(String filePath) {
		deCompression(filePath);
	}

	public static final String RAR_POSTFIX = ".rar";
	public static final String ZIP_POSTFIX = ".zip";

	private void deCompression(String filePath) {
		if (filePath.endsWith(RAR_POSTFIX)) {
			unRar(filePath);
		}
		if (filePath.endsWith(ZIP_POSTFIX)) {
			upZip(filePath);
		} else
			System.out.println("unknow file type");
	}

	private void unRar(String srcRarPath) {
		File file = new File(srcRarPath);
		String dstDirectoryPath = file.getParent();
		try {
			try (Archive archive = new Archive(file)) {
				if (archive != null) {
					FileHeader fileHeader = archive.nextFileHeader();
					RarDocument rarDocument = new RarDocument(fileHeader,
							dstDirectoryPath);
					rarDocument.setArchive(archive);
					rarDocument.doUnRar();
					fileHeader = archive.nextFileHeader();
				}
			}
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void upZip(String srcZipPath) {
		File file = new File(srcZipPath);
		String dstDirectoryPath = file.getParent();
		try {
			ZipFile zipFile = new ZipFile(file, "GBK");
			for (Enumeration<ZipArchiveEntry> files = zipFile.getEntries(); files
					.hasMoreElements();) {
				ZipArchiveEntry zipArchiveEntry = files.nextElement();
				ZipDocument zipDocument = new ZipDocument(zipArchiveEntry,
						dstDirectoryPath);
				zipDocument.setZipFile(zipFile);
				zipDocument.doUnZip();
			}
			zipFile.close();
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
