package co.simplon.bookhotel.repository;

import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.bookhotel.model.RoomType;

@Named
public interface RoomTypeRepository  extends JpaRepository<RoomType, Long>{
}