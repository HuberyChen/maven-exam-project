package com.hubery.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

public class ZipDocument {

    private ZipArchiveEntry zipArchiveEntry;
    private String dstDirectoryPath;
    private ZipFile zipFile;

    public ZipDocument(ZipArchiveEntry zipArchiveEntry, String dstDirectoryPath) {
        this.zipArchiveEntry = zipArchiveEntry;
        this.dstDirectoryPath = dstDirectoryPath;
    }

    public void doUnZip() throws Exception {
        String zipname = zipArchiveEntry.getName();
        ZipArchiveEntry packinfo = zipFile.getEntry(zipname);
        if (zipname.endsWith("/")) {// folder
            String fileName = dstDirectoryPath + File.separator + zipname;
            FileUtils.folderIsExists(fileName);
        } else {// file
            String fileName = dstDirectoryPath + File.separator + zipname;
            try (FileOutputStream fos = new FileOutputStream(fileName); InputStream is = zipFile.getInputStream(packinfo);) {
                IOUtils.copy(is, fos);
            }
        }
    }

    public void setZipFile(ZipFile zipFile) {
        this.zipFile = zipFile;
    }

}
