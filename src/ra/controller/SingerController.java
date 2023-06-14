package ra.controller;

import ra.model.Singer;
import ra.service.SingerService;

public class SingerController {
    private SingerService singerService = new SingerService();
    public Singer[] getAll(){
        return singerService.getAll();
    }
    public void save(Singer singer){

        singerService.save(singer);
    }
    public Singer findById(int id){
        return singerService.findById(id);
    }
    public boolean delete(int id){
      return  singerService.delete(id);
    }
    public  int getSize(){
        return singerService.getSize();
    }

    public int getNewId(){
        return singerService.getNewId();
    }
}
