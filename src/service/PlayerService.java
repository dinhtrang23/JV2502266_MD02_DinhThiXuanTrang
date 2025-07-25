package service;

import dao.PlayerDAO;
import entity.Player;
import validate.PlayerVadidation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PlayerService {
    public static void findAll(){
        List<Player> listPlayers = PlayerDAO.findAll();
        if (listPlayers == null){
            System.out.println("Có lỗi xảy ra trong quá trình xử lý");
            return;
        }
        listPlayers.forEach(System.out::println);
    }
    public static void createPlayer(Scanner scanner){
        Player player = new Player();
        player.inputData(scanner);
        boolean result = PlayerDAO.createPlayer(player);
        if (result){
            System.out.println("Thêm player mới thành công");
        }else{
            System.err.println("Thêm mới player thất bại");
        }
    }
    public static void updatePlayer(Scanner scanner){
        System.out.println("Nhập mã player cần cập nhật");
        int playerId = Integer.parseInt(scanner.nextLine());
        Player playerUpdate = PlayerDAO.findPlayerById(playerId);
        if (playerUpdate == null){
            System.err.println("Mã player không tồn tại");
        }else{
            boolean isExist = true;
            do{
                System.out.println("1. Cập nhật tên player");
                System.out.println("2. Cập nhật Email");
                System.out.println("3. Cập nhật SĐT");
                System.out.println("4. Cập nhật ngày đăng ký");
                System.out.println("5. Cập nhật trạng thái");
                System.out.println("6. Thoát");
                System.out.print("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        System.out.println("Nhập tên player");
                        playerUpdate.setPlayerName(scanner.nextLine());
                        break;
                        case 2:
                            System.out.println("Nhập vào email mới");
                            playerUpdate.setEmail(scanner.nextLine());
                            break;
                            case 3:
                                System.out.println("Nhập SĐT mới");
                                playerUpdate.setPhoneNumber((scanner.nextLine()));
                                break;
                                case 4:
                                    System.out.println("Nhập ngày tạo mới");
                                    playerUpdate.setRegisterDate(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                                    break;
                                    case 5:
                                        System.out.println("Nhập vào trạng thái mới");
                                        playerUpdate.setStatus(Boolean.parseBoolean(scanner.nextLine()));
                                        break;
                                        case 6:
                                            isExist = false;
                                            break;
                                            default:
                                                System.err.println("Vui lòng nhập từ 1-5");

                }
            }while (isExist);
            boolean result = PlayerDAO.updatePlayer(playerUpdate);
            if (result){
                System.out.println("Cập nhật thành công");
            }else {
                System.err.println("Có lỗi trong quá trình cap nhat");
            }
        }
    }
    public static void deletePlayer(Scanner scanner){
        System.out.println("Nhap ma can xoa");
        int playerId = Integer.parseInt(scanner.nextLine());
        Player playerDelete = PlayerDAO.findPlayerById(playerId);
        if (playerDelete == null){
            System.err.println("Mã không tồn tại");
        }else{
            System.out.println("Bạn có chắc chăn muon xóa không\"" + playerDelete.getPlayerName()+"\" không?(Y/N)");
            String confirm = scanner.nextLine().trim();
            if (confirm.equals("Y")){
                boolean result = PlayerDAO.deletePlayerById(playerId);
                if (result){
                    System.out.println("Xóa thành công");
                }else{
                    System.err.println("Xóa thất bại");
                }
            }else {
                System.out.println("Hủy thao tác xóa");
            }
        }
    }
    public static void searchPlayerByName(Scanner scanner){
        System.out.println("Nhập tên player cần tìm");
        String playerName = scanner.nextLine();
        List<Player> ListPlayers = PlayerDAO.searchPlayerByName(playerName);
        if (ListPlayers.isEmpty()) {
            System.out.println("Không tìm thấy tên player");
        }else{
            System.out.printf("Tìm thấy %d player:\n",ListPlayers.size());
            ListPlayers.forEach(System.out::println);
        }
    }

    public static void sortPlayerByRegister(Scanner scanner){
        List<Player> listPlayers = PlayerDAO.findAll();
        System.out.println("Danh sách ngày tạo sau khi sắp xếp");
        listPlayers.stream().sorted(Comparator.comparing(Player::getRegisterDate).reversed()).forEach(System.out::println);
    }
}
