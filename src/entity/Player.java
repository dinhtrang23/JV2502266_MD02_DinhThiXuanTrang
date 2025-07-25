package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Player {
    private int playerId;
    private String playerName;
    private String email;
    private String phoneNumber;
    private LocalDate registerDate;
    private boolean status;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Mã player: %s - Họ tên player: %s - Email đăng ký:%s\n " +
                "SĐT player: %s - Ngày đăng ký: %s - Trạng thái:%s\n",
                this.playerId, this.playerName, this.email, this.phoneNumber, this.registerDate, this.status ? "Hoạt động" : "Không hoạt động");
    }

    public void inputData(Scanner scanner) {
        System.out.println("Nhập vào tên player");
        this.playerName = scanner.nextLine();
        System.out.println("Nhập Email đã đăng ký:");
        this.email = scanner.nextLine();
        System.out.println("Nhập SĐT");
        this.phoneNumber = scanner.nextLine();
        System.out.println("Nhập ngày đăng ký");
        this.registerDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    }
}


