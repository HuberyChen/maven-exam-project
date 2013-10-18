package com.hubery.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.rarfile.FileHeader;

public class UnFileFactory implements IFileOperation {

    public void fileOpeartion(String filePath) {
        deCompression(filePath);
    }

    public static final String RAR_POSTFIX = ".rar";
    public static final String ZIP_POSTFIX = ".zip";
    public static final String GZ_POSTFIX = ".gz";

    private void deCompression(String filePath) {
        if (filePath.endsWith(RAR_POSTFIX)) {
            unRar(filePath);
        }
        if (filePath.endsWith(ZIP_POSTFIX)) {
            unZip(filePath);
        }
        if (filePath.endsWith(GZ_POSTFIX)) {
            unGz(filePath);
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
                    RarDocument rarDocument = new RarDocument(fileHeader, dstDirectoryPath + "//decompression");
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

    private void unZip(String srcZipPath) {
        File file = new File(srcZipPath);
        String dstDirectoryPath = file.getParent();
        try {
            ZipFile zipFile = new ZipFile(file, "GBK");
            for (Enumeration<ZipArchiveEntry> files = zipFile.getEntries(); files.hasMoreElements();) {
                ZipArchiveEntry zipArchiveEntry = files.nextElement();
                ZipDocument zipDocument = new ZipDocument(zipArchiveEntry, dstDirectoryPath + "//decompression");
                zipDocument.setZipFile(zipFile);
                zipDocument.doUnZip();
            }
            zipFile.close();
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("resource")
    private void unGz(String srcGzPath) {
        File file = new File(srcGzPath);
        StringBuilder dstDirectoryPath = new StringBuilder();
        dstDirectoryPath.append(file.getParent());
        GZIPInputStream in;
        try {
            in = new GZIPInputStream(new FileInputStream(srcGzPath));

            // Transfer bytes from the compressed file to the output file
            byte[] buf = new byte[1024];
            int len;

            // Open the output file
            srcGzPath = srcGzPath.replace(dstDirectoryPath.toString(), "");
            FileUtils.folderIsExists(dstDirectoryPath.append("//decompression").toString());
            File outFile = new File(dstDirectoryPath.append(srcGzPath.replace(".gz", "")).toString());
            if (!outFile.exists()) {
                OutputStream out = new FileOutputStream(outFile);
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
