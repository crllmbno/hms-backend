package hotel.management.hotel_management_system.Repository;

import hotel.management.hotel_management_system.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {
}
