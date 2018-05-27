package pl.krejzolekpro.system.data;

import pl.krejzolekpro.system.utils.MessageUtil;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private MessageUtil messageUtil = MessageUtil.getInstance();
    private static FileManager instance;

    public FileManager(){
        instance = this;
    }

    public static FileManager getInstance(){
        if(instance == null){
            return new FileManager();
        }
        return instance;
    }

    private String file_path = "E:";

    public void load(){
        File file = new File(file_path, "ticketList.yml");
        if(!file.exists()){
            try {
                file.createNewFile();
                file.setWritable(false);
                file.setReadable(false);
                messageUtil.send("Plik zostal znaleziony.", true);
            } catch (IOException e) {
                e.printStackTrace();
                messageUtil.send("Nie odnaleziono pliku 'ticketList.yml'. Zostal on stworzony.", true);
            }
        }
        file.setWritable(false);
        file.setReadable(false);
    }
}
