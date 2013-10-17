package com.hubery.exam.utils.fileOperation;

import java.io.File;
import java.io.FileOutputStream;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.rarfile.FileHeader;

public class RarDocument {

    private Archive archive;
    private FileHeader fh;
    private String dstDirectoryPath;

    public RarDocument(FileHeader fh, String dstDirectoryPath) {
        this.fh = fh;
        this.dstDirectoryPath = dstDirectoryPath;
    }

    public void doUnRar() throws Exception {
        String fileName;
        if (fh.getFileNameW().matches(".*[\u4e00-\u9faf].*")) {
            fileName = dstDirectoryPath + File.separator + fh.getFileNameW().trim();
        } else {
            fileName = dstDirectoryPath + File.separator + fh.getFileNameString();
        }
        if (fh.isDirectory()) {
            FileUtils.folderIsExists(fileName);
        } else {
            File out = new File(fileName);
            if (!out.exists()) {
                FileUtils.folderIsExists(out.getParentFile());
                out.createNewFile();
            }
            try (FileOutputStream os = new FileOutputStream(out)) {
                archive.extractFile(fh, os);
            }
        }
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }
}
