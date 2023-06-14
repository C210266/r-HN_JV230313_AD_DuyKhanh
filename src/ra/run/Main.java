package ra.run;

import ra.config.Config;
import ra.controller.SingerController;
import ra.controller.SongController;
import ra.model.Singer;
import ra.model.Song;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static SingerController singerController = new SingerController();
    private static SongController songController = new SongController();

    public static void main(String[] args) {
        System.out.println("------------MUSIC MANAGEMENT-------------");
        System.out.println("1 . Quan li ca si");
        System.out.println("2 . Quan li bai hat");
        System.out.println("3 . Tim kiem bai hat");
        System.out.println("4 . Thoat");
        int choice = Config.scanner().nextInt();
        switch (choice) {
            case 1:
                menuSinger();
                break;
            case 2:
                menuSong();
                break;
            case 3:
                searchSong();
                break;
            case 4:
                System.out.println("Exit");
                break;
            default:
                System.out.println("Hay nhap gia tri tu 1 den 4");
        }
    }

    //  1.   Menu Singer
    public static void menuSinger() {
        while (true) {
            System.out.println("----------------SINGER_MANAGEMENT--------------------");
            System.out.println("1 .Them moi singer");
            System.out.println("2 . Hien thi danh sach singer");
            System.out.println("3 . Thay doi thong tin singer");
            System.out.println("4 . Xoa ca si theo id");
            System.out.println("5 . Thoat");
            int choice = Config.scanner().nextInt();
            switch (choice) {
                case 1:
                    addNewSinger();
                    break;
                case 2:
                    displaySinger();
                    break;
                case 3:
                    updateSinger();
                    break;
                case 4:
                    deleteSinger();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Nhap so tu 1 den 5");
            }
            if (choice == 5) {
                break;
            }

        }
    }

    public static void addNewSinger() {
        System.out.println("Nhap so luong singer ma ban muon them");
        int n = Config.scanner().nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("nhập thông tin singer thứ " + (i + 1));
            Singer newSinger = new Singer();
            int id = singerController.getNewId();
            System.out.println("Id : " + id);
            newSinger.setSingerId(id);
            System.out.println("nhập tên ca si");
            newSinger.setSingerName(Config.scanner().nextLine());
            System.out.println("nhập tuoi");
            newSinger.setAge(Config.scanner().nextInt());
            System.out.println("nhập quoc tich");
            newSinger.setNationality(Config.scanner().nextLine());
            System.out.println("nhập gioi tinh");
            String gender = Config.scanner().nextLine();
            newSinger.setGender(gender == "Nam" ? true : false);
            System.out.println("nhập the loai/type");
            newSinger.setGenre(Config.scanner().nextLine());
            // lưu nó vào listcategory
            singerController.save(newSinger);
        }
    }

    public static void displaySinger() {
        Singer[] listSinger = singerController.getAll();
        if (singerController.getSize() == 0) {
            System.out.println("Ko co ca si nao");
        } else {
            for (Singer singer : listSinger) {
                if (singer != null) {
                    System.out.println(singer);
                }
            }
        }
    }

    public static void updateSinger() {
        System.out.println("Nhap id ca si ma ban muon thay doi");
        int index = Config.scanner().nextInt();
        Singer[] listSinger = singerController.getAll();
        Singer singeEdit = singerController.findById(index);
        if (singeEdit != null) {
            System.out.println("nhập tên ca si mới");
            singeEdit.setSingerName(Config.scanner().nextLine());
            System.out.println("nhập tuoi mới");
            singeEdit.setAge(Config.scanner().nextInt());
            System.out.println("nhập quoc tich mới");
            singeEdit.setNationality(Config.scanner().nextLine());
            System.out.println("nhập gioi tinh mới");
            String gender = Config.scanner().nextLine();
            singeEdit.setGender(gender == "Nam");
            System.out.println("nhập the loai/genre mới");
            singeEdit.setGenre(Config.scanner().nextLine());
            singerController.save(singeEdit);
        } else {
            System.err.println("Id không tồn tại");
        }

    }

    public static void deleteSinger() {
        System.out.println("Nhập vào ID của ca sĩ mà bạn muốn xóa:");
        int singerId = Config.scanner().nextInt();

        Song[] listSong = songController.getAll();
        boolean singerHasSongs = false;

        for (Song song : listSong) {
            if (song != null && song.getSinger().getSingerId() == singerId) {
                singerHasSongs = true;
                break;
            }
        }

        if (singerHasSongs) {
            System.out.println("Không thể xóa ca sĩ vì có bài hát liên quan đến ca sĩ này.");
        } else {
            boolean deleted = singerController.delete(singerId);
            if (deleted) {
                System.out.println("Xóa ca sĩ thành công.");
            } else {
                System.out.println("Không tìm thấy ca sĩ với ID đã nhập.");
            }
        }
    }


    //   2.  Menu Song
    public static void menuSong() {
        while (true) {
            System.out.println("----------------SONG MANAGEMENT--------------------");
            System.out.println("1 .Them moi song");
            System.out.println("2 . Hien thi danh sach song");
            System.out.println("3 . Thay doi thong tin song");
            System.out.println("4 . Xoa bai hat theo id");
            System.out.println("5 . Thoat ");
            int choice = Config.scanner().nextInt();
            switch (choice) {
                case 1:
                    addNewSong();
                    break;
                case 2:
                    displaySong();
                    break;
                case 3:
                    updateSong();
                    break;
                case 4:
                    deleteSong();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Nhap so tu 1 den 5");
                    break;
            }
            if (choice == 5) {
                break;
            }

        }
    }

    public static void addNewSong() {
        System.out.println("Nhap so luong bai hat ma ban muon them");
        int n = Config.scanner().nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("nhập thông tin song thứ " + (i + 1));
            Song newSong = new Song();
            System.out.println("Nhap songId");
            String songId = Config.scanner().nextLine();
            System.out.println("songId : " + songId);
            newSong.setSongId(songId);
            System.out.println("nhập tên bai hat");
            newSong.setSongName(Config.scanner().nextLine());
            System.out.println("nhập mo ta");
            newSong.setDescription(Config.scanner().nextLine());
            System.out.println("Nhập tên ca sĩ:");
            String singerName = Config.scanner().nextLine();
            boolean singerNotFound = true;
            for (Singer singer : singerController.getAll()) {
                if (singer != null && singer.getSingerName().equals(singerName)) {
                    newSong.setSinger(singer);
                    singerNotFound = false;
                    break;
                }
            }

            if (singerNotFound) {
                System.out.println("Không tìm thấy ca sĩ với tên đã nhập. Vui lòng thêm ca sĩ trước.");
            }

            System.out.println("nhập songWriter");
            newSong.setSongWriter(Config.scanner().nextLine());
            System.out.println("nhập songStatus");
            newSong.setSongStatus(Config.scanner().nextBoolean());
            // lưu nó vào listcategory
            songController.save(newSong);
        }
    }

    public static void displaySong() {
        Song[] listSong = songController.getAll();
        if (songController.getSize() == 0) {
            System.out.println("Ko co ca si nao");
        } else {
            for (Song song : listSong) {
                if (song != null) {
                    System.out.println(song);
                }
            }
        }
    }

    public static void updateSong() {
        System.out.println("Nhap songId ma ban muon thay doi");
        String index = Config.scanner().nextLine();
        Song[] listSinger = songController.getAll();
        Song songEdit = songController.findById(index);
        if (songEdit != null) {
            System.out.println("nhập tên bai hat mới");
            songEdit.setSongName(Config.scanner().nextLine());
            System.out.println("nhập mo ta");
            songEdit.setDescription(Config.scanner().nextLine());
            System.out.println("Nhập tên ca sĩ:");
            String singerName = Config.scanner().nextLine();
            for (Singer singer : singerController.getAll()) {
                if (singer != null && singer.getSingerName() == singerName) {
                    songEdit.setSinger(singer);
                } else {
                    System.out.println("Ca sĩ không tồn tại. Vui lòng thêm ca sĩ trước.");
                }
            }
            System.out.println("nhập songWriter");
            songEdit.setSongWriter(Config.scanner().nextLine());
            System.out.println("nhập songStatus mới");
            songEdit.setSongStatus(Config.scanner().nextBoolean());
            songController.save(songEdit);
        } else {
            System.err.println("Id không tồn tại");
        }
    }

    public static void deleteSong() {
        System.out.println("Nhap songId ma ban muon xoa");
        String songId = Config.scanner().nextLine();
        for (Song song : songController.getAll()) {
            if (song != null && song.getSongId().equals(songId)) {
                songController.delete(songId);
            }

        }
    }


    //  3.  Search song
    public static void searchSong() {
        System.out.println("-------------SEARCH SONG------------------");
        System.out.println("1 . Tìm kiếm tên bài hát hoặc thể loại");
        System.out.println("2 . Tìm kiếm ca sĩ theo tên hoặc thể loại");
        System.out.println("3 . Hiển thị danh sách bài hát theo thứ tự tăng dần");
        System.out.println("4 . Hiển thị 10 bài hát đăng mới nhất");
        System.out.println("5 . Thoát");
        int choice = Config.scanner().nextInt();
        switch (choice) {
            case 1:
                searchSongBySinger();
                break;
            case 2:
                searchSingerBySong();
                break;
            case 3:
                searchListSong();
                break;
            case 4:
                display10Song();
                break;
            case 5:
                break;
            default:
                System.out.println("Nhap gia tri tu 1 --- 5");
        }
    }

    public static void searchSongBySinger() {
        System.out.println("Nhap ten ca si ma ban muon search cac bai hat");
        String singerName = Config.scanner().nextLine();
        Song[] listSong = songController.getAll();
        for (Song song : listSong) {
            if (song != null && song.getSinger().getSingerName().equals(singerName)) {
                System.out.println(song);
            } else {
                System.out.println("ko co bai hat dc tim thay theo ten ca si");
            }
        }

    }

    public static void searchSingerBySong() {
        System.out.println("Nhap ten ca si ma ban muon search");
        String singerName = Config.scanner().nextLine();
        Singer[] listSinger = singerController.getAll();
        for (Singer singer : listSinger) {
            if (singer != null && singer.getSingerName().equals(singerName)) {
                System.out.println(singer);
            } else {
                System.out.println("Ko co ten ca si nao trung voi ten ban vua nhap");
            }
        }
    }

    public static void searchListSong() {
        Song[] listSong = songController.getAll();
        Arrays.sort(listSong, (song1, song2) -> song1.getSongName().compareToIgnoreCase(song2.getSongName()));

        // Print the sorted list of songs
        for (Song song : listSong) {
            if (song != null) {
                System.out.println(song);
            }
        }
    }

    public static void display10Song() {
        Song[] listSong = songController.getAll();

        Arrays.sort(listSong, Comparator.comparing(Song::getCreatedDate).reversed());

        int count = 0;
        for (Song song : listSong) {
            if (song != null) {
                System.out.println(song);
                count++;
            }

            if (count == 10) {
                break;
            }
        }
    }

}
