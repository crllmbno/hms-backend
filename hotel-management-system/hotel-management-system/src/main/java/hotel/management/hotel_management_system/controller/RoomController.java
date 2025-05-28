package hotel.management.hotel_management_system.controller;

import hotel.management.hotel_management_system.Repository.RoomRepository;
import hotel.management.hotel_management_system.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    // CREATE
    @PostMapping
    public Room addRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    // READ - All
    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // READ - One
    @GetMapping("/{roomNo}")
    public Room getRoom(@PathVariable String roomNo) {
        return roomRepository.findById(roomNo).orElse(null);
    }

    // UPDATE
    @PutMapping("/{roomNo}")
    public Room updateRoom(@PathVariable String roomNo, @RequestBody Room updatedRoom) {
        updatedRoom.setRoomNo(roomNo);
        return roomRepository.save(updatedRoom);
    }

    // DELETE
    @DeleteMapping("/{roomNo}")
    public void deleteRoom(@PathVariable String roomNo) {
        roomRepository.deleteById(roomNo);
    }
}
