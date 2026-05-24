package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entties.Train;
import ticket.booking.entties.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService
{
    private User user;
    // to fetch users from localdb
    private List<User> userList;

    private final ObjectMapper objectMapper=new ObjectMapper();
    private static final String USERS_PATH = "C:/Users/Varshini/Desktop/Projects/IRCTC/app/src/main/java/ticket/booking/localDb/users.json";

    public UserBookingService(User user1) throws IOException   //custom constructor
    {
        this.user=user1;
        loadUsers();
    }

    public UserBookingService() throws IOException{
        loadUsers();
    }

    public List<User> loadUsers() throws IOException {
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
        return userList;
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equalsIgnoreCase(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }
    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }
    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBookings(){
        user.printTickets();
    }


    public Boolean bookTrainSeat(Train trainSelectedForBooking, int row, int col) {
        return null;
    }

    public List<List<Integer>> fetchSeats(Train trainSelectedForBooking) {
        return List.of();
    }

    public List<Train> getTrains(String source, String destination) {
        return List.of();
    }
}
