package ra.controller;

import ra.model.Singer;
import ra.model.Song;
import ra.service.SongService;

public class SongController {
    private SongService songService = new SongService();
    public Song[] getAll(){
        return songService.getAll();
    }
    public void save(Song song){

        songService.save(song);
    }
    public Song findById(String id){
        return songService.findById(id);
    }
    public void delete(String id){
        songService.delete(id);
    }
    public  int getSize(){
        return songService.getSize();
    }

}
