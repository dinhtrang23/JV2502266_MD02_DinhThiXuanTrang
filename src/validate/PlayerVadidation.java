package validate;

public class PlayerVadidation {
    public static boolean vadidatePlayerId(String data){
        try{
            int playerId = Integer.parseInt(data.trim());
            if(playerId > 0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }
}
