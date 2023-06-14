package ra.service;

import ra.model.Singer;

public class SingerService {
    private Singer[] listSinger = new Singer[100];
    private int size;

    public SingerService() {
        listSinger[0] = new Singer(1, "KanD", 23, "VN", true, "POP");
        listSinger[1] = new Singer(2, "Khanh", 23, "VN", true, "VPOP");
        size ++;
    }

    public Singer[] getAll() {
        return listSinger;
    }

    public int getSize() {
        return size;
    }

    public boolean save(Singer singer) {
//        add
        if (findById(singer.getSingerId()) == null) {
            if (size == listSinger.length) {
                System.out.println("Danh sach Book ko co gi");
                return false;
            } else {
                for (int i = 0; i < listSinger.length; i++) {
                    if (listSinger[i] == null) {
                        listSinger[i] = singer;
                        break;
                    }
                }
                System.out.println("Them moi sach thanh cong");
                size++;
                return true;
            }
        } else {
//            update
            for (int i = 0; i < listSinger.length; i++) {
                if (listSinger[i] != null && listSinger[i].getSingerId() == singer.getSingerId()) {
                    listSinger[i] = singer;
                    break;
                }
            }
            System.out.println("Cập nhật thành công");
            return true;
        }

    }

    public Singer findById(int id) {
        for (Singer singer : listSinger) {
            if (singer != null) {
                if (singer.getSingerId() == id) {
                    return singer;
                }
            }
        }
        return null;
    }

    public boolean delete(int id) {
        if (findById(id) != null) {
            for (int i = 0; i < listSinger.length; i++) {
                if (listSinger[i] != null && listSinger[i].getSingerId() == id) {
                    listSinger[i] = null;
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

    public int getNewId() {
        int max = 0;
        for (Singer singer : listSinger) {
            if (singer != null) {
                if (singer.getSingerId() >= max) {
                    max = singer.getSingerId();
                }
            }
        }
        return max + 1;
    }
}
