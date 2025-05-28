package hotel.management.hotel_management_system.controller;

import hotel.management.hotel_management_system.model.Room;
import hotel.management.hotel_management_system.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    // CREATE
    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        Room saved = roomRepository.save(room);
        return ResponseEntity.ok(saved);
    }

    // READ - All
    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // READ - One
    @GetMapping("/{roomNo}")
    public ResponseEntity<Room> getRoom(@PathVariable String roomNo) {
        return roomRepository.findById(roomNo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{roomNo}")
    public ResponseEntity<Room> updateRoom(@PathVariable String roomNo, @RequestBody Room updatedRoom) {
        return roomRepository.findById(roomNo)
                .map(room -> {
                    room.setName(updatedRoom.getName());
                    room.setContact(updatedRoom.getContact());
                    room.setDate(updatedRoom.getDate());
                    return ResponseEntity.ok(roomRepository.save(room));
                })
                .orElseGet(() -> {
                    updatedRoom.setRoomNo(roomNo);
                    return ResponseEntity.ok(roomRepository.save(updatedRoom));
                });
    }

    // DELETE
    @DeleteMapping("/{roomNo}")
    public ResponseEntity<Void> deleteRoom(@PathVariable String roomNo) {
        if (!roomRepository.existsById(roomNo)) {
            return ResponseEntity.notFound().build();
        }
        roomRepository.deleteById(roomNo);
        return ResponseEntity.noContent().build();
    }
}
