package ra.service;

import ra.model.Singer;
import ra.model.Song;

import java.util.Date;

public class SongService {
    private Song[] listSong = new Song[100];
    private int size;
    private SingerService singerService = new SingerService();

    public SongService() {
        Singer[] listSinger = singerService.getAll();
        listSong[0] = new Song("Song1", "Hoa Co lau", "Hayy",listSinger[0],"SDAD",new Date(),true );
        listSong[1] = new Song("Song2", "Hoa Co Mau", "Hayyasdd",listSinger[1],"Huann ",new Date(),true );
        size++;
    }

    public Song[] getAll() {
        return listSong;
    }

    public int getSize() {
        return size;
    }

    public boolean save(Song song) {
//        add
        if (findById(song.getSongId()) == null) {
            if (size == listSong.length) {
                System.out.println("Danh sach Book ko co gi");
                return false;
            } else {
                for (int i = 0; i < listSong.length; i++) {
                    if (listSong[i] == null) {
                        listSong[i] = song;
                        break;
                    }
                }
                System.out.println("Them moi sach thanh cong");
                size++;
                return true;
            }
        } else {
//            update
            for (int i = 0; i < listSong.length; i++) {
                if (listSong[i] != null && listSong[i].getSongId() == song.getSongId()) {
                    listSong[i] = song;
                    break;
                }
            }
            System.out.println("Cập nhật thành công");
            return true;
        }

    }

    public Song findById(String songId) {
        for (Song song : listSong) {
            if (song != null) {
                if (song.getSongId().equals(songId) ) {
                    return song;
                }
            }
        }
        return null;
    }

    public boolean delete(String songId) {
        if (findById(songId) != null) {
            for (int i = 0; i < listSong.length; i++) {
                if (listSong[i] != null && listSong[i].getSongId().equals(songId) ) {
                    listSong[i] = null;
                    break;
                }
            }
            size--;
            System.out.println("Xoa thanh cong");
            return true;
        }
        System.out.println("Ko tim thay id can xoa");
        return false;
    }

}
