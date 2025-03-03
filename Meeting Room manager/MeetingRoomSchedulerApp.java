package myproject;
import java.util.*;

enum RoomFeature {
 PROJECTOR,
 VIDEO_CONFERENCING,
 WHITEBOARD,
 CONFERENCE_PHONE,
 AIR_CONDITIONING
}

class MeetingRoom {
 private String roomId;
 private String roomName;
 private int capacity;
 private EnumSet<RoomFeature> features;

 public MeetingRoom(String roomId, String roomName, int capacity, EnumSet<RoomFeature> features) {
     this.roomId = roomId;
     this.roomName = roomName;
     this.capacity = capacity;
     this.features = features;
 }

 public String getRoomId() {
     return roomId;
 }

 public String getRoomName() {
     return roomName;
 }

 public EnumSet<RoomFeature> getFeatures() {
     return features;
 }

 @Override
 public String toString() {
     return roomName;
 }
}

class RoomScheduler {
 private Map<String, MeetingRoom> rooms;

 public RoomScheduler() {
     this.rooms = new HashMap<>();
 }

 public void addMeetingRoom(MeetingRoom room) {
     rooms.put(room.getRoomId(), room);
     System.out.println("Room added: " + room.getRoomName() + ", ID: " + room.getRoomId());
 }

 public String bookRoom(String roomId, EnumSet<RoomFeature> requiredFeatures) {
     MeetingRoom room = rooms.get(roomId);
     if (room != null && room.getFeatures().containsAll(requiredFeatures)) {
         return "Room " + roomId + " booked successfully.";
     }
     return "Room " + roomId + " does not meet the required features.";
 }

 public List<String> listAvailableRooms(EnumSet<RoomFeature> requiredFeatures) {
     List<String> availableRooms = new ArrayList<>();
     for (MeetingRoom room : rooms.values()) {
         if (room.getFeatures().containsAll(requiredFeatures)) {
             availableRooms.add(room.getRoomName());
         }
     }
     System.out.println("Available rooms with " + requiredFeatures + ": " + availableRooms);
     return availableRooms;
 }
}


public class MeetingRoomSchedulerApp {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     RoomScheduler scheduler = new RoomScheduler();

     while (true) {
         System.out.println("Choose an option: \n1. Add Room \n2. Book Room \n3. List Available Rooms \n4. Exit");
         int choice = scanner.nextInt();
         scanner.nextLine();
         
         switch (choice) {
             case 1:
                 System.out.print("Enter Room ID: ");
                 String roomId = scanner.nextLine();
                 System.out.print("Enter Room Name: ");
                 String roomName = scanner.nextLine();
                 System.out.print("Enter Capacity: ");
                 int capacity = scanner.nextInt();
                 scanner.nextLine();
                 
                 System.out.println("Enter features separated by commas (PROJECTOR, VIDEO_CONFERENCING, WHITEBOARD, CONFERENCE_PHONE, AIR_CONDITIONING):");
                 String[] featureInput = scanner.nextLine().split(",");
                 EnumSet<RoomFeature> features = EnumSet.noneOf(RoomFeature.class);
                 for (String feature : featureInput) {
                     features.add(RoomFeature.valueOf(feature.trim().toUpperCase()));
                 }
                 
                 scheduler.addMeetingRoom(new MeetingRoom(roomId, roomName, capacity, features));
                 break;
             
             case 2:
                 System.out.print("Enter Room ID to book: ");
                 String bookRoomId = scanner.nextLine();
                 System.out.println("Enter required features separated by commas:");
                 String[] requiredFeaturesInput = scanner.nextLine().split(",");
                 EnumSet<RoomFeature> requiredFeatures = EnumSet.noneOf(RoomFeature.class);
                 for (String feature : requiredFeaturesInput) {
                     requiredFeatures.add(RoomFeature.valueOf(feature.trim().toUpperCase()));
                 }
                 
                 System.out.println(scheduler.bookRoom(bookRoomId, requiredFeatures));
                 break;
             
             case 3:
                 System.out.println("Enter required features separated by commas:");
                 String[] listFeaturesInput = scanner.nextLine().split(",");
                 EnumSet<RoomFeature> listFeatures = EnumSet.noneOf(RoomFeature.class);
                 for (String feature : listFeaturesInput) {
                     listFeatures.add(RoomFeature.valueOf(feature.trim().toUpperCase()));
                 }
                 
                 scheduler.listAvailableRooms(listFeatures);
                 break;
             
             case 4:
                 System.out.println("Exiting...");
                 scanner.close();
                 return;
             
             default:
                 System.out.println("Invalid choice. Please try again.");
         }
     }
 }
}
