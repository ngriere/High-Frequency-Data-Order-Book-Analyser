package Models;

import java.io.File;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nicolas_2
 */
public class FileRead {

    private final ArrayList<File> filesInSelectedFolder;
    private final ArrayList<File> dateFolders;
    private final String ROOT_FOLDER_NAME = "Generated Data";

    public FileRead() {
        filesInSelectedFolder = new ArrayList<File>();
        dateFolders = new ArrayList<File>();
    }

    public ArrayList<File> getDateFolders() {
        File directory = new File(ROOT_FOLDER_NAME);

        // get all the files from a directory
        File[] folderList = directory.listFiles();
        for (File folder : folderList) {
            if (folder.isDirectory()) {
                // Add it to directories list
                dateFolders.add(folder);
            }
        }
        return dateFolders;
    }

    public ArrayList<File> getFilesFromFolder(String selectedFolderName) {

        // If there are already files than we must empty it
        filesInSelectedFolder.clear();

        File selectedFolder = new File(ROOT_FOLDER_NAME + "//" + selectedFolderName);
        // get all the files from directory
        File[] fileList = selectedFolder.listFiles();
        for (File file : fileList) {
            if (file.isFile()) {
                // Add it to file list
                filesInSelectedFolder.add(file);
            }
        }
        return filesInSelectedFolder;
    }
}
