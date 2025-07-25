import entity.Player;
import service.PlayerService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("******PLAYER MANAGEMENT******");
            System.out.println("1. Danh sách người chơi");
            System.out.println("2. Thêm mới người chơi");
            System.out.println("3. Cập nhật thông tin người chơi");
            System.out.println("4. Xóa người chơi");
            System.out.println("5. Tìm kiếm người chơi theo tên");
            System.out.println("6. Sắp xếp người chơi theo ngày đăng ký");
            System.out.println("7. Thoát");
            System.out.print("Nhập lựa chọn của bạn");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    PlayerService.findAll();
                    break;
                    case 2:
                        PlayerService.createPlayer(scanner);
                        break;
                        case 3:
                            PlayerService.updatePlayer(scanner);
                            break;
                            case 4:
                                PlayerService.deletePlayer(scanner);
                                break;
                                case 5:
                                    PlayerService.searchPlayerByName(scanner);
                                    break;
                                    case 6:
                                        PlayerService.sortPlayerByRegister(scanner);
                                        break;
                                        case 7:
                                            System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-7");
            }

        }while (true);

    }
}